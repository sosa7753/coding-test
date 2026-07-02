import java.util.*;
class Solution {
    int max = 101;
    Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
    public int solution(int[][] points, int[][] routes) {    
        map.put(0, new HashMap<>());
        for(int[] route : routes) { // 각 루트 경로에 대해 
            int idx = 0;
            insert(points[route[0]-1][0], points[route[0]-1][1], idx);
            for(int i=0; i<route.length-1; i++) { // i와 i+1번째 경로     
                int nextIdx = move(route[i], route[i+1], points, idx);                           idx = nextIdx;
            }
        }
        
        int answer = 0;
        for(Map<Integer, Integer> m : map.values()) {
            for(int v : m.values())  {
                if(v >=2) answer++;
            }
        }
        return answer;
    }
    
    public int move(int s, int e, int[][] points, int startTime) {
        int sr = points[s-1][0];
        int sc = points[s-1][1];
        int er = points[e-1][0];
        int ec = points[e-1][1];
        
        int idx = startTime;
        while(sr != er || sc != ec) {
            if(sr == er) {
                sc = (ec > sc) ? sc+1 : sc-1;
            }else {
                sr = (er > sr) ? sr+1 : sr-1;
            }
            idx++;
            insert(sr, sc, idx);
        }
        return idx;
    }
    
    public int inverter(int r, int c) {
        return r*max + c;
    }
    
    public void insert(int r, int c, int idx) {
        if(!map.containsKey(idx)) {
            map.put(idx, new HashMap<>());
        }
            
        int key = inverter(r, c);
        Map<Integer, Integer> tmp = map.get(idx);
        tmp.put(key, tmp.getOrDefault(key, 0) + 1);
    }
}