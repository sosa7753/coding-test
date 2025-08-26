import java.util.*;
class Solution {
    List<List<int[]>> list = new ArrayList<>();
    public int solution(int n, int[][] costs) {
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int[] cost : costs) {
            list.get(cost[0]).add(new int[]{cost[1], cost[2]});
            list.get(cost[1]).add(new int[]{cost[0], cost[2]});
        }
        
        return prim(n, costs);
    }
    
    public int prim(int n, int[][] costs) {
        int result = 0;
        boolean[] visited = new boolean[n+1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (x[1] -y[1]));
        pq.offer(new int[]{1, 0});
        
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            int node = now[0]; int w = now[1];
            
            if(visited[node]) {
                continue;
            }
            
            visited[node] = true;
            result += w;
            
            for(int[] next : list.get(node)) {
                pq.offer(next);
            }
        }
        return result;
    }
}