class Solution {
    public int[] solution(int e, int[] starts) {
        int[] cnt = new int[e+1];
    
        for(int i=1; i<=e; i++) {
            for(int j=i; j<=e; j += i) {
                cnt[j]++;
            }
        }
    
        int[] maxIdx = new int[e+1];
        maxIdx[e] = e;
        
        for(int i=e-1; i>=1; i--) { // i~e까지의 최댓값
            if(cnt[i] >= cnt[maxIdx[i+1]]) {
                maxIdx[i] = i;
            }else {
                maxIdx[i] = maxIdx[i+1];
            }
        }
        
        int[] answer = new int[starts.length];
        for(int i=0; i<answer.length; i++) {
            answer[i] = maxIdx[starts[i]];
        }  
        
        return answer;
    }
}