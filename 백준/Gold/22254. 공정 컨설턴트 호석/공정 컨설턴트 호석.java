import java.util.*;
import java.io.*;
class Main {
    static int X;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int L = 1;
        int R = X;
        
        while(L<=R) {
            int mid = (L+R)/2;
            
            if(check(mid, arr)) {
                R = mid -1;
            }else {
                L = mid +1; 
            }
        }
        System.out.print(L);
    }
    
    public static boolean check(int cnt, int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i=0; i<arr.length; i++) {
            if(pq.isEmpty() || pq.size() < cnt) {
                pq.offer(arr[i]);
            }else {
                int tmp = pq.poll();
                pq.offer(tmp + arr[i]);
            }
        }
        
        int result = 0;
        while(!pq.isEmpty()) {
            result = Math.max(result, pq.poll());
        }
        
        if(result <= X) {
            return true;
        }
        return false;
    }
}