import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
           
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((x,y) -> (y-x));
        for(int i=0; i<arr.length-1; i++) {
            int tmp = arr[i+1] - arr[i];
            if(tmp != 0) {
                pq.offer(tmp);
            }
        }
        
        int len = pq.size();
        
        if(len+1 <= K) {
            System.out.print(0);
            return;
        }
        
        for(int i=0; i<K-1; i++) {
            pq.poll();
        }
        
        int sum = 0;
        while(!pq.isEmpty()) {
            sum += pq.poll();
        } 
        
        System.out.print(sum);
    }
}