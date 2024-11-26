package com.prep.interview.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree {

    TreeNode root;

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> outputList = new ArrayList<>();
        if (root == null) return outputList;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int level = queue.size();
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < level; i++) {
                TreeNode current = queue.poll();
                list.add(current.value);
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            outputList.add(list);
        }
        return outputList;
    }

    public TreeNode lowestCommonAncestorBST(TreeNode p, TreeNode q) {
        //makes sure that node p is always LESS than node q
        if (p.value > q.value) {
            TreeNode temp = q;
            q = p;
            p = temp;
        }

        while (root != null) {

            if (root.value > p.value && root.value > q.value) {
                root = root.left;
            } else if (root.value < p.value && root.value < q.value) {
                root = root.right;
            } else {
                return root;
            }
        }

        return null;
    }

    public TreeNode successor(TreeNode node) {
        if (root == null || node == null) return null;

        if (node.right != null) {
            //go to left most subtree
            TreeNode runner = node.right;
            while (runner.left != null) {
                runner = runner.left;
            }
            return runner;
        }

        TreeNode current = root;
        TreeNode successor = null;
        while (current != null) {
            if (node.value < current.value) {
                successor = current; //potential successor
                current = current.left;
            } else if (node.value > current.value) {
                current = current.right;
            } else {
                break;
            }
        }
        return successor;
    }

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
