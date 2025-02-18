import java.io.*;
import java.util.*;
class Main {
    static int N, M;
    static int[] people;
    static int[] task; 
    static List<List<Integer>> list = new ArrayList<>();
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        people = new int[N+1];
        task = new int[M+1];
        
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=1; i<=N; i++) {
            String[] str = br.readLine().split(" ");
            for(int j=0; j<str.length; j++) {
                if(j == 0) {
                   people[i] = Integer.parseInt(str[j]);
                }else {
                   int v = Integer.parseInt(str[j]);
                   task[v]++;
                   list.get(i).add(v);
                }               
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) ->(y[1] - x[1]));
        for(int i=1; i<people.length; i++) {
            pq.offer(new int[]{i, people[i]});
        }

        int idx = 1;
        while(!pq.isEmpty()) {
            int[] p = pq.poll();
            int pnum = p[0];
   
            int[] tmp = Arrays.copyOf(task, task.length);
            Arrays.sort(tmp);  
            for(int j=idx; j<tmp.length; j++){
                if(tmp[j] == 0) {
                   idx++;
                }else {
                    answer++;
                    idx++;
                    for(int k=0; k<list.get(pnum).size(); k++) {
                        int t = list.get(pnum).get(k);
                        task[t]--;
                    }
                    break;
                }
            }    
        }  
        System.out.print(answer);
    }  
}