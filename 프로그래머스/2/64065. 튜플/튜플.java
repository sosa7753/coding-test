import java.util.*;
class Solution {
    public int[] solution(String s) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (y[1] - x[1]));
        
        Map<String, Integer> map = new HashMap<>();
        
        int start = 0;
        int end = 0;
        for(int i=0; i<s.length(); i++) {
            if(!Character.isDigit(s.charAt(i))) {
                if(start != end) {
                    String str = s.substring(start, end);
                    map.put(str, map.getOrDefault(str, 0) + 1);
                }
                end++;
                start = end;
                continue;
            }
            
            end++;            
        }
        
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(new int[] {Integer.parseInt(entry.getKey()), entry.getValue()});
        }
        
        int[] answer = new int[pq.size()];
        int idx = 0;
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            answer[idx++] = now[0];
        }
     
        return answer;
    }
}