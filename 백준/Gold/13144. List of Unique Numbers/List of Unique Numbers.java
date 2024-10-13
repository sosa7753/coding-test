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
        long answer = 0;
        
        int p1 = 0;
        int p2 = 0;
        Set<Integer> set = new HashSet<>();
        int len = 0;
        while(p1 <= p2 && p2 < arr.length) {
            set.add(arr[p2]);
            if(len != set.size()) {
                answer +=(long)(p2 - p1 + 1);
                p2++;
            }else {
                set.remove(arr[p1]);
                p1++;
            }
            len = set.size();
        }   
         System.out.print(answer);
    }   
}