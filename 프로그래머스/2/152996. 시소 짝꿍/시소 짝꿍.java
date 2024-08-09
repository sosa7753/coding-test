import java.util.*;
class Solution {
    int w;
    public long solution(int[] weights) {
        w = weights.length;
        long answer = 0;
        
        Arrays.sort(weights);
        
        int prev = 0;
        for(int i=0; i< w-1; i++) {
            if(i > 0 && weights[i] == weights[i-1]) { // 중복체크
                prev--;
                answer+=prev;
                continue;
            }
            
            prev = 0;
            for(int j=i+1; j<w; j++) {
                if(weights[i] * 2 < weights[j]) {
                    break;
                }
                
                if(weights[i] == weights[j] || weights[i] * 2 == weights[j] ||
                   weights[i] * 3 == weights[j] * 2 || 
                   weights[i] * 4 == weights[j] * 3) {
                    prev++;
                }            
            }
            answer+=prev;            
        }
        return answer;
    }
}