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
    static int N,M;
    static int[] dist;
    static List<List<Node>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
        
        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.get(s).add(new Node(e, c));
            list.get(e).add(new Node(s, c));
        }
        
        dijkstra();
        
        System.out.print(dist[N]);
    }
    
    public static void dijkstra() {
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y) ->(x.w - y.w));
        pq.offer(new Node(1, 0));
        dist[1] = 0;
        
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
    }
}