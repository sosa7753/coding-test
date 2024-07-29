import java.util.*;
class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        String[] chart = {"code", "date", "maximum", "remain"};
        
        Map<String, Integer> map = new HashMap<>();        
        for(int i=0; i<4; i++) {
            map.put(chart[i], i);
        }
        
        int check = map.get(sort_by);      
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (x,y) -> (x[check] - y[check]));
        
        for(int i=0; i<data.length; i++) {
            if(val_ext > data[i][map.get(ext)]) {
                pq.offer(data[i]);
            }
        }
        
        int[][] answer = new int[pq.size()][4]; 
        int idx = 0;
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            
            for(int i=0; i<4; i++) {
                answer[idx][i] = now[i];
            }
            idx++;
        }
            
        return answer;
    }
}