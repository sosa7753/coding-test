import java.io.*;
import java.util.*;
class Main {
    static int N, M;
    static List<List<Integer>> list = new ArrayList<>(); 
    static int[] cnt;
    static Queue<Integer> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        cnt = new int[N+1];
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            list.get(f).add(b);
            cnt[b]++;
        }
        
        for(int i=1; i<=N; i++) {
            if(cnt[i] == 0) {
                q.offer(i);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append(" ");
                
            for(int next : list.get(now)) {
                cnt[next]--;
                if(cnt[next] == 0) {
                    q.offer(next);
                }
            }
        }
        
        System.out.print(sb.toString().trim());    
    }
}