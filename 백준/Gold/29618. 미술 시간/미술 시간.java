import java.util.*;
import java.io.*;
class Main {
    static int N, Q;
    static List<Integer> list = new ArrayList<>();
    static int[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        answer = new int[N+1];
        
        for(int i=1; i<=N; i++) {
            list.add(i);
        }
        
        for(int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            int idx = binarySearch(s); 
            while(!list.isEmpty() && idx < list.size() && list.get(idx) <=e) {
                answer[list.remove(idx)] = v;   
            }
            
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
            sb.append(answer[i]).append(' ');
        }
        System.out.print(sb);
    }
    
    public static int binarySearch(int t) { // 시작 idx를 찾는 것
        int l = 0; int r = list.size();
        while(l < r) {
            int mid = (l+r)/2;
            if(list.get(mid) < t) l = mid+1;
            else r = mid;
        }
        return l;
    }
}