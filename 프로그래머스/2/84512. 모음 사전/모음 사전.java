import java.util.*;
class Solution {
    public int solution(String word) {
        int answer = 0; 
        // 전체 문자열 5^5 + 5^4 + 5^3 + 5^2 + 5^1 = 3125 + 625 + 125 + 25 + 5 = 3905
        int total = 3905;
        
        String str = "AEIOU";
        
        String[] arr = word.split("");
        for(String s : arr) {
            total /= 5;
            answer += total * str.indexOf(s) + 1; 
        }
        return answer;
    }
}