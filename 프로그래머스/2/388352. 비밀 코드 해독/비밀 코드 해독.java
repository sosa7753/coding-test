import java.util.*;
class Solution {
    int answer = 0;
    public int solution(int n, int[][] q, int[] ans) {             
        int[] arr = new int[5];
        DFS(arr, q, ans, 0, 1, n);
        
        return answer;
    }
    
    public void DFS(int[] arr, int[][] q, int[] ans, int cnt, int start, int end) {
        if(cnt == arr.length) {
            if(check(arr, q, ans)) {
                answer++;
            }
            return;
        }
        
        for(int i=start; i<=end; i++) {
            arr[cnt] = i;
            DFS(arr, q, ans, cnt+1, i+1, end);
        }
    }
    
    public boolean check(int[] arr, int[][] q, int[] ans) {
        for(int i=0; i<q.length; i++) {
            Set<Integer> s = new HashSet<>();
            for(int j=0; j<q[i].length; j++) {
                s.add(arr[j]);
                s.add(q[i][j]);
            }
            if(s.size() != 10 - ans[i]) {
                return false;
            }
        }
        return true;
    }
}