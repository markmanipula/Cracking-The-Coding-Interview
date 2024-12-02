package com.prep.interview.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Arrays {

    public int coinChange(int[] coins, int amount) {
        // Edge case: if the amount is 0, no coins are needed
        if (amount == 0) return 0;

        // Memoization map to store already computed results
        Map<Integer, Integer> memo = new HashMap<>();

        // DFS call
        int result = dfs(coins, amount, memo);

        // If result is Integer.MAX_VALUE, it means no solution exists
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int dfs(int[] coins, int amount, Map<Integer, Integer> memo) {
        // Base case: when the amount is 0, no coins are needed
        if (amount == 0) return 0;

        // If the amount is negative, return an impossible value
        if (amount < 0) return Integer.MAX_VALUE;

        // If the result for the current amount is already computed, return it
        if (memo.containsKey(amount)) return memo.get(amount);

        int minCoins = Integer.MAX_VALUE;

        // Explore all coins
        for (int coin : coins) {
            int res = dfs(coins, amount - coin, memo);
            if (res != Integer.MAX_VALUE) {
                minCoins = Math.min(minCoins, res + 1);
            }
        }

        // Store the result in the memoization map
        memo.put(amount, minCoins);
        return minCoins;
    }

    public int maxVisiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {

        //create an array of angles
        List<Double> angles = new ArrayList<>();
        int result = 0;

        //convert points to angle and put in list
        for (List<Integer> point : points) {
            int x = point.get(0) - location.get(0);
            int y = point.get(1) - location.get(1);

            //this means that the current location is same as the current point
            if (x == 0 && y == 0) {
                result++;
                continue;
            }

            //formula to convert points to angles
            angles.add(Math.toDegrees(Math.atan2(y, x)));
        }

        //sort array for sliding window approach
        Collections.sort(angles);
        //add 360 degrees for case when points wrap around the circle after reaching 360 degrees
        int size = angles.size();
        for(int i = 0; i < size; i++) {
            angles.add(angles.get(i) + 360);
        }

        //sliding window approach
        int start = 0;
        int count = 0;
        for (int end = 0; end < angles.size(); end++) {
            while (angles.get(end) - angles.get(start) > angle) {
                start++;
            }
            count = Math.max(count, end - start + 1);
        }

        return count + result;
    }

    public List<List<Integer>> threeSum(int[] nums) {

        //create map with key as array[i] and value as list of their indices
        Map<Integer, Set<Integer>> map = new HashMap<>();
        //output is a hashset of lists to avoid dups
        Set<List<Integer>> outputSet = new HashSet<>();

        //loop through array and add items to map
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                Set<Integer> newSet = new HashSet<>();
                newSet.add(i);
                map.put(nums[i], newSet);
            }
            Set<Integer> oldSet = map.get(nums[i]);
            oldSet.add(i);
            map.put(nums[i], oldSet);
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                int complement = sum * -1;
                //check if complement exists in map and its not i or j
                if (map.containsKey(complement)) {
                    Set<Integer> compSet = map.get(complement);
                    if (!compSet.contains(i) && !compSet.contains(j)) {
                        //sort to avoid duplicates
                        List<Integer> triplet = java.util.Arrays.asList(nums[i], nums[j], complement);
                        Collections.sort(triplet);
                        outputSet.add(triplet);
                    }
                }
            }
        }

        //edge case for when there are multiple 0s
        Set<Integer> zeroSet = map.get(0);
        if (zeroSet != null && zeroSet.size() > 2) {
            outputSet.add(List.of(0, 0, 0));
        }

        return new ArrayList<>(outputSet);
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color) return image;

        dfs(image, sr, sc, image[sr][sc], color);
        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int color, int newColor) {
        //handle edge cases
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != color) {
            //do nothing
            return;
        }
        //fill
        image[sr][sc] = newColor;
        dfs(image, sr + 1, sc, color, newColor);
        dfs(image, sr - 1, sc, color, newColor);
        dfs(image, sr, sc + 1, color, newColor);
        dfs(image, sr, sc - 1, color, newColor);
    }

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
