import java.util.*;
class Solution {
    public int solution(int[][] scores) {
        int answer = 1;
        
        int[] s = scores[0];
        Arrays.sort(scores, (x,y) -> {
            if(x[0] == y[0]) {
                return x[1] - y[1];
            }else {
                return y[0] - x[0];
            }
        });
        
        int max = 0;
        for(int[] score : scores) {
            if(score[1] < max) {
                if(score.equals(s)) {
                    return -1;
                }
                continue;
            }
            
            max = Math.max(max, score[1]);
            if((s[0] + s[1]) < score[0] + score[1]) {
                answer++;
            }
        }
        
        return answer;
    }
}