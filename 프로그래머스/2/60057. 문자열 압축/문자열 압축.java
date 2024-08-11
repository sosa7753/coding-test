class Solution {
    int answer;
    int min;
    public int solution(String s) {
        answer = 0;
        min = Integer.MAX_VALUE;
        
        for(int i=1; i<=s.length(); i++) {
            DFS(s, "", 0, 1, i);
            if(answer < min) {
                min = answer;
            }
            answer = 0;
        }
        return min;
    }
    
    // 총 문자열, 이전문자열, 문자열 시작 인덱스, 반복횟수 , 압축 단위
    public void DFS(String s, String pre, int idx, int cnt, int len) {
         
        // 문자열 범위를 벗어날 경우 
        if(idx >= s.length()) {  
            if(cnt >= 2) {
                answer += String.valueOf(cnt).length();
            } 
            return;
        }
        
        // 마지막 문자열 길이가 다를 경우 
        if(s.length() - idx < len) {
            if(cnt >=2) {
                answer += String.valueOf(cnt).length();
            }
            answer += s.length() - idx;
            return;     
        }
             
        // 이전 값과 같다면 반복횟수 증가 
        if(pre.equals(s.substring(idx, idx+len))) {
            DFS(s, s.substring(idx, idx+len), idx+len, cnt+1, len);          
        }else {
            // 이전-이전에는 맞는 경우
            if(cnt >=2) {
                answer += String.valueOf(cnt).length() + len;
            }else {
                answer += len;
            }
            DFS(s, s.substring(idx, idx+len), idx+len, 1, len);
        }
    }
}