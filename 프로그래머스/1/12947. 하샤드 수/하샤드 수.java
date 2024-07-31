class Solution {
    public boolean solution(int x) {        
        int div = 0;
        String s = String.valueOf(x);
        
        for(int i=0; i<s.length(); i++) {
            div += s.charAt(i) - '0';
        }
        
        if(x%div == 0) {
            return true;
        }
        
        return false;
    }
}