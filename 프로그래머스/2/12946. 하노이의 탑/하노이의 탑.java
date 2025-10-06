import java.util.*;
class Solution {
    List<int[]> result = new ArrayList<>();
    public int[][] solution(int n) {
  
        hanoi(1, 2, 3, n);
        
        int[][] answer = new int[result.size()][2];
        int idx = 0;
        for(int[] r : result) {
            answer[idx++] = r;
        }
        return answer;
    }
    
    public void hanoi(int s, int m, int e, int h) {
        if(h == 0) {
            return;
        }
        
        hanoi(s, e, m, h-1);
        result.add(new int[]{s, e});
        hanoi(m, s, e, h-1);
    }
}