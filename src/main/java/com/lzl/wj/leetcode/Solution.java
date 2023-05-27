package com.lzl.wj.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {

    }
    public String oddString(String[] words) {
        int[] dif1 = getdif(words[0]);
        int[] dif2 = getdif(words[1]);
        if (Arrays.equals(dif1, dif2)) {
            for (int i = 3; i < words.length; i++) {
                dif2 = getdif(words[i]);
                if (!Arrays.equals(dif1, dif2)) {
                    return words[i];
                }
            }
        }
        if (Arrays.equals(getdif(words[0]), getdif(words[2]))) {
            return words[1];
        }
        return words[0];
    }
    private int[] getdif(String word) {
        int[] ans = new int[word.length() - 1];
        for (int i = 1; i < word.length(); i++) {
            ans[i - 1] = word.charAt(i) - word.charAt(i - 1);
        }
        return ans;
    }
    public int countServers(int[][] grid) {
        int[] rows = new int[grid.length];
        int[] cols = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    rows[i] += 1;
                    cols[j] += 1;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    if (rows[i] > 1 || cols[j] > 1) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
    public int shortestPathBinaryMatrix(int[][] grid) {
        int[][] temp = new int[grid.length][grid.length];
        if (grid[0][0] == 1) {
            return -1;
        }
        for (int[] t : temp) {
            Arrays.fill(t, Integer.MAX_VALUE);
        }
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0,0});
        temp[0][0] = 1;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0], y = poll[1];
            if (x == grid.length && y == grid.length) {
                return temp[x][y];
            }
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (x + dx  < 0 || x + dx >= grid.length || y + dy < 0 || y + dy >= grid.length) {
                        continue;
                    }
                    if (grid[x + dx][y + dy] == 1 || temp[x + dx][y + dy] <= temp[x][y] + 1) {
                        continue;
                    }
                    temp[x + dx][y + dy] = temp[x][y] + 1;
                    queue.offer(new int[]{x + dx, y + dy});
                }
            }
        }
        return -1;
    }
    public double[] sampleStats(int[] count) {
        double[] ans = new double[5];
        int left = 0, right = count.length - 1;
        int min = -1, max = -1;
        int mode = 0, size = 0, c = 0;
        double sum = 0;
        while (left <= right) {
            if (count[left] == 0) {
                left++;
                continue;
            }
            if (count[right] == 0) {
                right--;
                continue;
            }
            if (left == right && count[left] == 1) {
                sum += left;
                c += 1;
                break;
            }
            sum += right;
            sum += left;
            c += 2;
            if (min == -1) {
                min = left;
            }
            if (count[left] > size) {
                size = count[left];
                mode = left;
            }
            if (max == -1) {
                max = right;
            }
            if (count[right] > size) {
                size = count[right];
                mode = right;
            }
            count[left]--;
            count[right]--;
        }
        double mid = 0;
        if (left == right) {
            mid = left;
        } else {
            mid = (left + right) * 1.0 / 2;
        }
        ans[0] = min;
        ans[1] = max;
        ans[2] = sum / c;
        ans[3] = mid;
        ans[4] = mode;
        return ans;
    }
}
