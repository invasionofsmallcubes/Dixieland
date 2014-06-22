/*
 * Copyright 2002-2005 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import info.jtrac.domain.Item;
import info.jtrac.domain.ItemUser;
import info.jtrac.domain.User;
import info.jtrac.util.ItemUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.mail.Header;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;

/**
 * Class to handle sending of E-mail and pre-formatted messages
 */

// major: methods are too long
// major: why a MailSender is building the content of the email? Too many responsibilities in one class
// minor: it's just a matter of aesthetics but public method should be listed before private methods, it would be easier to read.

public class MailSender {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private JavaMailSenderImpl sender;
	private String prefix;
	private String from;
	private String url;
	private MessageSource messageSource;
	private Locale defaultLocale;

	public MailSender(Map<String, String> config, MessageSource messageSource,
			String defaultLocale) {
		// initialize email sender
		this.messageSource = messageSource;
		this.defaultLocale = StringUtils.parseLocaleString(defaultLocale);
		String mailSessionJndiName = config.get("mail.session.jndiname");


        // major: the initialization of the sender should be done
        // using a factory, and should be passed as an argument
        // to the constructor, the mail sender should not be aware
        // of the logic of the creation of the sender.

        if (StringUtils.hasText(mailSessionJndiName)) {
			initMailSenderFromJndi(mailSessionJndiName);
		}
		if (sender == null) {
			initMailSenderFromConfig(config);
		}


		// if sender is still null the send* methods will not
		// do anything when called and will just return immediately
		String tempUrl = config.get("jtrac.url.base");
		if (tempUrl == null) {
			tempUrl = "http://localhost/jtrac/";
		}
		if (!tempUrl.endsWith("/")) {
			tempUrl = tempUrl + "/";
		}
		this.url = tempUrl;
		logger.info("email hyperlink base url set to '" + this.url + "'");
	}

