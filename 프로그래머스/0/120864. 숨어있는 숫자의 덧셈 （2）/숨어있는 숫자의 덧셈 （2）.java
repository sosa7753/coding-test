class Solution {
    public int solution(String my_string) {
        int answer = 0;
        
        int p1 = 0;
        int p2 = 0;
        while(p2 < my_string.length()) {
            char c = my_string.charAt(p2);
            
            if(Character.isDigit(c)) { // 숫자면 
                p2++;
                if(p2 == my_string.length()) {
                    answer += Integer.parseInt(my_string.substring(p1, p2));
                }
            }else { // 문자면
                if(p1 != p2) {
                    answer += Integer.parseInt(my_string.substring(p1, p2));
                }
                p2++;
                p1 = p2;
            }
        }
        return answer;
    }
}