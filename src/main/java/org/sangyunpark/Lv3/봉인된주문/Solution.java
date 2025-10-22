package org.sangyunpark.Lv3.봉인된주문;

import java.util.*;

class Solution {
    // 알파벳 소문자 97 ~ 122
    private long[] bansNumber;

    public String solution(long n, String[] bans) {

        bansNumber = new long[bans.length];

        for(int i = 0; i < bans.length; i++) {
            bansNumber[i] = getNumber(bans[i]);
        }

        Arrays.sort(bansNumber);

        for(long ban : bansNumber) {
            if(n >= ban) {
                n++;
            }else {
                break;
            }
        }

        return getValue(n);
    }

    private String getValue(long n) { // n: 1-based
        int len = 1;
        while (true) {
            long block = pow26(len);
            if (n > block) {
                n -= block;
                len++;
            } else break;
        }

        n--; // 0-based

        StringBuilder sb = new StringBuilder(len);
        for (int i = len - 1; i >= 0; i--) { // 자릿수 만큼
            long div = pow26(i);
            int d = (int)(n / div); // 몫 구하고
            n %= div; // 다음 자릿수 이동
            sb.append((char)('a' + d));
        }
        return sb.toString();
    }

    private long pow26(int e) {
        long r = 1;
        for (int i = 0; i < e; i++) r *= 26L;
        return r;
    }

    private long getNumber(String ban) {
        int n = ban.length();
        long offset = 0L;

        for(int k = 1; k < n; k++) {
            offset += (long) Math.pow(26, k);
        }

        char[] arr = ban.toCharArray();
        long value = 0;
        for(int i = 0; i < arr.length; i++) {
            value += (arr[i] - 'a') * (long) Math.pow(26, n - i - 1);
        }

        return offset + value + 1; // 1-based
    }
}
