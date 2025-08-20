import java.util.*;
class Solution {
    List<List<Node>> list = new ArrayList<>();
    public int solution(int N, int[][] road, int K) {
        
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int[] r : road) {
            list.get(r[0]).add(new Node(r[1], r[2]));
            list.get(r[1]).add(new Node(r[0], r[2]));
        }
        
        int[] dist = dijkstra(N); 
        
        int answer = 0;
        for(int i=0; i<dist.length; i++) {
            if(dist[i] <= K) {
                answer++;
            }
        }
        return answer;
    }
    
    public int[] dijkstra(int N) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        boolean[] visited = new boolean[N+1];
        
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> (x.w - y.w));
        pq.offer(new Node(1, 0));
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(visited[now.to]) {
                continue;
            }
            visited[now.to] = true;
            
            for(Node next : list.get(now.to)) {
                if(dist[next.to] > now.w + next.w) {
                    dist[next.to] = now.w + next.w;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }
        return dist;
    }
}

class Node {
    int to;
    int w;
    Node(int to, int w) {
        this.to = to;
        this.w = w;
    }
}