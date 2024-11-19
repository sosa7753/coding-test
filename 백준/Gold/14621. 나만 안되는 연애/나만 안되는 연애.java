import java.util.*;
import java.io.*;
class Main {
    static class Node {
        int to;
        int w;
        Node(int to, int w) {
            this.w = w;
            this.to = to;
        }
    }
    static int N;
    static int M;
    static int[] mf; // 남자 0, 여자 1
    static List<List<Node>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        mf = new int[N+1];
        
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            if("M".equals(st.nextToken())) {
                mf[i] = 0;
            }else {
                mf[i] = 1;
            }
        }
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if(mf[s] == mf[e]) {
                continue;
            }
            list.get(s).add(new Node(e, w));
            list.get(e).add(new Node(s, w));
        }
        
        int result = prim();
        System.out.print(result);
    }
    
    public static int prim() {
        int sum = 0;
        int cnt = 0;
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> (x.w - y.w));
        pq.offer(new Node(1, 0));
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            if(visited[now.to]) {
                continue;
            }
            visited[now.to] = true;
            sum += now.w;
            cnt++;
            
            for(int i=0; i<list.get(now.to).size(); i++) {
                Node next = list.get(now.to).get(i);
                if(visited[next.to]) {
                    continue;
                }
                pq.add(next);
            }   
        }    
        if(cnt != N || sum == 0) {
            return -1;
        }
        return sum;
    }
}