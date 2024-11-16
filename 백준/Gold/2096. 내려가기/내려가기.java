import java.util.*;
import java.io.*;
class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int[][] max = new int[N+1][3];
        int[][] min = new int[N+1][3];
        for(int i=1; i<=N; i++) {
            for(int j=0; j<3; j++) {
                min[i][j] = Integer.MAX_VALUE;
            }
        }
        
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if(j==0) {
                    max[i][j] = Math.max(max[i-1][j], max[i-1][j+1]) + tmp;
                    min[i][j] = Math.min(min[i-1][j], min[i-1][j+1]) + tmp;
                }else if(j==1) {
                    max[i][j] = Math.max(max[i-1][j], Math.max(max[i-1][j-1], max[i-1][j+1])) + tmp;
                    min[i][j] = Math.min(min[i-1][j], Math.min(min[i-1][j-1], min[i-1][j+1])) + tmp;
                }else {
                    max[i][j] = Math.max(max[i-1][j], max[i-1][j-1]) + tmp;
                    min[i][j] = Math.min(min[i-1][j], min[i-1][j-1]) + tmp;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        int MAX = 0;
        int MIN = Integer.MAX_VALUE;
        for(int i=0; i<3 ; i++) {
            MAX = Math.max(max[N][i], MAX);
            MIN = Math.min(min[N][i], MIN);           
        }
        sb.append(MAX).append(" ").append(MIN);
        System.out.print(sb.toString());
    }
}