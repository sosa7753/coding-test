import java.util.*;
class Solution {
    List<int[]> list = new ArrayList<>();
    public int[][] solution(int n) {            
        hanoi(1, 2, 3, n);
        
        int[][] answer = new int[list.size()][2];
        for(int i=0; i<list.size(); i++) {
            int[] now = list.get(i);
            
            answer[i][0] = now[0];
            answer[i][1] = now[1];
        }
        
        return answer;
    }
    
    public void hanoi(int start, int mid, int end, int cnt) {
        if(cnt == 0) {
            return;
        }
        
        hanoi(start, end, mid, cnt-1); // s e m 일때 끝나는 로직
        list.add(new int[] {start, end});     
        hanoi(mid, start, end, cnt-1); // m s e 일 때 끝나는 로직 
    }
}