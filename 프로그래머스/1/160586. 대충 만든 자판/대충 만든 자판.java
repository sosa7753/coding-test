import java.util.*;
class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        
        // 알파벳 최초 등장 시점 
        Map<Character, Integer> map = new HashMap<>();
        
        // Map 에 초기값 저장
        for(int i=0; i<keymap.length; i++) {    
            String s = keymap[i];
            
            for(int j=0; j<s.length(); j++) {
                if(map.containsKey(s.charAt(j))) { // 현재 맵에 key가 이미 존재할 때
                    map.put(s.charAt(j), Math.min(map.get(s.charAt(j)), j+1));
                    continue;
                }
                
                // 맵에 key가 없을 때 
                map.put(s.charAt(j), j+1);
            }
        }
        
        int[] answer = new int[targets.length];
        int idx = 0;
        
        for(int i=0; i<targets.length; i++) { // targets 반복
            int sum = 0;
            boolean istrue = false;
            
            for(int j=0; j<targets[i].length(); j++) { // 문자열 반복
                
               if(!map.containsKey(targets[i].charAt(j))) { // Map에 미 존재 
                   answer[idx++] = -1;
                   istrue = true;
                   break;
               }
                
               sum += map.get(targets[i].charAt(j));
            }
            // 모두 존재 했을 때
            if(!istrue) {
                answer[idx++] = sum;
            }
        }      
        return answer;
    }
}