import java.io.*;
import java.util.*;
class Main {
    static class Node {
        int to;
        int w;
        Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
    static int INF = 200000000;
    static int N,E;
    static int A,B;
    static int first = 0;
    static int second = 0;
    static List<List<Node>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.get(s).add(new Node(e, c));
            list.get(e).add(new Node(s, c));
        }
        
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        
        dijkstra(1, A, B); // 1->A, 1->B
        dijkstra(B, A ,0); // A->B, B->A
        dijkstra(N, B, A);  // B->N, A->N
                   
        if(first >= INF && second >= INF) {
            System.out.print(-1);
        }else {
            System.out.print(Math.min(first, second));
        }
    }
    
    public static void dijkstra(int start, int end1, int end2) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        boolean[] visited = new boolean[N+1];
        
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> (x.w-y.w));
        pq.offer(new Node(start, 0));
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            if(visited[now.to]) {
                continue;
            }
            visited[now.to] = true;
            
            for(int i=0; i<list.get(now.to).size(); i++) {
                Node next = list.get(now.to).get(i);
                if(dist[next.to] > now.w + next.w) {
                    dist[next.to] = now.w + next.w;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }
        
        first += dist[end1];
        if(end2 == 0) {
            second += dist[end1];
        }else {
            second += dist[end2];
        }
    }
}