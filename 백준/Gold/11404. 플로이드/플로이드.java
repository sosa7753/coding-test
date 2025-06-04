import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        
        int[][] map = new int[n+1][n+1];
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(i != j) {
                    map[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            map[s][e] = Math.min(map[s][e], w);
        }
        
        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    if(map[i][k] != Integer.MAX_VALUE && map[k][j] != Integer.MAX_VALUE) {
                        if(map[i][k] + map[k][j] < map[i][j]) {
                            map[i][j] = map[i][k] + map[k][j];
                        }    
                    }
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(map[i][j] == Integer.MAX_VALUE) {
                    sb.append(0);
                }else {
                    sb.append(map[i][j]);          
                } 
                if(j < n) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }        
        System.out.print(sb);
     }
}