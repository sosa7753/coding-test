import java.util.*;
class Solution {
    public int[] solution(int[][] edges) {
        Map<Integer, Integer> out = new HashMap<>();
        Map<Integer, Integer> in = new HashMap<>();
        int[] answer = new int[4];
        
        // 간선 정리 
        for(int i=0; i<edges.length; i++) {
            out.put(edges[i][0], out.getOrDefault(edges[i][0], 0) + 1);
            in.put(edges[i][1], in.getOrDefault(edges[i][1], 0) + 1);
        }
        
        // 정점 찾기
        for(int value : out.keySet()) {
            if(out.get(value) > 1) {
                if(!in.containsKey(value)) {
                    answer[0] = value; // 만족하면 정점.
                }else {
                    answer[3]++; // 만족하지 않으면 8자 그래프
                }
            }
        }
        
        // 막대 그래프
        for(int value : in.keySet()) {
            if(!out.containsKey(value)) { // 나가는게 없다면 막대 그래프 
                answer[2]++;
            }   
        }
        
        // 도넛 그래프 개수 = 정점에서의 간선 수 - 막대 수 - 8자 수 
        answer[1] = out.get(answer[0]) - answer[2] - answer[3];
        return answer;
    }
}