class Solution {
    public String solution(String new_id) {
        String answer = new_id;
        
        // 대문자 -> 소문자
        answer = answer.toLowerCase();
        
        // 특정 조건외에 문자 삭제 
        answer = answer.replaceAll("[^a-zA-Z0-9-_.]", "");
        
        // .이 2번 이상 연속된 부분을 .하나로 대체
        answer = answer.replaceAll("(\\.)\\1+", "$1");
        
        // .이 처음이나 끝이면 제거 
        if(!"".equals(answer)) {    
            answer = answer.replaceAll("^\\.|\\.$", "");
        }
        
        // 빈 문자열이면 "a" 를 대입
        if("".equals(answer)) {
            answer = "a";
        }
        
        // 16자 이상이면 15개로 자름.
        if(answer.length() >= 16) {
            answer = answer.substring(0, 15);     
            // 끝 문자가 .이면 제거 
            answer = answer.replaceAll("\\.$", "");
        }
          
        // 길이가 2자 이하라면 마지막 문자를 길이가 3이 될때까지 붙임 
        int n = answer.length();
        if(n <=2) {
            StringBuilder sb = new StringBuilder();
            
            sb.append(answer);
            for(int i=1; i<= 3 - n; i++) {
                sb.append(answer.substring(n-1, n));
            }
            answer = sb.toString();
        }
        
        return answer;
    }
}