package com.bravofly.exercise.main;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private int weight;
    private Airports node;
    private List<TreeNode> children;

    public TreeNode(Airports node, int weight) {
        this.node = node;
        this.weight = weight;
        children = new ArrayList<>();
    }

    public int getWeight() {
        return weight;
    }

    public Airports getNode() {
        return node;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "weight=" + weight +
                ", node=" + node +
                ", children=" + children.size() +
                '}';
    }
}
