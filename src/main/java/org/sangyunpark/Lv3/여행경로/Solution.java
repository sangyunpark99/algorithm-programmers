package org.sangyunpark.Lv3.여행경로;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Solution {
  private HashMap<String, Integer> visited = new HashMap<>(); // 중복이 될 수 있으므로, 횟수로
  private HashMap<String, List<String>> path = new HashMap<>();
  private List<String> paths = new ArrayList<>();
  private int n;

  public String[] solution(String[][] tickets) {
    String[] answer = {};

    n = tickets.length; // 티켓 갯수

    for(int i = 0; i < tickets.length; i++) {
      String[] ticket = tickets[i];
      String from = ticket[0];
      String to = ticket[1];

      if(!path.containsKey(from)) {
        path.put(from, new ArrayList<>());
      }
      path.get(from).add(to); // 경로 추가
      visited.put(from + to, visited.getOrDefault(from + to, 0) + 1);
    }

    dfs("ICN","ICN");
    Collections.sort(paths); // O(nlogn)

    return paths.get(0).split(" ");
  }

  private void dfs(String start, String check) { // O(V + E)

    if(check.split(" ").length == n + 1) { // 경로 추가
      paths.add(check);
      return;
    }

    // ICN이 맨 마지막에 존재하는 경우, 이 로직을 먼저 사용하면 빠른 return이 된다.
    if(!path.containsKey(start)) return; // 마지막 도착 원소일 경우, 키가 존재하지 않을 수 있다.

    for(int i = 0; i < path.get(start).size(); i++) {
      String next = path.get(start).get(i);
      String ticket = start + next;
      int left = visited.getOrDefault(ticket, 0);

      if(left <= 0) continue;
      visited.put(ticket, left - 1);
      dfs(next, check + " " + next);
      visited.put(ticket, left);
    }
  }
}
