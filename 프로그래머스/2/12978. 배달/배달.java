import java.util.*;
class Solution {
    int[] dist;
    List<List<int[]>> list = new ArrayList<>();
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<road.length; i++) {
            list.get(road[i][0]).add(new int[] {road[i][1], road[i][2]});
            list.get(road[i][1]).add(new int[] {road[i][0], road[i][2]});
        }
        
        Dijkstra(N, road);
        
        for(int i=1; i<=N; i++) {
            if(dist[i] <= K) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public void Dijkstra(int N, int[][] road) {
        boolean[] visited = new boolean[N+1];
        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (x[1] - y[1]));
        dist[1] = 0;
        pq.offer(new int[] {1, 0});
        
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            
            if(visited[now[0]]) {
                continue;
            }
            visited[now[0]] = true;
            
            for(int i=0; i<list.get(now[0]).size(); i++) {
                int[] next = list.get(now[0]).get(i);
                
                if(dist[next[0]] > now[1] + next[1]) {
                    dist[next[0]] = now[1] + next[1];              
                }
                pq.offer(new int[] {next[0], dist[next[0]]});
            }
        }
    }
}