import java.util.*;
class Solution {
    List<List<Integer>> list = new ArrayList<>();
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int[] road : roads) {
            list.get(road[0]).add(road[1]);
            list.get(road[1]).add(road[0]);
        }
            
        int[] dist = dijkstra(destination, n);
        int len = sources.length;
        int[] answer = new int[len];
        for(int i=0; i<len; i++) {
            if(dist[sources[i]] == Integer.MAX_VALUE) {
                answer[i] = -1;
            }else {
                answer[i] = dist[sources[i]];
            }
        }
        return answer;
    }
    
    public int[] dijkstra(int s, int n) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        
        boolean[] visited = new boolean[n+1];
        
        PriorityQueue<int[]> pq = 
            new PriorityQueue<>((x,y) -> (x[1] -y[1]));
        
        pq.offer(new int[]{s, 0});
        
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