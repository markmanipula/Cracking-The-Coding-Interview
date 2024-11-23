package com.prep.interview.arrays;

import java.util.ArrayList;
import java.util.List;

public class Arrays {

    //binary search
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] == target) return middle;

            if (target < nums[middle]) {
                //go left
                right = middle - 1;
            } else {
                //go right
                left = middle + 1;
            }
        }
        return -1;
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int left = 0;
        int right = 1;
        int max = 0;

        while (right < prices.length) {
            if (prices[left] > prices[right]) {
                left = right;
            } else {
                max = Math.max(max, prices[right] - prices[left]);
            }
            right++;
        }
        return max;
    }

    public int minDistance(final int[] array,
                           final int item1,
                           final int item2) {
        int min = Integer.MAX_VALUE;
        int current = 0;
        int currentsIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item1 || array[i] == item2) {
                if (current != array[i]) {
                    min = Math.min(min, i - currentsIndex);
                }
                current = array[i];
                currentsIndex = i;
            }
            if (min == 1) return min;
        }
        return min;
    }

    public boolean meetingRooms(List<int[]> meetings) {
        meetings.sort((a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 0; i < meetings.size() - 1; i++) {
            if (meetings.get(i)[1] > meetings.get(i + 1)[0]) {
                return false;
            }
        }
        return true;
    }

    public int largestRectangle(int[] array) {
        int max = 0;

        for (int i = 0; i < array.length; i++) {
            int length = 1;

            int leftRunner = i;
            while (leftRunner > 0) {
                if (array[i] <= array[leftRunner - 1]) {
                    length++;
                    leftRunner--;
                } else {
                    break;
                }
            }

            int rightRunner = i;
            while (rightRunner < array.length - 1) {
                if (array[i] <= array[rightRunner + 1]) {
                    length++;
                    rightRunner++;
                } else {
                    break;
                }
            }

            int area = array[i] * length;
            if (area > max) {
                max = area;
            }
        }
        return max;

    }

    public int findDistinctInSortedArray(int[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {

            int middle = left + (right - left) / 2;

            if ((middle == 0 || array[middle] != array[middle - 1]) && (middle == array.length - 1 || array[middle] != array[middle + 1])) {
                return array[middle];
            }

            //go right
            if (array[middle] == array[middle - 1]) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }

        }

        return array[left];
    }

    public int maxLoss(int[] array) {

        int maxPrice = 0;
        int maxDifference = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxPrice) {
                maxPrice = array[i];
            }

            else if (maxPrice - array[i] > maxDifference) {
                maxDifference = maxPrice - array[i];
            }
        }

        return maxDifference;

    }

    public int findPeakElement(int[] array) {

        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int middle = left + (right - left) / 2;

            //move right
            if (array[middle] < array[middle + 1]) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return left;
    }

    public int[][] zeroMatrix(int[][] matrix) {

        boolean[] row = new boolean[matrix.length];

        //calculate the longest row length
        int maxColumn = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length > maxColumn) {
                maxColumn = matrix[i].length;
            }
        }
        boolean[] column = new boolean[maxColumn];

        //check for zeros
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }

        //zero out the column
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (column[j]) {
                    matrix[i][j] = 0;
                }
            }
        }

        //zero out the rows
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (row[i]) {
                    matrix[i][j] = 0;
                }
            }
        }

        return matrix;

    }

    public int[][] rotate(int[][] matrix) {

        int rowLength = matrix[0].length - 1;

        int[][] newMatrix = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newMatrix[i][j] = matrix[rowLength][i];
                rowLength--;
            }
            rowLength = matrix[0].length - 1;
        }
        return newMatrix;
    }

    public int[][] transpose(int[][] matrix) {

        int newRow = matrix[0].length;
        int newColumn = matrix.length;
        int[][] newMatrix = new int[newRow][newColumn];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newMatrix[j][i] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    public void print2dArray(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
