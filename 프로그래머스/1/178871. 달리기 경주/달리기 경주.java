import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        int p = players.length;
     String[] answer = new String[p];
    
     for(int i=0; i<p; i++) {
         answer[i] = players[i];
     }
        
     // 현재 순위표
     Map<String, Integer> mapA = new HashMap<>();
    
     // 초기 순위 저장
     for(int i=0; i<p; i++) {
        mapA.put(players[i], i);
     }
      
     // 호출만큼 순회
     for(int i=0; i<callings.length; i++) {
        int number = mapA.get(callings[i]); // 호출 순위
        String str = answer[number-1]; // 호출한 사람보다 앞 순위 선수
        
        answer[number] = str;
        answer[number-1] = callings[i];

        mapA.put(str, number);
        mapA.put(callings[i], number-1);
          
    }
    return answer;
    }
}