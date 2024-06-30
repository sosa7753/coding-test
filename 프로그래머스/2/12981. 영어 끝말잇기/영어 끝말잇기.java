import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] result = new int[2];
        Map<String, Integer> map = new HashMap<>();
        
        int[] answer = new int[n]; // n번째 사람의 차례 값.
        
        int cnt = 0;
        String pre = words[0].substring(0,1); 
        for(int i=0; i<words.length; i++) {
            // 먼저 차례 높여주기 
            answer[cnt%n]++;
            
            // 끝 값이 다르거나 map에 포함됨.
            if(pre.charAt(pre.length()-1) != words[i].charAt(0) || 
               map.containsKey(words[i])) {
                
               result[0] = cnt%n + 1;
               result[1] = answer[cnt%n];
               return result;             
            }
            
            pre = words[i];
            map.put(words[i], 1);
            cnt++;
        }

        return new int[] {0,0};
    }
}