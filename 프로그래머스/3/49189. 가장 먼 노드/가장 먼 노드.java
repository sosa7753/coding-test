import java.util.*;
class Solution {
    int[] dist;
    List<List<int[]>> list = new ArrayList<>();
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<edge.length; i++) {
            list.get(edge[i][0]).add(new int[] {edge[i][1], 1});
            list.get(edge[i][1]).add(new int[] {edge[i][0], 1});
        }
        
        Dijkstra(n);
        
        int max = 0;
        for(int i=1; i<dist.length; i++) {
            max = Math.max(dist[i], max);
        }
        
        for(int i=1; i<dist.length; i++) {
            if(max == dist[i]) {
                answer++;
            }
        }        
        return answer;
    }
    
    public void Dijkstra(int n) {
        boolean[] visited = new boolean[n+1];
        
        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (x[1] - y[1]));
        pq.offer(new int[] {1, 0});
        
        dist[1] = 0; 
        
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            
            if(visited[now[0]]) {
                continue;
            }
            visited[now[0]] = true;
            
            for(int i=0; i<list.get(now[0]).size(); i++) {
                int[] next = list.get(now[0]).get(i);
                
                if(dist[next[0]] > next[1] + now[1]) {
                    dist[next[0]] = next[1] + now[1];
                    pq.offer(new int[] {next[0], dist[next[0]]});
                }
            }
        }
        
        
    }
}