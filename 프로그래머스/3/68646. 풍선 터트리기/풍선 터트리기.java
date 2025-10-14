import java.util.*;
class Solution {
    public int solution(int[] a) {
        int n = a.length;
        
        int L = Integer.MAX_VALUE;
        int R = Integer.MAX_VALUE;
        // Set에 가능한 값을 넣자.
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<n; i++) {
            L = Math.min(L, a[i]); // 왼쪽에서 작은 값이면 가능
            R = Math.min(R, a[n-1-i]); // 오른쪽에서도 작은 값이면 가능
            set.add(L);
            set.add(R);
        }
        return set.size();
    }
}