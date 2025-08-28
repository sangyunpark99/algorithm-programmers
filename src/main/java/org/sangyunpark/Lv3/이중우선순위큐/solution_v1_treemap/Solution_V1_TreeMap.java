package org.sangyunpark.Lv3.이중우선순위큐.solution_v1_treemap;

import java.util.*;

public class Solution_V1_TreeMap {
    public int[] solution(String[] operations) {
        int[] answer = {};

        TreeMap<Integer, Integer> map = new TreeMap<>(); // 오름차순으로 자동 정렬

        for(String operation: operations) {
            String[] value = operation.split(" ");
            int second = Integer.parseInt(value[1]);

            if("I".equals(value[0])) {
                map.put(second, 0);
            } else {
                if(map.size() == 0) continue;
                if(second == 1) { // 최댓값
                    map.remove(map.lastKey());
                }else if(second == -1) { // 최솟값
                    map.remove(map.firstKey());
                }
            }
        }

        if(map.isEmpty()) {
            return new int[]{0,0};
        }

        return new int[]{map.lastKey(), map.firstKey()};
    }
}
