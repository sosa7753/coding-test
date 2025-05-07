class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        
        long pos = 0;
        long nav = 0;
        
        for(int i=0; i<sequence.length; i++) {
            pos += i%2 == 0 ? sequence[i] : -sequence[i];
            nav += i%2 == 0 ? -sequence[i] : sequence[i];
            
            pos = Math.max(0, pos);
            nav = Math.max(0, nav);
            
            answer = Math.max(answer, Math.max(nav, pos));
        }
        return answer;
    }
}