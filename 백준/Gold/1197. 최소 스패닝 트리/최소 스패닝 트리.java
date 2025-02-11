import java.io.*;
import java.util.*;
class Main {
    static class Node {
        int to;
        long w;
        Node(int to, long w) {
            this.to = to;
            this.w = w;
        }
    }
    static int N,M;
    static List<List<Node>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            long we = Long.parseLong(st.nextToken());
            list.get(s).add(new Node(e, we));
            list.get(e).add(new Node(s, we));
        }
        
        System.out.print(prim());
    }
    public static long prim() {
        long answer = 0;
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> Long.compare(x.w, y.w));
        pq.offer(new Node(1, 0L));
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(visited[now.to]) {
                continue;
            }
            visited[now.to] = true;
            answer += now.w;
            
            for(int i=0; i<list.get(now.to).size(); i++) {
                Node next = list.get(now.to).get(i);
                if(!visited[next.to]) {
                    pq.offer(next);    
                }        
            }           
        }
        return answer;       
    }
}