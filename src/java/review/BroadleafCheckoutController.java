/*
 * #%L
 * BroadleafCommerce Framework Web
 * %%
 * Copyright (C) 2009 - 2013 Broadleaf Commerce
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.exception.ServiceException;
import org.broadleafcommerce.common.payment.PaymentGatewayType;
import org.broadleafcommerce.common.payment.PaymentTransactionType;
import org.broadleafcommerce.common.payment.PaymentType;
import org.broadleafcommerce.common.vendor.service.exception.PaymentException;
import org.broadleafcommerce.common.web.payment.controller.PaymentGatewayAbstractController;
import org.broadleafcommerce.core.order.domain.NullOrderImpl;
import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.order.service.exception.IllegalCartOperationException;
import org.broadleafcommerce.core.payment.domain.OrderPayment;
import org.broadleafcommerce.core.payment.domain.PaymentTransaction;
import org.broadleafcommerce.core.pricing.service.exception.PricingException;
import org.broadleafcommerce.core.web.checkout.model.OrderInfoForm;
import org.broadleafcommerce.core.web.order.CartState;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// general comments about the class
// major: methods are too long, this class is actually really exhausting to read

/**
 * In charge of performing the various checkout operations
 * 
 * @author Andre Azzolini (apazzolini)
 * @author Elbert Bautista (elbertbautista)
 * @author Joshua Skorton (jskorton)
 */
public class BroadleafCheckoutController extends AbstractCheckoutController {

    private static final Log LOG = LogFactory.getLog(BroadleafCheckoutController.class);
    // major: baseConfirmationRedirect sounds like something that should not be reassigned. Why not final?
    protected static String baseConfirmationRedirect = "redirect:/confirmation";

    /**
     * Renders the default checkout page and allows modules to add variables to the model.
     *
     * @param request
     * @param response
     * @param model
     * @return the return path
     */
    public String checkout(HttpServletRequest request, HttpServletResponse response, Model model,
                           RedirectAttributes redirectAttributes) {

        // major: semantic naming mismatch Order != Cart.
        Order cart = CartState.getCart();


        // minor: the naming of the method is redundant. orderService.preValidateOperationOn(cart)
        // is better.

        // major: the code block in the IllegalCartOperationException appears to be not
        // an exceptional handling. It sets an "domain lock" called cartRequiresLock as a consequence
        // of a preInvalidCartOperation so it should be handled in the normal flow

        // major: the order service is preValidating a cart operation using a Order. The Order , in this file
        // appears to be just a container for some data. It would be better to give to this class
        // domain responsibility eventually using the orderService as collaborator when needed
        try {

            orderService.preValidateCartOperation(cart);
        } catch (IllegalCartOperationException ex) {
            model.addAttribute("cartRequiresLock", true);
        }

        // major: use polymorphism! The if block is always executed with the only exception of
        // nullOrderImpl, just implement a method on the superclass and override inside NullOrderImpl to do nothing
        if (!(cart instanceof NullOrderImpl)) {
            model.addAttribute("orderMultishipOptions",
                    orderMultishipOptionService.getOrGenerateOrderMultishipOptions(cart));
            model.addAttribute("paymentRequestDTO",
                    dtoTranslationService.translateOrder(cart));
        }

        // major: duplicated code here and in saveGlobalOrderDetails, better to refactor in one method
        populateModelWithReferenceData(request, model);
        return getCheckoutView();
    }


    /**
     * Attempts to attach the user's email to the order so that they may proceed anonymously
     * @param request
     * @param model
     * @param orderInfoForm
     * @param result
     * @return
     * @throws ServiceException
     */
    public String saveGlobalOrderDetails(HttpServletRequest request, Model model, 
            OrderInfoForm orderInfoForm, BindingResult result) throws ServiceException {
      // major: duplication, used in every method, use it as a field
      Order cart = CartState.getCart();

        // major: orderInfoFormValidator use orderInfoForm to validate data and eventually set errors on result.
        // orderInfoForm.validate(result) moving the responsibility to validate to orderInfoForm (the only responsibility) it has
        orderInfoFormValidator.validate(orderInfoForm, result);
        if (result.hasErrors()) {
            // We need to clear the email on error in case they are trying to edit it
            try {
              // major: boolean parameter as argument. You can split the save method in two.
              // Also move the cart.setEmailAddress(null) inside the false branch of the splitted method

              // minor: null is not really meaningful, probably would be better to introduce a cart.clearEmail()
              cart.setEmailAddress(null);
              orderService.save(cart, false);
            } catch (PricingException pe) {
                LOG.error("Error when saving the email address for order confirmation to the cart", pe);
            }

            // see review on line 101
            populateModelWithReferenceData(request, model);
            return getCheckoutView();
        }
        
        try {
            // major: duplicate code on line 130. Better to separate the save from
            // the setting of the email
            cart.setEmailAddress(orderInfoForm.getEmailAddress());
            orderService.save(cart, false);
        } catch (PricingException pe) {
            LOG.error("Error when saving the email address for order confirmation to the cart", pe);
        }
        
        return getCheckoutPageRedirect();   
    }

    /**
     * Creates a pass-through payment of the PaymentType passed in with
     * an amount equal to the order total after any non-final applied payments.
     * (for example gift cards, customer credit, or third party accounts)
     *
     * This intended to be used in cases like COD and other Payment Types where implementations wish
     * to just checkout without having to do any payment processing.
     *
     * This default implementations assumes that the pass-through payment is the only
     * "final" payment, as this will remove any payments that are not PaymentTransactionType.UNCONFIRMED
     * That means that it will look at all transactions on the order payment and see if it has unconfirmed transactions.
     * If it does, it will not remove it. **/



