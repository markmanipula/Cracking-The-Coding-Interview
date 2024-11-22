package com.prep.interview.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree {

    TreeNode root;

    public boolean isSameTree(final TreeNode p, final TreeNode q) {

        List<Integer> pList = new ArrayList<>();
        List<Integer> qList = new ArrayList<>();
        buildList(p, pList);
        buildList(q, qList);

        return pList.equals(qList);
    }

    private void buildList(TreeNode node, List<Integer> list) {
        if (node == null) {
            list.add(null);
            return;
        }
        list.add(node.value);
        buildList(node.left, list);
        buildList(node.right, list);
    }

    public boolean isTreeBst(final TreeNode node) {
        return isTreeBstHelper(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isTreeBstHelper(final TreeNode node, final int min, final int max) {
        if (node == null) return true;

        if (node.value <= min || node.value >= max) {
            return false;
        }

        boolean isLeftBst = isTreeBstHelper(node.left, min, node.value);
        boolean isRightBst = isTreeBstHelper(node.right, max, node.value);

        return isLeftBst && isRightBst;
    }

    public boolean isTreeBalanced(final TreeNode node) {
        return isTreeBalancedHelper(node) == 1;
    }

    private int isTreeBalancedHelper(final TreeNode node) {
        if (node == null) return 0;

        int leftHeight = isTreeBalancedHelper(node.left);
        if (leftHeight == -1) return -1;
        int rightHeight = isTreeBalancedHelper(node.right);
        if (rightHeight == -1) return -1;

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public List<LinkedList<TreeNode>> createListOfDepths(final TreeNode root) {

        final List<LinkedList<TreeNode>> output = new ArrayList<>();

        if (root == null) {
            return output;
        }

        final Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            final int depth = queue.size();
            final LinkedList<TreeNode> depthList = new LinkedList<>();

            for (int i = 0; i < depth; i++) {
                final TreeNode current = queue.poll();
                if (current != null) {
                    depthList.add(current);

                    if (current.left != null) {
                        queue.offer(current.left);
                    }
                    if (current.right != null) {
                        queue.offer(current.right);
                    }
                }
            }
            if (!depthList.isEmpty()) {
                output.add(depthList);
            }
        }
        return output;
    }

    public TreeNode createBinarySearchTree(int[] sortedArray) {

        return createBinarySearchTreeHelper(sortedArray, 0, sortedArray.length - 1);
    }

    private TreeNode createBinarySearchTreeHelper(int[] array, int start, int end) {

        if (start > end) {
            return null;
        }

        int middle = (start + end) / 2;
        TreeNode middleNode = new TreeNode(array[middle]);

        middleNode.left = createBinarySearchTreeHelper(array, start, middle - 1);
        middleNode.right = createBinarySearchTreeHelper(array, middle + 1, end);

        return middleNode;
    }

    //left branch -> current node/root -> right branch
    public void inOrderTraversal(TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            printNode(node);
            inOrderTraversal(node.right);
        }
    }

    //current node/root -> left branch -> right branch
    public void preOrderTraversal(TreeNode node) {
        if (node != null) {
            printNode(node);
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    //left branch -> right branch -> current node/root
    public void postOrderTraversal(TreeNode node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            printNode(node);
        }
    }

    public void printNode(TreeNode node) {
        System.out.println(node.value);
    }
}
