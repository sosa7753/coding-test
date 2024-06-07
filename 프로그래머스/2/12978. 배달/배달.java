import java.util.*;
class Solution {
    List<List<Node>> list = new ArrayList<>();
    int[] dist;
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        // 리스트 생성
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
        
        // 거리 배열 초기화
        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        // 간선 정보 넣어주기.
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
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> (x.weight-y.weight));
        
        pq.offer(new Node(1, 0));
        dist[1] = 0;
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            if(visited[now.to]) {
                continue;
            }
            visited[now.to] = true;
            
            for(int i=0; i<list.get(now.to).size(); i++) {
                Node adjNode = list.get(now.to).get(i);
                
                if(dist[adjNode.to] > now.weight + adjNode.weight) {
                    dist[adjNode.to] = now.weight + adjNode.weight;
                    pq.offer(new Node(adjNode.to, dist[adjNode.to]));
                }
            }
        }
    }
}

class Node {
    int to;
    int weight;
    Node(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}