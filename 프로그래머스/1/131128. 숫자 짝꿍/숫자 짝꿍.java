import java.util.*;
class Solution {
    public String solution(String X, String Y) {       
        Map<Character, Integer> mapX = new HashMap<>();
        Map<Character, Integer> mapY = new HashMap<>();
        char[] cx = X.toCharArray();
        char[] cy = Y.toCharArray();
        
        for(char c : cx) {
            mapX.put(c, mapX.getOrDefault(c, 0) + 1);
        }
        
        for(char c : cy) {
            mapY.put(c, mapY.getOrDefault(c, 0) + 1);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=9; i>=0; i--) {
            char c = (char)(i + '0');
            if(!mapX.containsKey(c) || !mapY.containsKey(c)) {
                continue;
            }
            int cnt = Math.min(mapX.get(c), mapY.get(c));
            sb.append(String.valueOf(c).repeat(cnt));
        }
        
        String str = sb.toString();
        if("".equals(str)) {
            return "-1";
        }
         
        return str.charAt(0) == '0' ? "0" : str;
    }
}