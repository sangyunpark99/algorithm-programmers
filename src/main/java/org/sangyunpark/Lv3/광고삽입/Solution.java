package org.sangyunpark.Lv3.광고삽입;

class Solution {

    private int[] diff;
    private long[] prefixTime;

    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        int play_timeValue = convertSecond(play_time);
        int adv_timeValue = convertSecond(adv_time);

        diff = new int[play_timeValue + 2];

        for(int i = 0; i < logs.length; i++) { // 1차 누적합
            String log = logs[i];
            String[] logSplit = log.split("-");
            int startTime = convertSecond(logSplit[0]);
            int endTime = convertSecond(logSplit[1]);
            diff[startTime]++;
            diff[endTime]--;
        }

        for(int i = 1; i < diff.length; i++) {
            diff[i] += diff[i - 1];
        }

        int gap = adv_timeValue;

        prefixTime = new long[play_timeValue + 2];
        prefixTime[0] = 0;

        for(int i = 1; i < diff.length; i++) {
            prefixTime[i] = prefixTime[i-1] + diff[i];
        }

        long maxValue = -1;
        int startTime = 0; // 시작 지점은 0
        int endTime = 0;

        for(int time = 0; time <= play_timeValue - adv_timeValue; time++) {
            long curGap = time == 0 ? prefixTime[time + adv_timeValue - 1] :
                    prefixTime[time + adv_timeValue - 1] - prefixTime[time - 1];
            if(curGap > maxValue) {
                maxValue = curGap;
                startTime = time;
            }
        }

        return convertTime(startTime);
    }

    private String convertTime(int value) {
        StringBuilder sb = new StringBuilder();

        int hour = value / 3600;
        int min = value % 3600 / 60;
        int sec = value % 3600 % 60;

        sb.append(String.format("%02d",hour));
        sb.append(":");
        sb.append(String.format("%02d",min));
        sb.append(":");
        sb.append(String.format("%02d",sec));

        return sb.toString();
    }

    private int convertSecond(String time) {
        String[] splitValue = time.split(":");
        int totalSeconds = 0;

        totalSeconds += Integer.parseInt(splitValue[0]) * 3600;
        totalSeconds += Integer.parseInt(splitValue[1]) * 60;
        totalSeconds += Integer.parseInt(splitValue[2]);

        return totalSeconds;
    }
}
