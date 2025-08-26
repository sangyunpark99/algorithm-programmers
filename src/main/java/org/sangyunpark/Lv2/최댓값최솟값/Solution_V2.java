package org.sangyunpark.Lv2.최댓값최솟값;

public class Solution_V2 {

    public String solution(String s) {
        String[] array = s.split(" ");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(String value : array) {
            int number = Integer.parseInt(value);
            min = Math.min(min, number);
            max = Math.max(max, number);
        }

        return min + " " + max;
    }
}
