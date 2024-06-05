import java.util.*;
class Solution {
    List<List<Node>> list;
    int[] dist;
    public int solution(int N, int[][] road, int K) {
        
        // 거리 배열 생성 
        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        // 리스트 생성 
        list = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
        
        // 데이터 초기화
        for(int i=0; i<road.length; i++) {
            list.get(road[i][0]).add(new Node(road[i][1], road[i][2]));
            list.get(road[i][1]).add(new Node(road[i][0], road[i][2]));
        }
        
        Dijkstra(N, 1);
        
        int answer = 0;
        for(int i=1; i<=N; i++) {
            if(dist[i] <= K) {
                answer++;
            }
        }
        System.out.println(Arrays.toString(dist));
        return answer;
    }
    
    public void Dijkstra(int N, int start) {    
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> (x.weight - y.weight));
        
        pq.offer(new Node(start, 0));
        dist[start] = 0;
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            if(visited[now.to]) {
                continue;
            }
            visited[now.to] = true;
            
            for(int i=0; i<list.get(now.to).size(); i++) {
                Node node = list.get(now.to).get(i);
                
                if(visited[node.to]) {
                    continue;
                }
                               
                if(dist[node.to] > now.weight + node.weight) {
                    dist[node.to] = now.weight + node.weight;
                    pq.offer(new Node(node.to, dist[node.to]));
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