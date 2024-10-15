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
        
        Arrays.sort(arr);      
        int[] answer = new int[3];
        long min = Long.MAX_VALUE;
        
        for(int i=0; i<N-2; i++) {
            int p1 = i+1;
            int p2 = N-1;
            
            while(p1 < p2) {
                long sum = (long)arr[i] + (long)(arr[p1] + arr[p2]);
                
                if(Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    answer[0] = arr[i];
                    answer[1] = arr[p1];
                    answer[2] = arr[p2];
                }
                
                if(sum > 0) {
                    p2--;
                }else {
                    p1++;
                }
            }
        }   
        Arrays.sort(answer);
        System.out.print(answer[0] + " " + answer[1] + " " + answer[2]);      
    }
}