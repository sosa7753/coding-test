import java.util.*;
import java.io.*;
class Main {
    static int N;
    static List<List<Integer>> list = new ArrayList<>();
    static int[] cnt;
    static int[] build;
    static int[] answer; // 해당 건물까지 오는데 걸린 최대 시간 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
        
        cnt = new int[N+1];
        build = new int[N+1]; // 자기 자신이 짓는 시간
        answer = new int[N+1];
        
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            build[i] = Integer.parseInt(st.nextToken());
            
            while(true) {
                int value = Integer.parseInt(st.nextToken());
                if(value == -1) {
                    break;
                }
                list.get(value).add(i);
                cnt[i]++;
            }
        }
        
        sort();
        
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
            sb.append(answer[i] + build[i]).append("\n");
        }    
        System.out.print(sb.toString());
    }
    
    public static void sort() {
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=N; i++) {
            if(cnt[i] == 0) {
                q.offer(i);
            }
        }
        
        while(!q.isEmpty()) {
            int now = q.poll();
            
            for(int next : list.get(now)) {
                cnt[next]--;
                answer[next] = Math.max(answer[next], answer[now] + build[now]);
                if(cnt[next] == 0) {
                    q.offer(next);
                }
            }
        }
    }
}