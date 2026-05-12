import java.util.*;
class Solution {
    List<List<int[]>> list = new ArrayList<>();
    Set<Integer> set = new HashSet<>();
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int[] path : paths) {
            list.get(path[0]).add(new int[]{path[1], path[2]});
            list.get(path[1]).add(new int[]{path[0], path[2]});
        }
        
        for(int s : summits) {
            set.add(s);
        }
        
        int[] dist = dijkstra(gates, n);
        
        int[] result = new int[]{-1, Integer.MAX_VALUE};
        for(int s : set) {
            if(dist[s] < result[1] || (dist[s] == result[1] && s < result[0])){
                result[0] = s;
                result[1] = dist[s];
            }
        }
        return result;
        
    }
    
    public int[] dijkstra(int[] gates, int n) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        // node 번호, intensity
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (x[1] - y[1]));
        
        for(int gate : gates) {
            dist[gate] = 0;
            pq.offer(new int[]{gate, 0});
        }
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[1], node = cur[0];
            
            if(cost > dist[node]) continue;
            
            if(set.contains(node)) continue; // 산봉우리
            
            for(int[] next : list.get(node)) {
                int nextNode = next[0], weight = next[1];
                int nextIntensity = Math.max(cost, weight);
                if(nextIntensity < dist[nextNode]) {
                    dist[nextNode] = nextIntensity;
                    pq.offer(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
        
        return dist;
    }
}