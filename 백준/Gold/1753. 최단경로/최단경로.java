import java.util.*;
import java.io.*;
class Main {
    static class Node {
        int to;
        int w;
        Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
    static int V,E; // 정점, 간선 수 
    static List<List<Node>> list = new ArrayList<>();
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        int K = Integer.parseInt(br.readLine());
        dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for(int i=0; i<=V; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.get(s).add(new Node(e,c));
        }
        
        dijkstra(K);
        
        for(int i=1; i<dist.length; i++) {
            if(dist[i] == Integer.MAX_VALUE) {
                bw.write("INF");
            }else {
                bw.write(String.valueOf(dist[i]));
            }
            bw.write("\n");
        }
        
        bw.flush();
        bw.close();
        
    }
    
    public static void dijkstra(int K) {
        boolean[] visited = new boolean[V+1];
        dist[K] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y) ->(x.w - y.w));
        pq.offer(new Node(K, 0));
        
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