     /**
     * Make sure not to expose this method in your extended Controller if you do not wish to
     * have this feature enabled.
     *
     * @param redirectAttributes
     * @param paymentType
     * @return
     * @throws PaymentException
     * @throws PricingException
     */
    public String processPassthroughCheckout(final RedirectAttributes redirectAttributes,
                                             PaymentType paymentType) throws PaymentException, PricingException {

      Order cart = CartState.getCart();

        //Invalidate any payments already on the order that do not have transactions on them that are UNCONFIRMED
        List<OrderPayment> paymentsToInvalidate = new ArrayList<OrderPayment>();
        for (OrderPayment payment : cart.getPayments()) {
            if (payment.isActive()) {
                if (payment.getTransactions() == null || payment.getTransactions().isEmpty()) {
                    paymentsToInvalidate.add(payment);
                } else {
                    for (PaymentTransaction transaction : payment.getTransactions()) {
                        if (!PaymentTransactionType.UNCONFIRMED.equals(transaction.getType())) {
                             paymentsToInvalidate.add(payment);
                        }
                    }
                }
            }
        }

        for (OrderPayment payment : paymentsToInvalidate) {
            cart.getPayments().remove(payment);
            if (paymentGatewayCheckoutService != null) {
                paymentGatewayCheckoutService.markPaymentAsInvalid(payment.getId());
            }
        }

        //Create a new Order Payment of the passed in type

        // major: break of encapsulation. The payment service has the responsibility
        // to create the OrderPayment and there are setter called to add other information.
        // It should be added a method createFullPayment (since we are setting the cart, the amount
        // and the gateway type, which is a constant btw)

        OrderPayment passthroughPayment = orderPaymentService.create();
        passthroughPayment.setType(paymentType);
        passthroughPayment.setPaymentGatewayType(PaymentGatewayType.PASSTHROUGH);
        passthroughPayment.setAmount(cart.getTotalAfterAppliedPayments());
        passthroughPayment.setOrder(cart);

        // major: the same as above

        // Create the transaction for the payment
        PaymentTransaction transaction = orderPaymentService.createTransaction();
        transaction.setAmount(cart.getTotalAfterAppliedPayments());
        transaction.setRawResponse("Passthrough Payment");
        transaction.setSuccess(true);
        transaction.setType(PaymentTransactionType.AUTHORIZE_AND_CAPTURE);

        transaction.setOrderPayment(passthroughPayment);
        passthroughPayment.addTransaction(transaction);

        // major: not really clear why passing "cart" as parameter
        // when it's setted inside "passthroughPayment".
        // minor: null parameter - better to use a wrapper for this method
        // without the null parameter exposed
        orderService.addPaymentToOrder(cart, passthroughPayment, null);
        orderService.save(cart, true);

        // minor: this method is returning a String, the name of the method
        // it's not really clear about the what is returning. It actually sounds as a void
        // operation.
        return processCompleteCheckoutOrderFinalized(redirectAttributes);
    }

    /**
     * If the order has been finalized. i.e. all the payments have been applied to the order,
     * then you can go ahead and call checkout using the passed in order id.
     * This is usually called from a Review Page or if enough Payments have been applied to the Order to complete checkout.
     * (e.g. Gift Cards cover the entire amount and there is no need to call an external Payment Gateway, or
     * a Payment from a Hosted Gateway has already been applied to the order like Paypal Express Checkout)
     *
     * @return
     * @throws Exception
     */

    public String processCompleteCheckoutOrderFinalized(final RedirectAttributes redirectAttributes) throws PaymentException {
        Order cart = CartState.getCart();

        if (cart != null && !(cart instanceof NullOrderImpl)) {
            try {
                String orderNumber = initiateCheckout(cart.getId());
                return getConfirmationViewRedirect(orderNumber);
            } catch (Exception e) {
                handleProcessingException(e, redirectAttributes);
            }
        }

        return getCheckoutPageRedirect();
    }

  // minor: why is this method public, it appears to be used only inside the class
  // major: throwing a java.lang.Exception at this level means the the paymentGatewayCheckoutService
  // it's not handling it's exceptions correctly. At this level we expect a domain exception, like PaymentException.
  // initiateCheckout is probably just called only inside this method
  public String initiateCheckout(Long orderId) throws Exception{
        if (paymentGatewayCheckoutService != null && orderId != null) {
            return paymentGatewayCheckoutService.initiateCheckout(orderId);
        }
        return null;
    }

    public void handleProcessingException(Exception e, RedirectAttributes redirectAttributes) throws PaymentException {
        if (LOG.isTraceEnabled()) {
            LOG.trace("A Processing Exception Occurred finalizing the order. Adding Error to Redirect Attributes.");
        }

        redirectAttributes.addAttribute(PaymentGatewayAbstractController.PAYMENT_PROCESSING_ERROR,
                PaymentGatewayAbstractController.getProcessingErrorMessage());
    }

    public String getBaseConfirmationRedirect() {
        return baseConfirmationRedirect;
    }

    protected String getConfirmationViewRedirect(String orderNumber) {
        return getBaseConfirmationRedirect() + "/" + orderNumber;
    }

}
