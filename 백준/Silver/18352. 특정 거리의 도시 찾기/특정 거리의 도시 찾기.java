import java.util.*;
import java.io.*;

public class Main {
    
    static class Node {
        int to;
        int cnt;
        Node(int to, int cnt) {
            this.to = to;
            this.cnt = cnt;
        }
    }
       
    static List<List<Node>> list;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 노드 개수
        int M = Integer.parseInt(st.nextToken()); // 간선 정보 개수
        int K = Integer.parseInt(st.nextToken()); // 거리 
        int S = Integer.parseInt(st.nextToken()); // 시작 노드 
        
        // 리스트 초기화
        list = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
        // 간선 정보 입력 및 저장.
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.get(s).add(new Node(end, 1));
        }
        
        Dijkstra(M, N, S);
        boolean isfalse = true;
        for(int i=1; i<=N; i++) {
            if(dist[i] == K) {
                isfalse = false;
                System.out.println(i);
            }
        }
        
        if(isfalse) {
            System.out.println(-1);
        } 
    }
    public static void Dijkstra(int M, int N, int S) {
        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        boolean[] visited = new boolean[N+1];
        
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> (x.cnt - y.cnt));
        
        pq.offer(new Node(S, 0));
        dist[S] = 0;
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            if(visited[now.to]) {
                continue;
            }
            visited[now.to] = true;
            
            for(int i=0; i<list.get(now.to).size(); i++) {
                Node next = list.get(now.to).get(i);
                
                if(dist[next.to] > now.cnt  + next.cnt) {
                    dist[next.to] = now.cnt + next.cnt;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }
    }      
}

