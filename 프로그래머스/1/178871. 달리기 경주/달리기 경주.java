import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        int p = players.length;
        String[] answer = new String[p];
        
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<p; i++) {
            answer[i] = players[i];
            map.put(players[i], i);
        }
        
        for(int i=0; i<callings.length; i++) {
            int now = map.get(callings[i]);
            
            String who = answer[now-1];
            
            // map 순위 변경
            map.put(callings[i], now -1);
            map.put(who, now);
            
            // 배열 순위 변경
            String n = callings[i];
            answer[now] = who;
            answer[now-1] = n;                
        }
        return answer;
    }
}