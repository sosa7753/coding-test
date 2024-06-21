class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int max = brown + yellow;
        // x * y = brown + yellow
        // x + y = (brown + 4)/2
        
        for(int i=1; i<=max; i++) {
            if(max%i != 0) {
                continue;
            }
            
            int second = max/i;
            if(i + second == (brown+4)/2) {
               answer[0] = Math.max(i, second);
               answer[1] = Math.min(i, second);
               break;
            }             
        }
        return answer;
    }
}