import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        
        // 바뀌는 값은 부른 사람/앞 사람 -> 내 등수 앞 사람을 알아야함 -> 배열
        // 빠르게 내 등 수 찾기 Map
        
        int n = players.length;
        
        Map<String, Integer> map = new HashMap<>();            
        for(int i=0; i<n; i++) {
            map.put(players[i], i);  
        }
        
        for(String call : callings) {
            int score = map.get(call);
            String front = players[score-1];
            // 배열 스왑
            players[score] = front;
            players[score-1] = call;
            
            // Map 스왑
            map.put(call, score-1);
            map.put(front, score);
        }
        
        return players;
    }
}