class Solution {
    int answer = 0;
    public int solution(int n) {       
        // ( 14개 사용 가능 
        // ) 14개 사용 가능 
        // DFS로 만들어서 세자 백트래킹 주의
        DFS(0, 0, 0, n);
        return answer;
    }
    
    public void DFS(int cnt, int open, int close, int n) {
        if(open == n || cnt == 2 * n) {
            answer++;
            return;
        }
        
        if(close > open) {
            return;
        }
        
        DFS(cnt+1, open+1, close, n);
        DFS(cnt+1, open, close+1, n);
    }
}