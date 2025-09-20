package org.sangyunpark.Lv3.기둥과보설치;

import java.util.*;

class Solution {

    private boolean[][] pillar; // 기둥
    private boolean[][] floor; // 보
    private List<int[]> result = new ArrayList<>();

    public int[][] solution(int n, int[][] build_frame) {

        pillar = new boolean[n + 1][n + 1];
        floor = new boolean[n + 1][n + 1];

        for(int i = 0; i < build_frame.length; i++) {
            int[] order = build_frame[i];
            int x = order[0];
            int y = order[1];
            int a = order[2]; // 0(기둥), 1(보)
            int b = order[3]; // 0(삭제), 1(설치)

            if(b == 1) { // 설치
                if(a == 0) {
                    installPillar(y, x, n);
                }else {
                    installFloor(y, x, n);
                }
            }else { // 삭제
                if(a == 0) {
                    removePillar(y, x, n);
                }else {
                    removeFloor(y, x, n);
                }
            }
        }

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                if(floor[i][j]) {
                    result.add(new int[]{j, i, 1});
                }

                if(pillar[i][j]) {
                    result.add(new int[]{j, i , 0});
                }
            }
        }

        int[][] answer = new int[result.size()][3];

        for(int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        Arrays.sort(answer, (a, b) -> {
            if(a[0] == b[0]) {
                if(a[1] == b[1]) {
                    return a[2] - b[2];
                }
                return a[1] - b[1];
            }

            return a[0] - b[0];
        });

        return answer;
    }

    private void removePillar(int y, int x, int n) {
        pillar[y][x] = false; // 기둥 제거
        if(!checkRemove(n)) { // 지우면 안되는 경우
            pillar[y][x] = true; // 기둥 생성
        }
    }

    private void removeFloor(int y, int x, int n) {
        floor[y][x] = false; // 보 제거
        if(!checkRemove(n)) { // 지우면 안되는 경우
            floor[y][x] = true; // 보 생성
        }
    }

    private boolean checkRemove(int n) { // 지워도 조건에 위배되지 않는지 확인
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                if(pillar[i][j] && !checkForPillar(i, j, n)) {
                    return false;
                }
                if(floor[i][j] && !checkForFloor(i, j, n)) {
                    return false;
                }
            }
        }

        return true;
    }

    private void installPillar(int y, int x, int n) {
        if(checkForPillar(y, x, n)) {
            pillar[y][x] = true;
            return;
        }
    }

    private boolean checkForPillar(int y, int x, int n) {

        if(y == 0 || // 바닥 위에 설치
                floor[y][x] || // 바닥 위
                (x - 1 >= 0 && floor[y][x-1]) || // 바닥 오른쪽 위
                (y - 1 >= 0 && pillar[y-1][x]) // 기둥 위
        ) return true;

        return false;
    }

    private void installFloor(int y, int x, int n) {
        if(checkForFloor(y, x, n)) {
            floor[y][x] = true;
            return;
        }
    }

    private boolean checkForFloor(int y, int x, int n) {

        if(x < 0 || x > n) return false;

        if(
                (y - 1 >= 0 && pillar[y - 1][x]) || // 보 왼쪽 아래 기둥
                        (y - 1 >= 0 && x + 1 <= n && pillar[y - 1][x + 1]) || // 보 오른쪽 아래 기둥
                        (x - 1 >= 0 && x + 1 <= n && floor[y][x - 1] && floor[y][x + 1]) // 양 옆에 보
        ) return true;

        return false;
    }
}