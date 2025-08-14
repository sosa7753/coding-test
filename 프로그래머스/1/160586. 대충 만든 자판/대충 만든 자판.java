import java.util.*;
class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        
        // Keymap을 Map에 저장해두자. (이 문자는 몇번이 최소)
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<keymap.length; i++) {
           char[] c = keymap[i].toCharArray();
           for(int j=0; j<c.length; j++) {
               if(!map.containsKey(c[j])) {
                   map.put(c[j], j+1);
               }else {
                   map.put(c[j], Math.min(map.get(c[j]), j+1));
               }
           }
        }
        
        int[] answer = new int[targets.length];
        for(int i=0; i<targets.length; i++) {
            char[] c = targets[i].toCharArray();
            int cnt = 0;
            for(int j=0; j<c.length; j++) {
                if(!map.containsKey(c[j])) {
                    cnt = -1;
                    break;
                }else {
                    cnt += map.get(c[j]);
                }
            }
            answer[i] = cnt;
        }
        return answer;
    }
}