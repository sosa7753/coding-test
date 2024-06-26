import java.util.*;
class Solution {
    public String solution(String m, String[] musicinfos) {
        StringBuilder sb;
       
        int clock = 0;
        int maxLen = 0;
        String tmp = "";
        
        String M = change(m);
           
        for(int i=0; i<musicinfos.length; i++) {  
            String[] s = musicinfos[i].split(",");
            
            clock = time(s[0]);           
            int gap = time(s[1]) - clock;
        
            int cnt = 0;
            int p = 0;
            sb = new StringBuilder();
            
            String str = change(s[3]);
            while(cnt < gap) {
                sb.append(String.valueOf(str.charAt(p)));
                  
                cnt++;
                p++;
                if(p == str.length()) {
                    p = 0;
                }
            }
            
            if(sb.toString().contains(M)) {
                if(maxLen < gap) {
                    maxLen = gap;
                    tmp = s[2];
                }
            }         
        }
            
        if("".equals(tmp)) {
            return "(None)";
        }else {
            return tmp;
        }        
    }
    
    public int time(String clock) {
        String[] str = clock.split(":");
        
        return Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
    } 
    
    public String change(String s) {
        String result = s;
        // A# C# D# F# G# 
        String[] origin = {"A#","B#","C#", "D#", "F#", "G#"};
        String[] next = {"H", "I", "J", "K", "L", "M"};
        for(int i=0; i<origin.length; i++) {
            result = result.replace(origin[i], next[i]);
        }
        return result;
    }
}