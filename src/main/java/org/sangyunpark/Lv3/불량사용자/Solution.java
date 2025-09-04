package org.sangyunpark.Lv3.불량사용자;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
  private List<List<String>> values = new ArrayList<>();
  private int bannedSize = 0;
  private Set<String> answer = new HashSet<>();

  public int solution(String[] user_id, String[] banned_id) {

    bannedSize = banned_id.length;

    for(int i = 0; i < banned_id.length; i++) { // O(8)
      values.add(new ArrayList<>());

      for(int j = 0; j < user_id.length; j++) { // O(8)
        if(check(banned_id[i], user_id[j])) { // 불량 사용자라면
          values.get(i).add(user_id[j]);
        }
      }
    }

    combi(0, new HashSet<>());

    return answer.size();
  }

  private void combi(int depth, Set<String> check) {
    if(depth == bannedSize) {
      List<String> checkList = new ArrayList<>(check);
      Collections.sort(checkList); // 정렬 해서 문자열을 만드는게 핵심
      StringBuilder joinValue = new StringBuilder();
      for(int i = 0; i < checkList.size(); i++) {
        String value = checkList.get(i);
        joinValue.append(value);
      }
      answer.add(joinValue.toString());
      return;
    }

    for(int i = 0; i < values.get(depth).size(); i++) {
      String value = values.get(depth).get(i);
      if(check.contains(value)) continue;
      check.add(value);
      combi(depth + 1, check);
      check.remove(value);
    }
  }

  private boolean check(String banned, String user) { // O(64)

    char[] bannedList = banned.toCharArray();
    char[] userList = user.toCharArray();

    if(bannedList.length != userList.length) return false;

    for(int i = 0; i < bannedList.length; i++) {
      if(bannedList[i] == '*') continue;
      if(bannedList[i] != userList[i]) { // 일치하지 않는 경우
        return false;
      }
    }

    return true;
  }

}
