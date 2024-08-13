import java.util.*;
class Solution {
    int[] dist; 
    List<List<Integer>> list = new ArrayList<>();
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<roads.length; i++) {
            list.get(roads[i][0]).add(roads[i][1]);
            list.get(roads[i][1]).add(roads[i][0]);
        }
        
        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        Dijkstra(destination);
        
        for(int i=0; i<sources.length; i++) {
            if(dist[sources[i]] == Integer.MAX_VALUE) {
                answer[i] = -1;
            }else {
                answer[i] = dist[sources[i]];
            }
        }
        return answer;
    }
    
    public void Dijkstra(int destination) {
        boolean[] visited = new boolean[dist.length];
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (x[1] - y[1]));
        pq.offer(new int[] {destination, 0});
        dist[destination] = 0;
        
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            
            if(visited[now[0]]) {
                continue;
            }
            visited[now[0]] = true;
            
            for(int i=0; i<list.get(now[0]).size(); i++) {
                int next = list.get(now[0]).get(i);
                
                if(dist[next] > now[1] + 1) {
                    dist[next] = now[1] + 1;
                    pq.offer(new int[] {next, dist[next]});
                }
            }
        }
    }
}