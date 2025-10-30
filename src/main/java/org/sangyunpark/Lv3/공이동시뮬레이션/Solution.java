package org.sangyunpark.Lv3.공이동시뮬레이션;

import java.util.*;

class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = -1;

        long left = y;
        long right = y;
        long top = x;
        long bottom = x;

        for(int i = queries.length - 1; i >= 0; i--) {
            int dir = queries[i][0];
            int dist = queries[i][1];

            if(dir == 0) {
                if(left != 0) {
                    left += dist;
                }

                right = Math.min(m - 1, right + dist);
            }else if(dir == 1) {
                if(right != m - 1) {
                    right -= dist;
                }

                left = Math.max(0, left - dist);
            }else if(dir == 2) { // 위
                if(top != 0) {
                    top += dist;
                }

                bottom = Math.min(n - 1, bottom + dist);
            }else { // 아래
                if(bottom != n - 1) {
                    bottom -= dist;
                }
                top = Math.max(0, top - dist);
            }

            if(left >= m || right < 0 || top >= n || bottom < 0) return 0;
        }

        return (right - left + 1) * (bottom - top + 1);
    }
}