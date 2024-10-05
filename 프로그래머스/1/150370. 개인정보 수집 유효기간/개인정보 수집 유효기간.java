import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<terms.length; i++) {
            String[] s = terms[i].split(" ");
            map.put(s[0], Integer.parseInt(s[1]));
        }
        
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<privacies.length; i++) {
            String[] str = privacies[i].split(" ");
            int plus = map.get(str[1]);
            
            if(!check(today, str[0], plus)) {
               list.add(i+1);
            }
        }
        
        int[] answer = new int[list.size()];
        for(int i=0; i<answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
    
    public boolean check(String today, String pre, int save) {        
        String[] t = today.split("\\.");
        String[] p = pre.split("\\.");
               
        int gap = 0;
        
        // 년도 
        gap += (Integer.parseInt(t[0]) - Integer.parseInt(p[0]))*12;
        
        // 월 
        gap += Integer.parseInt(t[1]) - Integer.parseInt(p[1]);
        
        // 일 
        int extra = Integer.parseInt(t[2]) - Integer.parseInt(p[2]);
        
        if(gap < save) {
            return true; // 괜춘
        }else if(gap == save) {
            if(extra < 0) {
                return true;
            }
        }
        return false;
     }   
}