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
    static int N,M;
    static int S,E;
    static List<List<Node>> list = new ArrayList<>();
    static List<List<Node>> reList = new ArrayList<>();
    static int[] cnt; // 진입차수 배열 
    static int[] dist; // 거리 배열
    static boolean[] visited;
    static Queue<Integer> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
         
        cnt = new int[N+1];       
        dist = new int[N+1];
        visited = new boolean[N+1];
        
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
            reList.add(new ArrayList<>());
        }
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            list.get(s).add(new Node(e, w));
            reList.get(e).add(new Node(s, w));
            cnt[e]++;
        }
        
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        
        // 출발 초기화
        q.offer(S);
        while(!q.isEmpty()) { // 위상정렬을 수행하면서 각 노드까지 임계경로 구함.
            int now = q.poll();
            
            for(Node next : list.get(now)) {
                cnt[next.to]--;
                dist[next.to] = Math.max(dist[next.to], dist[now] + next.w);
                
                if(cnt[next.to] == 0) {
                    q.offer(next.to);
                }
            }
        }
        
        // 역방향 위상 정렬 -> 현재 노드의 임계경로 = to까지의 임계경로 + w 이면 해당 도로는 쉬지않고 달림.
        q = new LinkedList<>();
        q.offer(E);
        visited[E] = true;
        int answer = 0;
        while(!q.isEmpty()) {
            int now  = q.poll();
            
            for(Node next : reList.get(now)) {
                if(dist[next.to]  + next.w == dist[now]) {
                    answer++;
                    
                    if(!visited[next.to]) {
                        visited[next.to] = true;
                        q.offer(next.to);
                    }
                }
            }
        }
        System.out.println(dist[E]);
        System.out.println(answer);       
    }
}