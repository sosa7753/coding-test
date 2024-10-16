import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());           
        }
          
        int min = Integer.MAX_VALUE;
        int[] answer = new int[2];
        int p1 = 0;
        int p2 = arr.length-1;
        while(p1 < p2) {
            int sum = arr[p1] + arr[p2];
            
            if(min > Math.abs(sum)) {
                min = Math.abs(sum);
                answer[0] = arr[p1];
                answer[1] = arr[p2];
            }
            
            if(sum >= 0) {
                p2--;
            }else {
                p1++;
            }           
        }
        
        System.out.print(answer[0] + " " + answer[1]);
    }
}