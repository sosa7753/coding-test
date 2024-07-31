class Solution {
    public boolean solution(String s) {
        s = s.toUpperCase();
        
        int p = 0;
        int y = 0;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == 'P') {
                p++;
                continue;
            }
            
            if(s.charAt(i) == 'Y') {
                y++;
            }
        }
        
        if(p == y) {
            return true;
        }
        
        return false;
    }
}