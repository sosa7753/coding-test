import java.util.*;
import java.io.*;
class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int N;
    static boolean[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        map = new boolean[101][101];
        
        for(int i=0; i<N; i++) {
            String[] str = br.readLine().split(" ");
            
            int x = Integer.parseInt(str[0]);
            int y = Integer.parseInt(str[1]);
            int d = Integer.parseInt(str[2]); // 초기 방향 
            int g = Integer.parseInt(str[3]); // 세대 
            curve(x,y,d,g);
        }       
        System.out.println(square());       
    }
    
    public static void curve(int x, int y, int d, int g) {   
        List<Integer> list = new ArrayList<>();
        list.add(d);
        
        for(int i=1; i<=g; i++) {
            for(int j=list.size()-1; j>=0; j--) {
                list.add((list.get(j)+1)%4);
            }
        }
        
        map[y][x] = true;  
        for(int dir : list) {
            y += dy[dir];
            x += dx[dir];
            map[y][x] = true;
        }     
    }
    
    public static int square() {
        int result = 0;
        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) {
                    result++;
                }
            }
        }
        return result;
    }
}