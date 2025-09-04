package org.sangyunpark.Lv3.보석쇼핑;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Solution {

  private HashMap<String, Integer> map = new HashMap<>();
  private HashSet<String> kinds = new HashSet<>();
  private List<int[]> answers = new ArrayList<>();

  public int[] solution(String[] gems) {

    int left = 0;
    int right = 0;
    int bestLeft = 0;
    int bestRight = gems.length - 1;
    int bestGap = Integer.MAX_VALUE;

    // 상품
    for(String gem: gems) {
      kinds.add(gem);
    }

    while(left <= right && right < gems.length) { // O(n)

      map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
      right++;

      while(map.size() == kinds.size()) {

        int curGap = right - left;
        if(curGap < bestGap) {
          bestGap = curGap;
          bestLeft = left;
          bestRight = right - 1;
        }else if(curGap == bestGap) {
          if(bestLeft > left) { // 현재 left가 더 작은 경우
            bestLeft = left;
            bestRight = right - 1;
          }
        }

        String value = gems[left];
        // 제거 하고 범위 줄이기
        if(map.get(value) == 1) {
          map.remove(value);
        }else {
          map.put(value, map.get(value) - 1);
        }

        left++;
      }
    }

    return new int[]{bestLeft + 1, bestRight + 1};
  }
}
