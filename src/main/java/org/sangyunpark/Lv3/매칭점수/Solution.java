package org.sangyunpark.Lv3.매칭점수;

import java.util.*;
import java.util.regex.*;

class Solution {

    private HashMap<String, Node> nodes = new HashMap<>();

    public static class Node {

        int idx;
        String curUrl;
        int basicScore;
        int outerLinkCnt; // 현재 페이지에서 걸린 외부 링크
        double linkScore;
        double matchScore;
        List<Node> linkScoreNode = new ArrayList<>();

        public Node(int idx, String curUrl) {
            this.idx = idx;
            this.curUrl = curUrl;
        }

        public void print() {
            System.out.printf(
                    "idx=%d, curUrl=%s, basicScore=%d, outerLinkCnt=%d, linkScore=%.2f, matchScore=%.2f%n",
                    idx, curUrl, basicScore, outerLinkCnt, linkScore, matchScore
            );
        }
    }

    public int solution(String word, String[] pages) {
        int answer = 0;

        // 1. 현재 페이지 추출해서 pages에 값 넣기
        int idx = 0;
        for(String page: pages) {
            // meta tag 추출 어떻게? 정규 표현식
            page = page.toLowerCase();
            String curPageUrl = findCurUrl("<meta(.*?)/>", page,"content=",1);
            if(!nodes.containsKey(curPageUrl)) {
                nodes.put(curPageUrl, new Node(idx++, curPageUrl));
            }
        }

        for(String page: pages) {
            // 1. 현재 페이지 추출
            //String value = findTagContent
            page = page.toLowerCase();
            String curPageUrl = findCurUrl("<meta(.*?)/>", page,"content=",1);

            // 현재 페이지로, Node 추출
            Node cur = nodes.get(curPageUrl);

            // 기본 점수 계산
            int searchCnt = countSearch("(?<![A-Za-z])" + word.toLowerCase() + "(?![A-Za-z])", page);
            cur.basicScore = searchCnt;

            // 외부 링크 점수 계산
            List<String> outerLinkPages = findOuterLink("<a href=(.*?)>", page);
            cur.outerLinkCnt = outerLinkPages.size();

            // 연결된 외부 링크의 페이지에 현재 페이지 항목 추가하기
            for(int i = 0; i < outerLinkPages.size(); i++) {
                String url = outerLinkPages.get(i);
                if(nodes.containsKey(url)) {
                    Node outerLinkNode = nodes.get(url);
                    outerLinkNode.linkScoreNode.add(cur);
                }
            }

        }

        // 링크 점수 계산 하기
        List<Map.Entry<String,Node>> pageNodes = new ArrayList<>(nodes.entrySet());

        for(int i = 0; i < pageNodes.size(); i++) {
            String key = pageNodes.get(i).getKey();
            Node cur = nodes.get(key);
            List<Node> linkNodes = cur.linkScoreNode;
            for(int j = 0; j < linkNodes.size(); j++) {
                String linkUrl = linkNodes.get(j).curUrl;
                Node linkNode = nodes.get(linkUrl);
                if(linkNode.outerLinkCnt > 0) {
                    cur.linkScore += (double)linkNode.basicScore / linkNode.outerLinkCnt;
                }
            }
        }

        // 매칭 점수 계산 하기
        for(int i = 0; i < pageNodes.size(); i++) {
            String key = pageNodes.get(i).getKey();
            Node cur = nodes.get(key);
            cur.matchScore = cur.basicScore + cur.linkScore;
        }

        // 점수, index순으로 정렬
        Collections.sort(pageNodes,(a,b) -> {

            Node aNode = a.getValue();
            Node bNode = b.getValue();

            // 비교시 Double.compare
            int cmp = Double.compare(bNode.matchScore, aNode.matchScore); // Double비교시, Double.compare
            if(cmp != 0) return cmp;
            return aNode.idx - bNode.idx;
        });

        return pageNodes.get(0).getValue().idx;
    }

    private List<String> findOuterLink(String regex, String page) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(page);
        List<String> outerLinkPages = new ArrayList<>();
        while(matcher.find()) {
            outerLinkPages.add(matcher.group(1));
        }

        return outerLinkPages;
    }

    private int countSearch(String regex, String page) {
        int cnt = 0;

        Pattern bodyPattern = Pattern.compile("<body>([\\s\\S]*?</body>)"); // body Pattern
        Matcher bodyMatcher = bodyPattern.matcher(page);

        String bodyPage = "";
        if(bodyMatcher.find()) {
            bodyPage = bodyMatcher.group(1);
        } else {
            bodyPage = page;
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(bodyPage);

        while(matcher.find()) {
            cnt++;
        }

        return cnt;
    }

    private String findCurUrl(String regex, String page, String splitValue,int idx) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(page);

        String value = "";

        while(matcher.find()) {
            value = matcher.group(1);
        }

        String[] res = value.split(splitValue);

        return res[idx];
    }
}
