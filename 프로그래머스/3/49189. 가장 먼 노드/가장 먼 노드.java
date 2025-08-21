import java.util.*;
class Solution {
    List<List<Integer>> list = new ArrayList<>();
    public int solution(int n, int[][] edge) {
        
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int[] ed : edge) {
            list.get(ed[0]).add(ed[1]);
            list.get(ed[1]).add(ed[0]);
        }
        
        int[] dist = dijkstra(n);
        int answer = 1;
        int max = 0;
        for(int i=2; i<=n; i++) {
            if(dist[i] == Integer.MAX_VALUE) {
                continue;
            }
            
            if(max == dist[i]) {
                answer++;
            }else if(max < dist[i]) {
                max = dist[i];
                answer = 1;
            }      
        }
        return answer;
    }
    
    public int[] dijkstra(int n) {
        boolean[] visited = new boolean[n+1];
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (x[1]-y[1]));
        pq.offer(new int[]{1, 0});
        
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            int to = now[0];
            int w = now[1];
            
            if(visited[to]) {
                continue;
            }
            
            visited[to] = true;
            for(int next : list.get(to)) {
                if(dist[next] > w + 1) {
                    dist[next] = w + 1;
                    pq.offer(new int[]{next, dist[next]});
                }
            }
        }
        return dist;
    }
}