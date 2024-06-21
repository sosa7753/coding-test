import java.util.*;
class Solution {
    public int solution(int []A, int []B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);
        
        int min = Math.min(A.length, B.length);
        for(int i=0; i<min; i++) {
            answer += A[i] * B[min-1 - i];
        }

        return answer;
    }
}