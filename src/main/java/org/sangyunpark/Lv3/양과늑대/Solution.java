package org.sangyunpark.Lv3.양과늑대;
import java.util.*;


public class Solution {

    private List<List<Integer>> graph = new ArrayList<>();
    private int[] Info;
    private int answer = 1;

    public int solution(int[] info, int[][] edges) {

        Info = info.clone();

        if(info.length == 2)    {
            if(info[1] == 0) {
                return 2;
            }

            return 1;
        }

        for(int i = 0; i < info.length; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int parent = edge[0];
            int child = edge[1];

            graph.get(parent).add(child);
        }

        dfs(0, 1, 0, new ArrayList<>(List.of(graph.get(0).get(0), graph.get(0).get(1))));

        return answer;
    }

    private void dfs(int cur, int sheep, int wolf, List<Integer> candidates) {

        answer = Math.max(answer, sheep);
        System.out.println("number = " + cur);
        System.out.println("sheep = " + sheep);
        System.out.println("wolf = " + wolf);
        System.out.println(candidates);
        System.out.println();

        for(int i = 0; i < candidates.size(); i++) {
            int next = candidates.get(i);

            int nextSheep = sheep;
            int nextWolf = wolf;

            if(Info[next] == 0) {
                nextSheep++;
            }

            if(Info[next] == 1) {
                if(sheep <= wolf + 1) {
                    continue;
                }
                nextWolf++;
            }

            List<Integer> newCandidates = new ArrayList<>(candidates);
            newCandidates.remove(Integer.valueOf(next));
            for(int j = 0; j < graph.get(next).size(); j++) {
                newCandidates.add(graph.get(next).get(j));
            }

            dfs(next, nextSheep, nextWolf, newCandidates);
        }
    }
}
