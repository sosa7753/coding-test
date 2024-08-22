import java.util.*;
import java.io.*;

class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int cnt = 0;
        while(true) {
            int t = Integer.parseInt(br.readLine());
            if(t ==0) {
                break;
            }
            
            cnt++;
            
            int[][] map = new int[t][t];
            for(int i=0; i<t; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<t; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            bw.write("Problem " + cnt + ": " + DP(map) + "\n");
        }     
        
        bw.flush();
        bw.close();
    }
    
    public static int DP(int[][] map) {
        int len = map.length;
        
        int[][] dist = new int[len][len];
        for(int i=0; i<len; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0,0});
        dist[0][0] = map[0][0]; 
          
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            
            for(int i=0; i<4; i++) {
                int row = now[0] + dr[i];
                int col = now[1] + dc[i];
                
                if(row < 0 || row > len-1 || col < 0 || col > len-1) {
                    continue;
                }
                              
                if(dist[row][col] > dist[now[0]][now[1]] + map[row][col]) {
                    dist[row][col] = dist[now[0]][now[1]] + map[row][col];              
                    queue.offer(new int[] {row, col});
                }                                                    
            }
        }
        return dist[len-1][len-1];   
    }
}