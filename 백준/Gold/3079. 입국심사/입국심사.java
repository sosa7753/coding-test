import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken()); // 서있는 사람 수.
        
        long[] arr = new long[N];
        for(int i=0; i<N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        
        Arrays.sort(arr);
        
        long left = 0;
        long right = M * arr[0];
        
        while(left <= right) {
            long mid = right - (right - left)/2;
            
            long cnt = 0;
            boolean isTrue = false;
            for(int i=0; i<arr.length; i++) {
                cnt += mid/arr[i];
                if(cnt >= M) {
                    isTrue = true;
                    break;
                }
            }
            
            if(isTrue) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        System.out.print(left);
    }
}