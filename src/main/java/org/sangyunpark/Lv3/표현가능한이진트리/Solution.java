package org.sangyunpark.Lv3.표현가능한이진트리;
import java.util.*;

class Solution {

    private int h;

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for(int i = 0; i < numbers.length; i++) {
            String bin = Long.toBinaryString(numbers[i]);
            int m = getTreeMaxSize(bin.length());
            String addedZero = "0".repeat(m - bin.length()) + bin;
            answer[i] = isValid(addedZero, 0, addedZero.length() - 1) ? 1 : 0;
        }

        return answer;
    }

    private boolean isValid(String s, int l, int r) {

        if(l >= r) { // 리프 노드
            return true;
        }

        int mid = (l + r) / 2;
        int lChild = (l + mid - 1) / 2;
        int rChild = (mid + 1 + r) / 2;

        if(s.charAt(mid) == '0' && (s.charAt(lChild) == '1' || s.charAt(rChild) == '1')) {
            return false;
        }

        return isValid(s, l, mid - 1) && isValid(s, mid + 1, r);
    }

    private int getTreeMaxSize(int length) {
        int h = 1;
        int m = 1;
        while(m < length) {
            h++;
            m = (1 << h) - 1;
        }

        return m;
    }
}