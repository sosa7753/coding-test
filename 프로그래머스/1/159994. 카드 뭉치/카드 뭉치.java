import java.util.*;
class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "";
        
        int p1=0;
        int p2=0;
        boolean ok = true;
        for(int i=0; i<goal.length; i++) {
            String s = goal[i];
            
            if(p1 < cards1.length && s.equals(cards1[p1])) {
                p1++;
                continue;
            }
            
            if(p2 < cards2.length && s.equals(cards2[p2])) {
                p2++;
                continue;
            }
            
            ok = false;
            break;
        }
        
        if(ok) {
            answer = "Yes";
        }else {
            answer = "No";
        }
        return answer;
    }
}