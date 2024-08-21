import java.util.*;
class Solution {
    List<List<Node>> list = new ArrayList<>();
    int[] dist;
    boolean[] visited;
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        dist = new int[N+1];
        visited = new boolean[N+1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<road.length; i++) {
            list.get(road[i][0]).add(new Node(road[i][1], road[i][2]));
            list.get(road[i][1]).add(new Node(road[i][0], road[i][2]));            
        }
        
        Dijkstra(N);
        
        for(int i=1; i<=N; i++) {
            if(dist[i] <= K) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public void Dijkstra(int N) {
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> (x.weight - y.weight));
        pq.offer(new Node(1, 0));
        dist[1] = 0;
        
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            
            if(visited[node.to]) {
                continue;
            }
            
            visited[node.to] = true;
            for(int i=0; i<list.get(node.to).size(); i++) {
                Node next = list.get(node.to).get(i);
                
                if(dist[next.to] > node.weight + next.weight) {
                    dist[next.to] = node.weight + next.weight;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }      
    }
}

class Node {
    int to;
    int weight;
    Node (int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}