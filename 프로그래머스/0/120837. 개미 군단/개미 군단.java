class Solution {
    public int solution(int hp) {
        int answer = 0;
        
        int tmp = hp;
        
        for(int i=5; i>=1; i = i - 2) {
            answer += tmp/i;
            tmp = tmp%i;
            
            if(tmp == 0) {
                break;
            }
        }
        return answer;
    }
}