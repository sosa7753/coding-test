import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N];      
        for(int i=0; i<N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }
        
        int idx = 0;
        while(!pq.isEmpty()) {
            arr[idx++] = pq.poll(); 
        }
        
        int left = 1;
        int right = arr[arr.length-1];
        while(left <= right) {
            int mid = right - (right - left)/2;
            
            int start = 0;
            int cnt = 1;
            for(int i=1; i<arr.length; i++) {
                if(arr[i] - arr[start] >= mid) {
                    cnt++;
                    start = i;
                }
            }
            
            if(cnt < C) { // 해당 간격으로 C개를 설치 못함. -> 더 작은 간격으로 해야됨.
                right = mid-1;
            }else {
                left = mid+1;
            }
        }       
        
        System.out.print(left -1);
    }
}