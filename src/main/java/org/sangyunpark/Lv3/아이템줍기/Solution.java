package org.sangyunpark.Lv3.아이템줍기;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    private int[][] map;
    private int answer = Integer.MAX_VALUE;

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        map = new int[101][101];

        int type = 1;
        for(int[] value : rectangle) { // 경계선만 그리기

            int x1 = 2 * value[0];
            int y1 = 2 * value[1];
            int x2 = 2 * value[2];
            int y2 = 2 * value[3];

            for(int i = x1; i <= x2; i++) { // 위쪽
                map[y1][i] = type;
                map[y2][i] = type;
            }

            for(int i = y1; i <= y2; i++) { // 왼
                map[i][x1] = type;
                map[i][x2] = type;
            }
        }

        for(int[] value: rectangle) {
            int x1 = 2 * value[0];
            int y1 = 2 * value[1];
            int x2 = 2 * value[2];
            int y2 = 2 * value[3];

            for(int i = y1 + 1; i < y2; i++) {
                for(int j = x1 + 1; j < x2; j++) {
                    map[i][j] = 0;
                }
            }
        }

        bfs(characterY, characterX, itemY, itemX);

        return answer / 2;
    }

    private void bfs(int startY, int startX, int endY, int endX) { // 시작 지점, 끝 지점도 2배
        boolean[][] visited = new boolean[101][101];
        visited[2 * startY][2 * startX] = true;

        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{2 * startY, 2 * startX, 0});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curY = cur[0];
            int curX = cur[1];
            int cnt = cur[2];

            if(curY == 2 * endY && curX == 2 * endX) { // BFS는 최단 경로
                answer = cnt; //
                break;
            }

            for(int i = 0; i < 4; i++) {
                int ny = curY + dy[i];
                int nx = curX + dx[i];

                // 100까지
                if(ny < 0 || ny >= 101 || nx < 0 || nx >= 101 || visited[ny][nx] || map[ny][nx] == 0) continue;
                if(map[ny][nx] == 1) { // 테두리인 경우
                    visited[ny][nx] = true; // 방문 처리
                    queue.offer(new int[]{ny, nx, cnt + 1}); // 횟수 더하기
                }
            }
        }
    }
}
