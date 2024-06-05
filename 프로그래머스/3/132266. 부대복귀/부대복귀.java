import java.util.*;
class Solution {
    List<List<Node>> list = new ArrayList<>();
    int[] dist;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {

        // 리스트 생성
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        // 거리 배열 생성
        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        // 데이터 저장
        for(int i=0; i<roads.length; i++) {
            list.get(roads[i][0]).add(new Node(roads[i][1], 1));
            list.get(roads[i][1]).add(new Node(roads[i][0], 1));
        }
        
        Dijkstra(n, destination);
        
        int[] answer = new int[sources.length];
        int idx = 0;
        
        for(int i=0; i<sources.length; i++) {
            if(dist[sources[i]] == Integer.MAX_VALUE) {
                answer[idx++] = -1;
            }else {
                answer[idx++] = dist[sources[i]];
            }
        }
        
        return answer;
    }
    
    public void Dijkstra(int n, int destination) {
        boolean[] visited = new boolean[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> (x.weight - y.weight));
        
        pq.offer(new Node(destination, 0));
        dist[destination] = 0;
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            if(visited[now.to]) {
                continue;
            }
            
            for(int i=0; i<list.get(now.to).size(); i++) {
                Node node = list.get(now.to).get(i);
                
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