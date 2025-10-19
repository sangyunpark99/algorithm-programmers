package org.sangyunpark;

public class Main {

    private static int[][] matrix;
    private static final int N = 10;
    private static boolean[] visited;

    public static void main(String[] args) {
        matrix = new int[N][N]; // 0 ~ 9번

        // 무방향 간선 = 양방향 간선
        matrix[1][2] = 1; matrix[2][1] = 1;
        matrix[1][3] = 1; matrix[3][1] = 1;
        matrix[3][4] = 1; matrix[4][3] = 1;

        visited = new boolean[N];

        for(int i = 0; i <= N - 1; i++) {
            if(!visited[i]) {
                dfs(i);
                System.out.println();
            }
        }
    }

    private static void dfs(int cur) {
        visited[cur] = true;
        System.out.print(cur + " ");
        for(int i = 0; i < N; i++) {
            if(matrix[cur][i] == 1 && !visited[i]) {
                visited[i] = true;
                dfs(i);
            }
        }
    }
}