	/**
	 * we bend the rules a little and fire off a new thread for sending
	 * an email message.  This has the advantage of not slowing down the item
	 * create and update screens, i.e. the system returns the next screen
	 * after "submit" without blocking.  This has been used in production
	 * (and now I guess in many JTrac installations worldwide)
	 * for quite a while now, on Tomcat without any problems.  This helps a lot
	 * especially when the SMTP server is slow to respond, etc.
	 */
	private void sendInNewThread(final MimeMessage message) {
		new Thread() {
			@Override
			public void run() {
				logger.debug("send mail thread start");

        // major: nested try catch. It's really ugly.
        // It appears to be caused by getAllHeaders that is throwing
        // a MessagingException.
        try {
					try {
						sender.send(message);
						logger.debug("send mail thread successfull");
					} catch (Exception e) {
						logger.error("send mail thread failed", e);
						logger.error("mail headers dump start");
						Enumeration headers = message.getAllHeaders();
						while (headers.hasMoreElements()) {
							Header h = (Header) headers.nextElement();
							logger.info(h.getName() + ": " + h.getValue());
						}
						logger.error("mail headers dump end");
					}
          // major: why propagating a RuntimeException in a thread?
          // It's going to be swallowed in its own thread of execution
          // No one will see it. Also, this is a duplicated catch.
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}.start();
	}

	private String fmt(String key, Locale locale) {
		try {
			return messageSource.getMessage("mail_sender." + key, null, locale);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return "???mail_sender." + key + "???";
		}
	}

	private String addHeaderAndFooter(StringBuffer html) {
		StringBuffer sb = new StringBuffer();
		// additional cosmetic tweaking of e-mail layout
		// style just after the body tag does not work for a minority of clients
		// like gmail, thunderbird etc.
		// ItemUtils adds the main inline CSS when generating the email content,
		// so we gracefully degrade
		sb
				.append("<html><body><style type='text/css'>table.jtrac th, table.jtrac td { padding-left: 0.2em; padding-right: 0.2em; }</style>");
		sb.append(html);
		sb.append("</html>");
		return sb.toString();
	}

	private String getItemViewAnchor(Item item, Locale locale) {
		String itemUrl = url + "app/item/" + item.getRefId();
		return "<p style='font-family: Arial; font-size: 75%'><a href='"
				+ itemUrl + "'>" + itemUrl + "</a></p>";
	}

	private String getSubject(Item item) {
		String summary = null;
		if (item.getSummary() == null) {
			summary = "";
		} else if (item.getSummary().length() > 80) {
			summary = item.getSummary().substring(0, 80);
		} else {
			summary = item.getSummary();
		}
		return prefix + " #" + item.getRefId() + " " + summary;
	}

  // major: this method should accept not the Item but a Message to send.
  // the sending is the minor and less invasive part of the method.
	public void send(Item item) {

    // major: well, sender is null, so no email is going out...silently. Just a debug message.
    // this means that the initial configuration is not able to create a sender.
    // This control should no be here and the methods that handle jndi or configFile should throw a
    // ConfigurationException if the sender is not created.
		if (sender == null) {
			logger.debug("mail sender is null, not sending notifications");
			return;
		}

		// TODO make this locale sensitive per recipient
    // major: don't use the comment to explain what the code is doing, just wrap the function in a
    // method with a talking name. This apply to all comments in this method.
    logger.debug("attempting to send mail for item update");
		// prepare message content
		StringBuffer sb = new StringBuffer();
		String anchor = getItemViewAnchor(item, defaultLocale);
		sb.append(anchor);
    // minor: why is not important here the user's locale like in the sendUserPassword method?
		sb.append(ItemUtils.getAsHtml(item, messageSource, defaultLocale));
		sb.append(anchor);
		if (logger.isDebugEnabled()) {
			logger.debug("html content: " + sb);
		}
		// prepare message
		MimeMessage message = sender.createMimeMessage();
    // minor: I would use StandardCharsets.UTF_8.name() that return the canonical name
    // major: how do you test the use cases about the message?
    // the message is in the local stack so it can't be tested
    // better to implement a bridge pattern to decouple the messageHelper and pass it as
    // a collaborator. Although keep in mind that building the message is not a responsibility
    // of this class so this class should just send the message and not building it.
		MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

		// Remember the TO person email to prevent duplicate mails
    // minor: recipient would be a better name for this variable
    // major: catching a general Exception is a bad idea. You are telling me
    // we could have a problem but when I'm reading it and I don't understand
    // what could go wrong (and, also, the try block is too long so that's one of
    // the reason I can't see what could go wrong)
		String toPersonEmail;
		try {
			helper.setText(addHeaderAndFooter(sb), true);
			helper.setSubject(getSubject(item));
			helper.setSentDate(new Date());
			helper.setFrom(from);
			// set TO
			if (item.getAssignedTo() != null) {
				helper.setTo(item.getAssignedTo().getEmail());
				toPersonEmail = item.getAssignedTo().getEmail();
			} else {
				helper.setTo(item.getLoggedBy().getEmail());
				toPersonEmail = item.getLoggedBy().getEmail();
			}
			// set CC
			List<String> cclist = new ArrayList<String>();
			if (item.getItemUsers() != null) {
				for (ItemUser itemUser : item.getItemUsers()) {
					// Send only, if person is not the TO assignee
					if (!toPersonEmail.equals(itemUser.getUser().getEmail())) {
						cclist.add(itemUser.getUser().getEmail());
					}
				}

				// sounds complicated but we have to ensure that no null
				// item will be set in setCC(). So we collect the cc items
				// in the cclist and transform it to an stringarray.
				if (cclist.size() > 0) {
					String[] cc = cclist.toArray(new String[0]); 
					helper.setCc(cc);
				}
			}
			// send message
			// workaround: Some PSEUDO user has no email address. Because email
			// address
			// is mandatory, you can enter "no" in email address and the mail
			// will not
			// be sent.

      // major: this check is too late, we created everything and then we
      // won't use it.
			if (!"no".equals(toPersonEmail))
				sendInNewThread(message);
		} catch (Exception e) {
			logger.error("failed to prepare e-mail", e);
		}
	}

  // major: this method should accept not the User but a Message to send.
  // the sending is the smallest and less invasive part of the method.
	public void sendUserPassword(User user, String clearText) {
    // major: well, sender is null, so no email is going out...silently. Just a debug message.
    // This means that the initial configuration is not able to create a sender.
    // This control should no be here and the methods that handle jndi or configFile should throw a
    // ConfigurationException if the sender is not created.
		if (sender == null) {
			logger
					.debug("mail sender is null, not sending new user / password change notification");
			return;
		}
		logger.debug("attempting to send mail for user password");

		String localeString = user.getLocale();
		Locale locale = null;
		if (localeString == null) {
			locale = defaultLocale;
		} else {
			locale = StringUtils.parseLocaleString(localeString);
		}

    // major: there is a bit of code duplication with the method send.
    // Apparently it seems that just the body is changing but the rest is
    // really similar. As suggested above the way a message is created should be
    // refactored in a collaborator. This method should just send a message.

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
		try {
			helper.setTo(user.getEmail());
			helper.setSubject(prefix + " " + fmt("loginMailSubject", locale));
			StringBuffer sb = new StringBuffer();
			sb.append("<p>" + fmt("loginMailGreeting", locale) + " "
					+ user.getName() + ",</p>");
			sb.append("<p>" + fmt("loginMailLine1", locale) + "</p>");
			sb.append("<table class='jtrac'>");
			sb.append("<tr><th style='background: #CCCCCC'>"
					+ fmt("loginName", locale)
					+ "</th><td style='border: 1px solid black'>"
					+ user.getLoginName() + "</td></tr>");
			sb.append("<tr><th style='background: #CCCCCC'>"
					+ fmt("password", locale)
					+ "</th><td style='border: 1px solid black'>" + clearText
					+ "</td></tr>");
			sb.append("</table>");
			sb.append("<p>" + fmt("loginMailLine2", locale) + "</p>");
			sb.append("<p><a href='" + url + "'>" + url + "</a></p>");
			helper.setText(addHeaderAndFooter(sb), true);
			helper.setSentDate(new Date());
			// helper.setCc(from);
			helper.setFrom(from);
			sendInNewThread(message);

      // major: generic exception, method too long, I don't understand what could fail.
		} catch (Exception e) {
			logger.error("failed to prepare e-mail", e);
		}
	}

	private void initMailSenderFromJndi(String mailSessionJndiName) {
		logger.info("attempting to initialize mail sender from jndi name = '"
				+ mailSessionJndiName + "'");
		JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
		factoryBean.setJndiName(mailSessionJndiName);
        // "java:comp/env/" will be prefixed if the JNDI name doesn't already
        // have it

        // major: the above behavoiur is already documented
        // in the class JndiObjectFactoryBean. Remove it.

		factoryBean.setResourceRef(true);
		try {
			// this step actually does the JNDI lookup
			factoryBean.afterPropertiesSet();
		} catch (Exception e) {
			logger.warn("failed to locate mail session : " + e);
			return;
		}
		Session session = (Session) factoryBean.getObject();
		sender = new JavaMailSenderImpl();
		sender.setSession(session);
		logger.info("email sender initialized from jndi name = '"
				+ mailSessionJndiName + "'");
	}

	private void initMailSenderFromConfig(Map<String, String> config) {
		String host = config.get("mail.server.host");
		if (host == null) {
			logger
					.warn("'mail.server.host' config is null, mail sender not initialized");
			return;
		}
		String port = config.get("mail.server.port");
		String localhost = config.get("mail.smtp.localhost");
		from = config.get("mail.from");
		prefix = config.get("mail.subject.prefix");
		String userName = config.get("mail.server.username");
		String password = config.get("mail.server.password");
		String startTls = config.get("mail.server.starttls.enable");
		logger.info("initializing email adapter: host = '" + host
				+ "', port = '" + port + "', from = '" + from + "', prefix = '"
				+ prefix + "'");
		this.prefix = prefix == null ? "[jtrac]" : prefix;
		this.from = from == null ? "jtrac" : from;
		int p = 25;
		if (port != null) {
			try {
				p = Integer.parseInt(port);
			} catch (NumberFormatException e) {
				logger.warn("mail.server.port not an integer : '" + port
						+ "', defaulting to 25");
			}
		}
		sender = new JavaMailSenderImpl();
		sender.setHost(host);
		sender.setPort(p);
		Properties props = null;

		if (userName != null) {
			// authentication requested
			props = new Properties();
			props.put("mail.smtp.auth", "true");
			if (startTls != null && startTls.toLowerCase().equals("true")) {
				props.put("mail.smtp.starttls.enable", "true");
			}
			sender.setUsername(userName);
			sender.setPassword(password);
		}

		if (localhost != null) {
			if (props == null) {
				props = new Properties();
			}
			props.put("mail.smtp.localhost", localhost);
		}

		if (props != null) {
			sender.setJavaMailProperties(props);
		}

		logger.info("email sender initialized from config: host = '" + host
				+ "', port = '" + p + "'");
	}

}