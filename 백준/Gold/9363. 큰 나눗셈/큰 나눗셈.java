import java.util.*;
import java.io.*;
class Main {
    static int MAX = 1000000;
    static int[] min = new int[MAX+1]; // 최소 소인수 배열, 소수면 자기 자신
    static Map<Integer, Integer> map;
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st;
        
       min[1] = 1; 
       for(int i=2; i<=MAX; i++) {
           if(min[i] != 0) continue;
           
           min[i] = i;
           long start = (long)i*i;
           if(start > MAX) continue;    
           for(int j=(int)start; j<=MAX; j +=i) {
               if(min[j] == 0) {
                   min[j] = i;
               }
           }
       }
        
       int T = Integer.parseInt(br.readLine());
       for(int i=0; i<T; i++) {
           map = new HashMap<>();
           
           st = new StringTokenizer(br.readLine());
           int N = Integer.parseInt(st.nextToken());
           int M = Integer.parseInt(st.nextToken());
           
           st = new StringTokenizer(br.readLine());
           for(int j=0; j<N; j++) {
               addMap(Integer.parseInt(st.nextToken()), map, 1);
           }
           
           st = new StringTokenizer(br.readLine());
           for(int j=0; j<M; j++) {
               addMap(Integer.parseInt(st.nextToken()), map, -1);
           }
           
           int[] result = cal();
           
           StringBuilder sb = new StringBuilder();
           sb.append("Case #").append(i+1).append(": ");
           sb.append(result[0]).append(" / ").append(result[1]);
           System.out.println(sb.toString());
       }
    } 
    
    public static int[] cal() {
        int up = 1;
        int down = 1;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() == 0) continue;
            
            int key = entry.getKey();
            int value = entry.getValue();
            
            if(value > 0) {
                up *= (int)Math.pow(key, value);
            }else {
                down *= (int)Math.pow(key, Math.abs(value));
            }
        }
        return new int[]{up, down};
    }
    
    public static void addMap(int val, Map<Integer,Integer> map, int flag) {  
        if(val <= 1) return;
        
        while(val > 1) {
            int p = min[val];
            int cnt = 0;
            while(val % p == 0) {
                val /=p;
                cnt++;
            }
       
            map.put(p, map.getOrDefault(p, 0) + flag*cnt);
        }
    }
}