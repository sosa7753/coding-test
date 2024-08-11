import java.util.*;
class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        
        int cnt = score.length;
        
        Arrays.sort(score);
        
        int idx = score.length - m;
        while(cnt >= m) {
            answer += score[idx] * m;
            idx -= m;
            cnt -= m;
        }
        return answer;
    }
}