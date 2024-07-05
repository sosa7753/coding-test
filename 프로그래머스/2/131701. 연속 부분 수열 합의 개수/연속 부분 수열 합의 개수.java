import java.util.*;
class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        
        int[] S = new int[elements.length];
        S[0] = elements[0];
        
        for(int i=1; i<elements.length; i++) {
            S[i] = S[i-1] + elements[i];
        }
        
        int max = S[S.length - 1];
        
        for(int i=0; i<S.length; i++) {          
            for(int j=i; j<S.length; j++) {
                if(i == 0) {
                    set.add(S[j]);
                    set.add(max - S[j]);
                }else {
                    set.add(S[j] - S[i-1]);
                    set.add(max - (S[j] - S[i-1]));
                }
            }
        }
        
        set.remove(0);     
        return set.size();
    }
}