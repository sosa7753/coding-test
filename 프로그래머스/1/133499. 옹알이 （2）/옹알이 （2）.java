class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        
        for(int i=0; i<babbling.length; i++) {
            String s = change(babbling[i]);
            char pre = 'T';
            for(int j=0; j<s.length(); j++) {
                char c = s.charAt(j);
                if(c >= 'a' && c <='z') {
                    break;
                }
                
                if(pre == c) {
                    break;
                }
                pre = c;
                
                if(j == s.length() -1) {
                    answer++;
                }
            }
        }
        return answer;
    }
    
    public String change(String str) {
        String[] s1 = {"aya", "ye", "woo", "ma"};
        String[] s2 = {"A", "B", "C", "D"};
        
        for(int i=0; i<4; i++) {
            str = str.replace(s1[i], s2[i]);
        }
        return str;
    }
}