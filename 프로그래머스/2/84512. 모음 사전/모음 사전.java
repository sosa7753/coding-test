class Solution {
    public int solution(String word) {
        int answer = 0;
        int total = 3905;
        String str = "AEIOU";
        
        String[] arr = word.split("");
        
        for(String s : arr) {
            total /= 5;
            answer += total*str.indexOf(s) + 1;
        }
        return answer;
    }
}