import java.util.*;
class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<s.length(); i++) {
            int cnt = 1;
            char c = s.charAt(i);
            
            while(cnt <= index) {
                if(c == 'z') {
                    c = 'a';
                }else {
                    c = (char)(c+1);
                }
                
                if(!skip.contains(String.valueOf(c))) {
                    cnt++;
                }
            }
            sb.append(String.valueOf(c));
        }
              
        return sb.toString();
    }
}