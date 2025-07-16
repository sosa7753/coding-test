import java.util.*;
class Solution {
    List<int[]> list = new ArrayList<>();
    public int[][] solution(int n) {
        hanoi(1, 2, 3, n);
        
        int[][] answer = new int[list.size()][2];
        int idx = 0;
        for(int[] l : list) {
            answer[idx++] = l; 
        }
        return answer;
    }
    
    public void hanoi(int start, int mid, int end, int cnt) {
        if(cnt == 0) {
            return;
        }
        
        hanoi(start, end, mid, cnt-1);
        list.add(new int[]{start, end});
        hanoi(mid, start, end, cnt-1);
    }
}