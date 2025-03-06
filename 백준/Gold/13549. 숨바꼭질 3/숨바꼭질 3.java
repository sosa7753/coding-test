import java.util.*;
import java.io.*;
class Main {
    static int N;
    static int K;
    static int min = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        BFS(); 
        System.out.print(min);
    }
    
    public static void BFS() {
        boolean[] visited = new boolean[100001];
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) ->(x[1] - y[1]));
        pq.offer(new int[]{N, 0});
        
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            int cur = now[0];
            int time = now[1];
                       
            if(cur == K) {
                min = time;
                return;
            }
            
            if(visited[cur]) {
                continue;
            }
            visited[cur] = true;
            
            if(cur <= 50000) {
                pq.offer(new int[]{cur*2, time});
            }      
            if(cur <= 99999) {
                pq.offer(new int[]{cur+1, time+1});
            }
            
            if(cur > 0) {
                pq.offer(new int[]{cur-1, time+1});
            }            
        }
    }
}