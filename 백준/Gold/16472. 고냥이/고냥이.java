import java.util.*;
import java.io.*;
class Main {
    static int N;
    static String str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        N = Integer.parseInt(br.readLine());
        str = br.readLine();
        
        int p1 = 0;
        int p2 = 0;
    
        Map<Character, Integer> map = new HashMap<>();
        int answer = 0;
        while(p1<=p2 && p2<str.length()) {
            char c = str.charAt(p2);
            map.put(c, map.getOrDefault(c, 0) + 1);
            
            while(map.size() > N) {
                char c2 = str.charAt(p1);
                map.put(c2, map.get(c2) - 1);
                if(map.get(c2) == 0) {
                    map.remove(c2);
                }
                p1++;
            }
            answer = Math.max(answer, p2 - p1 + 1);
            p2++;
        }
        System.out.print(answer);    
    }
}