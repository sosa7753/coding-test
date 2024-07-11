class Solution {
    public int solution(String s) {
        int answer = 0;
        
        if(s.length() == 1) {
            return 1;
        }
        
        char ok = s.charAt(0);
        int in = 1;
        int out = 0;
        int p = 1;
        while(p < s.length()) {
            if(in == 0 && out == 0) {
                ok = s.charAt(p);
                p++;
                in++;
                continue;
            }
                
            if(ok == s.charAt(p)) {
                in++;
            }else {
                out++;
            }
            if(in == out) {
                answer++;
                in = 0;
                out = 0;
                System.out.print(s.charAt(p) + " ");
            }
            p++;
        }
        
        if(in != out) {
            return answer + 1;
        }
        return answer;
    }
}