package org.sangyunpark.Lv3.표현가능한이진트리;

class Solution_복습01 {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for(int i = 0; i < numbers.length; i++) {
            long number = numbers[i];

            String binary = Long.toBinaryString(number);
            long nodefullcnt = getAddNode(binary.length());
            long addNodeCnt = Math.max(nodefullcnt - binary.length(), 0);

            StringBuilder sb = new StringBuilder();
            String addNode = "0".repeat((int)addNodeCnt);
            sb.append(addNode);
            sb.append(binary);
            String newTree = sb.toString();

            if(check(newTree, 0, newTree.length() - 1)) {
                answer[i] = 1;
                continue;
            }

            answer[i] = 0;
        }

        return answer;
    }

    private boolean check(String tree, int start, int end) {

        if(start == end) { // 리프 노드인 경우
            return true;
        }

        int mid = (start + end) / 2;
        char parentNode = tree.charAt(mid);
        char leftNode = tree.charAt((start + mid - 1) / 2);
        char rightNode = tree.charAt((end + mid + 1) / 2);
        // 부모 노드가 0인데, 자식 노드가 1인 경우

        if(parentNode == '0' && (leftNode == '1' || rightNode == '1')) {
            return false;
        }

        boolean left = check(tree, start, mid - 1);
        boolean right = check(tree, mid + 1, end);

        return left && right;
    }

    private long getAddNode(long number) {
        int h = 0;
        long cnt = 0;

        while(true) {
            long value = (long) (Math.pow(2,h)) - 1; // 노드 갯수

            if(value >= number) {
                cnt = value;
                break;
            }

            cnt = value;
            h++;
        }

        return cnt;
    }
}
