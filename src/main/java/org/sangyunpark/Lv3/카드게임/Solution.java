package org.sangyunpark.Lv3.카드게임;

import java.util.*;

class Solution {

    private int cardsLen;
    private final int CARD_MAX_NUMBER = 1001;
    private int round = 0;

    public int solution(int coin, int[] cards) {
        cardsLen = cards.length;
        int[] userCard = new int[CARD_MAX_NUMBER];
        int[] useCoinCard = new int[CARD_MAX_NUMBER];

        int totalCardCount = 0;
        // 1. 초기 카드 저장하기
        for(int i = 0; i < cardsLen / 3; i++) {
            userCard[cards[i]]++;
        }

        int startIndex = cardsLen / 3;
        int target = cardsLen + 1;

        while(startIndex < cardsLen) {
            // 코인 사용 안하고
            int c1 = cards[startIndex++];
            int c2 = cards[startIndex++];
            useCoinCard[c1]++;
            useCoinCard[c2]++;

            boolean canMake = false;

            for(int i = 1; i <= cardsLen; i++) { // 손에 있는 패끼리
                if(userCard[i] > 0 && userCard[target - i] > 0) {
                    userCard[i]--;
                    userCard[target-i]--;
                    canMake = true;
                    break;
                }
            }

            if(!canMake && coin > 0) {
                for(int i = 1; i <= cardsLen; i++) {
                    if(userCard[i] > 0 && useCoinCard[target - i] > 0) {
                        userCard[i]--;
                        useCoinCard[target-i]--;
                        coin--;
                        canMake = true;
                        break;
                    }
                    if(useCoinCard[i] > 0 && userCard[target - i] > 0) {
                        userCard[target - i]--;
                        useCoinCard[i]--;
                        coin--;
                        canMake = true;
                        break;
                    }
                }
            }

            if(!canMake && coin > 1) {
                for(int i = 1; i <= cardsLen; i++) {
                    if(useCoinCard[i] > 0 && useCoinCard[target - i] > 0) {
                        useCoinCard[i]--;
                        useCoinCard[target-i]--;
                        coin -= 2;
                        canMake = true;
                        break;
                    }
                }
            }

            if(canMake) {
                round++;
            }else break;
        }

        return round+1;
    }
}