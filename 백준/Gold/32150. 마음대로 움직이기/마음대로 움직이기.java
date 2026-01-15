import java.util.*;
import java.io.*;
class Main {
    static int INF = Integer.MAX_VALUE;
    static int K, Q;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        K = Integer.parseInt(br.readLine());
        
        arr = new int[K+2];
        st =  new StringTokenizer(br.readLine());
        
        arr[0] = -INF; arr[K+1] = INF;
        for(int i=1; i<=K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());          
        }
        
        Q = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            sb.append(solve(T, P));
            if(i != Q-1) sb.append("\n");
        }
        
        System.out.print(sb);
    }
    
    public static int solve(int T, int P) {
        /* 1. 초기 범위 만들기
           2. 장애물로 커트 하기
           3. 가짓수 세기
        */
        
        int fs = P - T; int fe = P + T; // 1단계
        int origin = binarySearch(P);
       
        int ss = Math.max(arr[origin]+1, fs);
        int se = Math.min(arr[origin+1]-1, fe);
        
        if((fe%2 == 0 && ss%2 == 1) || (fe%2 == 1 && ss%2 == 0)) ss++;
        if((fe%2 == 0 && se%2 == 1) || (fe%2 == 1 && se%2 == 0)) se--;
        
        return (se  - ss)/2 + 1;
    }
    
    public static int binarySearch(int P) { 
        int L = 0;
        int R = arr.length-1;
        
        int answer = R;
        while(L <= R) {
            int mid = R + (L - R)/2;
            
            if(P >= arr[mid]) {
               answer = mid;
               L = mid + 1;
            }else {
               R = mid - 1;
            }
        }
        return answer;
    }
}