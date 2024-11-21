package com.prep.interview.trees;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TreeNode {

    TreeNode left;
    TreeNode right;
    int value;

    public TreeNode(int value) {
        this.value = value;
    }
}
