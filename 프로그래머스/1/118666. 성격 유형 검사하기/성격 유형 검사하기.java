import java.util.*;
class Solution {
    Map<Character, Integer> map = new HashMap<>();
    char[] left = {'R', 'C', 'J', 'A'};
    char[] right = {'T', 'F', 'M', 'N'};
    public String solution(String[] survey, int[] choices) {  
        init();
        for(int i=0; i<survey.length; i++) {
            char l = survey[i].charAt(0);
            char r = survey[i].charAt(1);
            cal(l, r, choices[i]);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<4; i++) {
            if(map.get(left[i]) >= map.get(right[i])) {
                sb.append(left[i]);
            }else {
                sb.append(right[i]);
            }
        }
    
        return sb.toString();
    }
    
    public void cal(char l, char r, int choice) {
        if(choice == 4) {
            return;
        }
        
        if(1 <= choice && choice <= 3) {
           map.put(l, map.get(l) + 4-choice);
        }else {
           map.put(r, map.get(r) + choice - 4);
        }
    }
    
    public void init() {
        for(int i=0; i<4; i++) {
            map.put(left[i], 0);
            map.put(right[i], 0);
        }
    }
}