package org.sangyunpark.Lv3.주사위고르기;

import java.util.*;

class Solution {

    private int diceLen = 0;
    private HashMap<Integer, int[]> diceSet = new HashMap<>();
    private int winCnt = Integer.MIN_VALUE;
    private int[] ans;

    public int[] solution(int[][] dice) {

        diceLen = dice.length;

        for(int i = 0; i < dice.length; i++) {
            Arrays.sort(dice[i]);
            diceSet.put(i, dice[i]);
        }

        searchDice(diceLen / 2, new ArrayList<>(), 0, 0); //

        System.out.println(Arrays.toString(ans));

        return ans;
    }

    // 어떤 주사위를 선택할 건데?
    private void searchDice(int r, List<Integer> dices, int depth, int cur) {
        if(depth == r) { // 주사위 선택하기
            int size = dices.size();

            // 선택한 주사위에서 나올 수 있는 합 구하기
            List<Integer> selectedSums = new ArrayList<>();
            getSum(size, new ArrayList<>(), 0, dices, selectedSums);
            Collections.sort(selectedSums);

            // 나머지 주사위에서 나올 수 있는 합 구하기
            List<Integer> notSelectedDices = new ArrayList<>();
            for(int i = 0; i < diceLen; i++) {
                if(!dices.contains(i)) {
                    notSelectedDices.add(i);
                }
            }
            List<Integer> notSelectedSums = new ArrayList<>();
            getSum(size, new ArrayList<>(), 0, notSelectedDices, notSelectedSums);

            Collections.sort(notSelectedSums);

            int curWinCnt = 0;

            for(int i = 0; i < selectedSums.size(); i++) {
                curWinCnt += getSmallIdxCnt(selectedSums.get(i), notSelectedSums); // 크기 기준
            }

            if(curWinCnt > winCnt) { // 승리 횟수가 더 많은 경우
                winCnt = curWinCnt;
                ans = dices.stream().mapToInt(i -> i + 1).toArray();
            }

            return;
        }

        for(int i = cur; i < diceLen; i++) {
            dices.add(i);
            searchDice(r, dices, depth + 1, i + 1);
            dices.remove(dices.size() - 1);
        }
    }

    private int getSmallIdxCnt(int value, List<Integer> notSelectedSums) { // 이분 탐색으로 합계 구하기

        int left = 0;
        int right = notSelectedSums.size() - 1;

        while(left <= right) {
            int mid = (left + right) / 2;
            if(value > notSelectedSums.get(mid)) {
                left = mid + 1;
            }else { // 크거나 같은 경우
                right = mid - 1;
            }
        }

        return left;
    }

    // 선택한 주사위로 가능한 모든 합 구하기
    private void getSum(int r, List<Integer> combi, int depth, List<Integer> dices, List<Integer> sums) {

        if(depth == r) {
            int sum = 0;
            int j = 0;

            for(int i = 0; i < combi.size(); i++) {
                int value = diceSet.get(dices.get(j++))[combi.get(i)];
                sum += value;
            }

            sums.add(sum);

            return;
        }

        for(int i = 0; i < 6; i++) {
            combi.add(i);
            getSum(r, combi, depth + 1, dices, sums);
            combi.remove(combi.size() - 1);
        }
    }
}
