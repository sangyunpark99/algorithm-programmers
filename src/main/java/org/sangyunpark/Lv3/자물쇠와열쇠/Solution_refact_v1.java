package org.sangyunpark.Lv3.자물쇠와열쇠;

import java.util.Arrays;

public class Solution_refact_v1 {
  private int n;
  private int holeCnt; // 구멍 갯수
  private int[][] board;

  public boolean solution(int[][] key, int[][] lock) {
    boolean answer = true;

    board = new int[lock.length * 3][lock.length * 3]; // key보다 lock이 더 크다

    for(int i = 0; i < board.length; i++) {
      Arrays.fill(board[i], 100);
    }

    for(int i = 0; i < lock.length; i++) {
      for(int j = 0; j < lock.length; j++) {
        board[i + key.length][j + key.length] = lock[i][j]; // 자물쇠 위치 옮기기
        if(lock[i][j] == 0) {
          holeCnt++;
        }
      }
    }

    for(int i = 0; i < 4; i++) {
      if(check(key, lock)) {
        return true;
      }
      key = rotate(key);
    }

    return false;
  }

  private int[][] rotate(int[][] key) { // 오른쪽으로 90도 회전
    int n = key.length;
    int[][] rotatedKey = new int[n][n];

    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        rotatedKey[i][j] = key[n-1-j][i];
      }
    }

    return rotatedKey;
  }

  private boolean check(int[][] key, int[][] lock) {
    int n = key.length;
    int H = board.length;

    for(int i = 0; i <= H-n; i++) { // 더해질 좌표
      for(int j = 0; j <= H-n; j++) { // 더해질 좌표
        int cnt = 0;
        boolean ok = true;
        for(int k = 0; k < key.length; k++) { // 키의 위치
          for(int l = 0; l < key.length; l++) {
            if(key[k][l] == 0) continue;
            int value = board[k + i][l + j];

            if(value == 1) {
              ok = false;
              break;
            }

            if(value == 0) {
              cnt++;
            }
          }
          if(!ok) break;
        }

        if(ok && cnt == holeCnt) {
          return true;
        }
      }
    }

    return false;
  }
}
