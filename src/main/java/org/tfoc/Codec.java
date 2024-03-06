package org.tfoc;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Your Codec object will be instantiated and called as such:
 * Codec serializer = new Codec();
 * Codec deserializer = new Codec();
 * TreeNode ans = deserializer.deserialize( serializer.serialize(root) );
 */
public class Codec {

    public static final String WHITE = "white";
    int i = 0;

    /**
     * Encodes a tree to a single string.
     */
    public String serialize(TreeNode root) {

        StringBuilder sb = new StringBuilder();
        String valueFromTreeNode = getValueFromTreeNode(root);
        if (Objects.nonNull(valueFromTreeNode)) {
            sb.append(valueFromTreeNode);
        }

        return String.join(",", sb.toString());
    }

    /**
     * Decodes your encoded data to tree.
     */
    public TreeNode deserialize(String data) {
        if (Objects.isNull(data))
            return null;

        List<String> list = Arrays.asList(data.split(","));
        return stringToTreeNode(list);
    }


    private String getValueFromTreeNode(TreeNode treeNode) {
        if (Objects.nonNull(treeNode)) {
            String data = (Objects.isNull(treeNode.val)) ? WHITE : "" + treeNode.val;
            return data + "," + getValueFromTreeNode(treeNode.left) + "," + getValueFromTreeNode(treeNode.right);
        }
        return WHITE;
    }

    private TreeNode stringToTreeNode(List<String> data) {
        if (WHITE.equals(data.get(i)))
            return null;

        TreeNode treeNode = new TreeNode(Integer.parseInt(data.get(i)));
        i++;
        treeNode.left = stringToTreeNode(data);
        i++;
        treeNode.right = stringToTreeNode(data);

        return treeNode;
    }
}