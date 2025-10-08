import java.util.*;
class Solution {
    Map<Integer, Integer> in = new HashMap<>(); // 나한테 들어오는 간선
    Map<Integer, Integer> out = new HashMap<>(); // 내가 보내는 간선
    public int[] solution(int[][] edges) {
        for(int[] e : edges) {
            out.put(e[0], out.getOrDefault(e[0], 0) + 1);
            in.put(e[1], in.getOrDefault(e[1], 0) + 1);
        }
        
        int[] answer = new int[4];
        for(int k : out.keySet()) {
            if(out.get(k) > 1) {
                if(!in.containsKey(k)) {
                    answer[0] = k;
                }else {
                    answer[3]++; // 8자
                }
            }
        }
        
        for(int k : in.keySet()) {
            if(!out.containsKey(k)) {
                answer[2]++;
            }
        }
        
        answer[1] = out.get(answer[0]) - answer[2] - answer[3];
        return answer;
    }
}