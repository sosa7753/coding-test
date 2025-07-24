import java.util.*;
import java.io.*;
class Main {
    static int N, M;
    static int[] min, max;
    static int size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        size = 1;
        while(size < N) { // N = 6 일 때, size = 8이 되어야함.
            size <<= 1;
        }
        
        min = new int[size * 2];
        max = new int[size * 2];
        for(int i=0; i<N; i++) {
            int val = Integer.parseInt(br.readLine());
            
            min[i+size] = val;
            max[i+size] = val;
        }
        
        for(int i=size-1; i>=1; i--) {
            min[i] = Math.min(min[i*2], min[i*2+1]);
            max[i] = Math.max(max[i*2], max[i*2+1]);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(small(a, b)).append(" ").append(big(a, b)).append("\n");
        }
        
        System.out.print(sb);
        
    }
    
    public static int small(int s, int e) {
        int left = size + s - 1;
        int right = size + e - 1;
        int result = Integer.MAX_VALUE;
        
        while(left <= right) {
            if(left%2 == 1) {
                result = Math.min(result, min[left++]);
            }
            
            if(right%2 == 0) {
                result = Math.min(result, min[right--]);
            }
            
            left /=2;
            right /=2;
        }
        return result;
    }
    
    public static int big(int s, int e) {
        int left = size + s - 1;
        int right = size + e -1;
        int result = 0;
        
        while(left <= right) {
            if(left%2 == 1) {
                result = Math.max(result, max[left++]);
            }
            
            if(right%2 == 0) {
                result = Math.max(result, max[right--]);
            }
            
            left /=2;
            right /=2;
        }
        return result;
    }
}