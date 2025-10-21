package org.sangyunpark.Lv3.블록이동하기;

import java.util.*;

class Solution {

    private int[] dy = {-1,0,1,0};
    private int[] dx = {0,1,0,-1};
    private boolean[][][][] visited;
    private int n;

    public int solution(int[][] board) {
        int answer = 0;

        n = board.length;

        visited = new boolean[n][n][n][n];
        Queue<Robot> queue = new ArrayDeque<>();
        queue.add(new Robot(0,0,0,1,0));
        visited[0][0][0][1] = true;

        while(!queue.isEmpty()) {
            Robot curRobot = queue.poll();

            int y1 = curRobot.y1;
            int x1 = curRobot.x1;
            int y2 = curRobot.y2;
            int x2 = curRobot.x2;
            int time = curRobot.time;

            if((y1 == n - 1 && x1 == n - 1) || (y2 == n - 1 && x2 == n - 1)) { // 가장 빠르게 도착
                return time;
            }

            for(int i = 0; i < 4; i++) { // 상하좌우 이동
                int ny1 = y1 + dy[i];
                int nx1 = x1 + dx[i];
                int ny2 = y2 + dy[i];
                int nx2 = x2 + dx[i];

                if(isValid(ny1, nx1, ny2, nx2, board) && !visited[ny1][nx1][ny2][nx2]) {
                    visited[ny1][nx1][ny2][nx2] = true;
                    queue.offer(new Robot(ny1, nx1, ny2, nx2, time + 1));
                }
            }

            if(y1 == y2) { // 가로 모양
                for(int d = -1; d <= 1; d += 2) { // 위로 회전, 아래로 회전
                    if(isValid(y1 + d, x1, y2 + d, x2, board)) {
                        if(!visited[y1][x1][y1 + d][x1]) { // y1 피봇
                            visited[y1][x1][y1 + d][x1] = true;
                            queue.offer(new Robot(y1, x1, y1 + d, x1, time + 1));
                        }

                        if(!visited[y2 + d][x2][y2][x2]) {
                            visited[y2 + d][x2][y2][x2] = true;
                            queue.offer(new Robot(y2 + d, x2, y2, x2, time + 1));
                        }
                    }
                }
            }

            if(x1 == x2) { // 세로 모양
                for(int d = -1; d <= 1; d += 2) { // 왼쪽 회전, 오른쪽 회전
                    if(isValid(y1, x1 + d, y2, x2 + d, board)) { // y1,x1 피봇
                        if(!visited[y1][x1][y1][x1 + d]) {
                            visited[y1][x1][y1][x1 + d] = true;
                            queue.offer(new Robot(y1, x1, y1, x1 + d, time + 1));
                        }

                        if(!visited[y2][x2 + d][y2][x2]) {
                            visited[y2][x2 + d][y2][x2] = true;
                            queue.offer(new Robot(y2, x2 + d, y2, x2, time + 1));
                        }
                    }
                }
            }
        }

        return -1;
    }

    private boolean isValid(int ny1, int nx1, int ny2, int nx2, int[][] map) {
        return ny1 >= 0 && ny1 < n && nx1 >= 0 && nx1 < n && ny2 >= 0 && ny2 < n && nx2 >= 0 && nx2 < n
                && map[ny1][nx1] == 0 && map[ny2][nx2] == 0;
    }

    public class Robot {

        int y1;
        int x1;
        int y2;
        int x2;
        int time;

        public Robot(int y1, int x1, int y2, int x2, int time) {
            this.y1 = y1;
            this.x1 = x1;
            this.y2 = y2;
            this.x2 = x2;
            this.time = time;
        }
    }
}
