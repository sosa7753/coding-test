import java.io.*;
import java.util.*;
class Main {
    static int N, K, W;
    static long[] dist;
    static long[] time; 
    static List<List<Integer>> list; // 정방향 
    static List<List<Integer>> re; // 역방향 
    static int[] cnt; // 진입 차수 
    static Queue<Integer> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            dist = new long[N+1];
            time = new long[N+1];       
            cnt = new int[N+1];
            
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                time[j] = Long.parseLong(st.nextToken());
            }
            
            list = new ArrayList<>();
            re = new ArrayList<>();
            for(int j=0; j<=N; j++) {
                list.add(new ArrayList<>());
                re.add(new ArrayList<>());
            }
            
            for(int j=0; j<K; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                list.get(s).add(e);
                re.get(e).add(s);
            }
            
            W = Integer.parseInt(br.readLine());      
            initCnt();
            
            for(int j=1; j<cnt.length; j++) {
                if(cnt[j] == 0) {
                    queue.offer(j);
                    dist[j] = time[j];
                }
            }
            
            sort();
            
            bw.write(String.valueOf(dist[W]));
            bw.write("\n");   
        }
        bw.flush();
        bw.close();        
    }
    
    public static void initCnt() {
        queue = new LinkedList<>();
        for(int i=1; i<=N; i++) {
            for(int j=0; j<list.get(i).size(); j++) {
                cnt[list.get(i).get(j)]++;
            }
        }
    }
    
    public static void sort() {
        while(!queue.isEmpty()) {
            int now = queue.poll();
            
            // 다음 큐
            for(int i=0; i<list.get(now).size(); i++) {
                int next = list.get(now).get(i);
                cnt[next]--;
                if(cnt[next] == 0) {
                    queue.offer(next);
                }
            }
            
            // 걸리는 시간 연산부
            long max = 0;
            for(int i=0; i<re.get(now).size(); i++) {
                int pre = re.get(now).get(i);          
                max = Math.max(max, dist[pre]);
            }
            dist[now] = max + time[now];
            
            if(now == W) {
               return;
            }
        }
    }
}