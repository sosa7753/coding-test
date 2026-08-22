import java.util.*;
class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        
        long max = 1;
        List<Integer> list = new ArrayList<>();
        for(int i=1; i<=n; i++) {
            list.add(i);
            max *= i;
        }
        
        k--;
        int idx = 0;
        while(idx < n) {
            max = max/(n-idx);
            answer[idx++] = list.remove((int)(k/max));
            k %= max;
        }
        
        return answer;
    }
}