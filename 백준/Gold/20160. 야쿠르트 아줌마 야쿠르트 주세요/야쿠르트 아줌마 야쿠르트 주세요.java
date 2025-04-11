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
    static int V,E;
    static List<List<Node>> list = new ArrayList<>();
    static int[] ya;
    static int start;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        for(int i=0; i<=V; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            list.get(s).add(new Node(e, w));
            list.get(e).add(new Node(s, w));
        }
        
        ya = new int[10];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<10; i++) {
            ya[i] = Integer.parseInt(st.nextToken());
        }
        
        start = Integer.parseInt(br.readLine());
        
        // 야쿠르트 아줌마의 특정 정점의 최대 도착시간
        int[] yaDist = new int[V+1];
        
        int pre = ya[0];
        for(int i=1; i<10; i++) {
            int[] dist = dijkstra(pre);
            if(dist[ya[i]] == Integer.MAX_VALUE) {
                i++;
                continue;
            }
            yaDist[ya[i]] = Math.max(yaDist[ya[i]], dist[ya[i]] + yaDist[pre]);
            pre = ya[i];
        }
        
        int[] my = dijkstra(start);
        int init = Integer.MAX_VALUE;
        int answer = -1;
        
        // 시작부터 겹치는 값
        if(start == ya[0]) {
            init = ya[0];
        }
        
        for(int i=1; i<=V; i++) {
            if(yaDist[i] == 0) {
                continue;
            }
            
            if(my[i] <= yaDist[i]) {
                if(i < init) {
                    answer = i;
                }else {
                    answer = init;
                }
                break;
            }
        }
        System.out.print(answer);
    }
    
    public static int[] dijkstra(int start) {
        boolean[] visited = new boolean[V+1];
        int[] dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> (x.w - y.w));
        pq.offer(new Node(start, 0));
        
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            
            if(visited[node.to]) {
                continue;
            }
            
            visited[node.to] = true;
            
            for(Node next : list.get(node.to)) {
                if(dist[next.to] > node.w + next.w) {
                    dist[next.to] = node.w + next.w;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }         
        }  
        return dist;
    }
}