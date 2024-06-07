import java.util.*;
class Solution {
    List<List<Node>> list = new ArrayList<>();
    int[] dist;
    public int solution(int n, int[][] edge) {
        
        // 리스트 생성
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        // 리스트 초기화
        for(int i=0; i<edge.length; i++) {
            list.get(edge[i][0]).add(new Node(edge[i][1],1));
            list.get(edge[i][1]).add(new Node(edge[i][0],1));
        }
        
        // 거리 배열 초기화
        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        Dijkstra(n);

        int max = Integer.MIN_VALUE;
        int cnt = 0;
        // 거리 배열로 가장 먼 노드 및 개수 추출 
        for(int i=2; i<=n; i++) {
            if(dist[i] > max) {
                max = dist[i];
                cnt = 1;
            }else if(dist[i] == max) {
                cnt++;
            }
        }
        return cnt;
    }
    
    public void Dijkstra(int n) {
        boolean[] visited = new boolean[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> (x.weight - y.weight));
        
        pq.offer(new Node(1,0));
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