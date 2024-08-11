class Solution {
    public int solution(String s) {
        int answer = s.length();
        
        for(int i=1; i<=s.length()/2; i++) {
            answer = Math.min(answer, check(s, i));
        }
        
        return answer;
    }
    
    public int check(String s, int len) {
        int result = 0;
        
        int max = (s.length()/len) * len; 
               
        int count = 1;
        String start = s.substring(0,len);
        
        for(int i=len; i<max; i= i+len) {
            String str = s.substring(i, i+len);
            if(start.equals(str)) {
                count++;
                continue;
            }
            
            if(count >= 2) {
                result += String.valueOf(count).length();
            }
            count = 1;
            result += len;
            start = str;
        }
        
        if(count >=2) {
            result += String.valueOf(count).length();
        }
        result += start.length();
        result += s.length() - max;  
        
        return result;
    }
}