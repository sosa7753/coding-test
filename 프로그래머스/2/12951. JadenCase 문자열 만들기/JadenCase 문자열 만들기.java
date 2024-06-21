class Solution {
    public String solution(String s) {        
        
        s =  s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        
        boolean first = true;
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if('a' <= c && c <= 'z' && first) { // 소문자면 
                String sc = String.valueOf(c).toUpperCase();
                sb.append(sc);
                first = false;
                continue;
            }
            
            if(c == ' ') {
                first = true;
                sb.append(c);
                continue;
            }
            
            first = false;
            sb.append(c);
        }
        return sb.toString();
    }
}