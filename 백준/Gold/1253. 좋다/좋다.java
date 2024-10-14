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
        int answer = 0;
        for(int i=0; i<N; i++) {
            int target = arr[i];
            
            int p1 = 0;
            int p2 = arr.length-1;
            
            while(p1 < p2) {
                if(p1 == i) {
                    p1++;
                    continue;
                }
                
                if(p2 == i) {
                    p2--;
                    continue;
                }
                int sum = arr[p1] + arr[p2];
                
                if(sum == target) {
                    answer++;
                    break;
                }else if(sum > target) {
                    p2--;
                }else {
                    p1++;
                }
            }          
        }
        System.out.print(answer);
    }
}