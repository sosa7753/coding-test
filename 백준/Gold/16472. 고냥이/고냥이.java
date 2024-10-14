import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());       
        String str = br.readLine();
        
        int p1 = 0;
        int p2 = 0;
        
        Map<Character, Integer> map = new HashMap<>();
        int answer = 0;
        while(p1 <= p2 && p2 < str.length()) {
            map.put(str.charAt(p2), map.getOrDefault(str.charAt(p2), 0) + 1);
            while(map.size() > N) {
                map.put(str.charAt(p1), map.get(str.charAt(p1)) -1);
                if(map.get(str.charAt(p1)) ==0) {
                    map.remove(str.charAt(p1));
                }
                p1++;
            }
            answer = Math.max(answer, p2 - p1 + 1);
            p2++;          
        }
        System.out.print(answer);
    }
}