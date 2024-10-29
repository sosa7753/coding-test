import java.util.*;
class Solution {
    public int solution(int[] a) {
        int L = Integer.MAX_VALUE;
        int R = Integer.MAX_VALUE;
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<a.length; i++) {
            L = Math.min(L, a[i]);
            R = Math.min(R, a[a.length-1-i]);
            set.add(L);
            set.add(R);
        }
        return set.size();
    }
}