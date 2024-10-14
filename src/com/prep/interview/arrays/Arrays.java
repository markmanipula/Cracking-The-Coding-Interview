package com.prep.interview.arrays;

public class Arrays {

    public int findPeakElement(int[] array) {

        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int middle = left + (right - left) / 2;

            //move right
            if (array[middle] < array[middle + 1]) {
                left = middle + 1;
            } else {
                right = middle -1;
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
