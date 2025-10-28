package org.sangyunpark.Lv3.여행경로_복습;

import java.util.*;

class Solution {

    private HashMap<String, Integer> useTickets = new HashMap<>();
    private HashMap<String, List<String>> ticketMap = new HashMap<>();
    private String[] answer;
    private boolean isAnswer = false;
    private int remainingTicket = 0;

    public String[] solution(String[][] tickets) {

        Arrays.sort(tickets, (a, b) -> { // 사전 순 탐색
            if(a[0].equals(b[0])) {
                return a[1].compareTo(b[1]);
            }
            return a[0].compareTo(b[0]);
        });

        for(int i = 0; i < tickets.length; i++) {
            String[] ticket = tickets[i];
            String start = ticket[0];
            String end = ticket[1];

            if(!ticketMap.containsKey(start)) {
                ticketMap.put(start, new ArrayList<>());
            }

            if(!ticketMap.containsKey(end)) {
                ticketMap.put(end, new ArrayList<>());
            }

            String checkValue = start + "," + end;
            useTickets.put(checkValue, useTickets.getOrDefault(checkValue, 0) + 1);
            ticketMap.get(start).add(end);
            remainingTicket++;
        }

        dfs("ICN", new ArrayList<>(List.of("ICN")));

        return answer;
    }

    private void dfs(String cur, List<String> values) {

        if(isAnswer) {
            return;
        }

        if(remainingTicket == 0) {

            int valueLen = values.size();
            answer = new String[valueLen];

            for(int i = 0; i < valueLen; i++) {
                answer[i] = values.get(i);
            }

            isAnswer = true; // 제일 처음 완료된 경우가 정답
            return;
        }

        for(String next : ticketMap.getOrDefault(cur, Collections.emptyList())) {
            String checkValue = cur + "," + next;
            int remainTicket = useTickets.get(checkValue);
            if(remainTicket > 0) { // 사용하지 않은 경우
                useTickets.put(checkValue, remainTicket - 1); // 티켓 사용
                remainingTicket--; // 티켓 전역적으로 관리
                values.add(next);
                dfs(next, values); // 다음 티켓으로 이동
                values.remove(values.size() - 1); // 이동하지 않은 티켓 삭제
                remainingTicket++;
                useTickets.put(checkValue, remainTicket); // 티켓 원상 복구
            }
        }
    }
}