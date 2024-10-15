import java.util.*;
import java.io.*;
class Main {
    static Set<Integer> set = new HashSet<>();
    static boolean visited[][] = new boolean[201][201]; // A,B 물의 양
    static int[] max = new int[3];
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
        
       for(int i=0; i<3; i++) {
           max[i] = Integer.parseInt(st.nextToken());
       }
             
       BFS();
       int[] result = new int[set.size()];
       int idx = 0;
       for(int value : set) {
           result[idx++] = value; 
       }
       Arrays.sort(result);
       for(int i=0; i<result.length; i++) {
           System.out.print(result[i] + " ");
       }
    }
    
    public static void BFS() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0,0,max[2]});
        visited[0][0] = true;      
        set.add(max[2]);
        
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            
            for(int i=0; i<3; i++) { // 출발 
                for(int j=0; j<3; j++) { // 도착 
                    if(i==j) {
                        continue;
                    }
                    
                    int tmp = Math.min(max[j]-now[j], now[i]); // 보낼 물의 양
                    int[] next = now.clone();
                    next[i] = now[i]-tmp;
                    next[j] = now[j]+tmp;
                    if(!visited[next[0]][next[1]]) {
                        visited[next[0]][next[1]] = true;
                        if(next[0]==0) {
                            set.add(next[2]);
                        }
                        queue.offer(next);
                    }                              
                }
            }       
        }
    }
}