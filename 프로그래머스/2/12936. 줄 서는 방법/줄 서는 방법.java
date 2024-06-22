import java.util.*;
class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        
        List<Integer> list = new ArrayList<>();
        
        long max = 1;
        for(int i=1; i<=n; i++) {
            list.add(i);
            max *= i;
        }
        
        k--;
        int idx = 0;
        while(idx < n) {
            max = max/(n - idx);
            answer[idx++] = list.remove((int)(k/max));
            k = k % max;
        }
        return answer;
    }
}