package org.sangyunpark.Lv3.미로탈출명령어;

import java.util.*;

class Solution {

    private int[] dy = new int[]{1,0,0,-1};
    private int[] dx = new int[]{0,-1,1,0};
    private String[] move = new String[]{"d", "l", "r", "u"}; // 미리 사전 순 정렬

    public String solution(int n, int m, int x, int y, int r, int c, int k) {

        int dist = Math.abs(x - r) + Math.abs(y - c);

        if(dist > k) { // 최소 이동 거리
            return "impossible";
        }

        if((k - dist) % 2 == 1) { // 홀수면 불가능
            return "impossible";
        }

        return bfs(x, y, n, m, r, c, k, dist);
    }

    private String bfs(int y, int x, int n, int m, int r, int c, int k, int dist) { // 도착하려면

        boolean[][][] visited = new boolean[n][m][k + 1]; // 횟수로 관리
        visited[y - 1][x - 1][0] = true;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(y - 1, x - 1, "", 0));

        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            int cury = cur.y;
            int curx = cur.x;
            String path = cur.path;
            int cnt = cur.cnt;

            if(cnt > k) continue;

            if(cnt == k) {
                if(cury == r - 1 && curx == c - 1) {
                    return path;
                }
                continue;
            }

            for(int i = 0; i < 4; i++) {
                int ny = cury + dy[i];
                int nx = curx + dx[i];

                if(ny >= n || nx >= m || ny < 0 || nx < 0 || visited[ny][nx][cnt + 1]) continue;
                int rest = k - (cnt + 1);
                int need = Math.abs(ny - (r - 1)) + Math.abs(nx - (c - 1));
                if(need > rest) continue;
                if((rest - need) % 2 != 0) continue; // 짝수 배인 경우만 탐색
                visited[ny][nx][cnt + 1] = true;
                String newPath = new StringBuilder(path).append(move[i]).toString();
                queue.offer(new Node(ny, nx, newPath, cnt + 1));
            }
        }

        return "";
    }

    public static class Node {
        int y;
        int x;
        String path = "";
        int cnt;

        Node(int y, int x, String path, int cnt) {
            this.y = y;
            this.x = x;
            this.path = path;
            this.cnt = cnt;
        }
    }
}
