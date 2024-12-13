import java.util.*;
import java.io.*;
class Main {
    static List<List<Integer>> list = new ArrayList<>();
    static int N;
    static int M;
    static int answer = Integer.MAX_VALUE;
    static Set<Integer> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
 
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.get(f).add(e);
            list.get(e).add(f);
        }
        
        for(int i=1; i<=N; i++) {
            int tmp = list.get(i).size();
            if(tmp < 2) {
                continue;
            }
            set = new HashSet<>();
            set.add(i);
            DFS(0, i, tmp);
        }
           
        if(answer == Integer.MAX_VALUE) {
            System.out.print(-1);
        }else {
            System.out.print(answer-6);
        }
    }
    
    public static void DFS(int depth, int now, int result) {      
        if(depth == 2) {
            answer = Math.min(result, answer);
            return;
        }
        
        for(int i=0; i<list.get(now).size(); i++) {
            int next = list.get(now).get(i);
            if(next <= now) {
                continue;
            }
            
            if(check(next)) {
                set.add(next);
                DFS(depth+1, next, result + list.get(next).size());
                set.remove(next);
            }else {
                if(set.size() == 3) {
                    answer = Math.min(result, answer);
                }
            }          
        }    
    }
    
    public static boolean check(int value) {
        List<Integer> l = list.get(value);
        for(int s : set) {
            if(!l.contains(s)) {
                return false;
            }
        }
        return true;
    }
}