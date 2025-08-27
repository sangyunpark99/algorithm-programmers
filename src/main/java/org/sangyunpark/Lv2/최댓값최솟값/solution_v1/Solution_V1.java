package org.sangyunpark.Lv2.최댓값최솟값.solution_v1;

import java.util.*;

public class Solution_V1 {

    public String solution(String s) {
        StringBuilder sb = new StringBuilder();

        int[] array = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray(); // 문자열 int[] 배열로 변환

        Arrays.sort(array); // 변환환 배열을 정렬
        sb.append(array[0]).append(" "); // 최솟값과 띄워쓰기 문자를 StringBuilder에 추가
        sb.append(array[array.length - 1]); // 최댓값을 StringBuilder에 추가

        return sb.toString(); // 정답 출력
    }
}
