import java.util.*;
class Solution {
    List<List<int[]>> list = new ArrayList<>();
    boolean[] visited;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<costs.length; i++) {
            list.get(costs[i][0]).add(new int[] {costs[i][1], costs[i][2]});
            list.get(costs[i][1]).add(new int[] {costs[i][0], costs[i][2]});
        }
        return prim(n, costs);
    }
    
    public int prim(int n, int[][] costs) {
        int result = 0;
        visited = new boolean[n+1];
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (x[1] - y[1]));
        pq.offer(new int[] {1, 0});
        
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            
            if(visited[now[0]]) {
                continue;
            }
            visited[now[0]] = true;
            result += now[1];
            
            for(int i=0; i<list.get(now[0]).size(); i++) {
                int[] next = list.get(now[0]).get(i);
                
                if(visited[next[0]]) {
                    continue;
                }                
                pq.offer(next);               
            }
        }
        return result;
    }
 }