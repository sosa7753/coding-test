class Solution {
    int answer = 0;
    public int solution(int n) {       
        DFS(0, 0, 0, n);
        return answer;
    }
    
    public void DFS(int cnt, int plus, int minus, int n) {
        if(plus == n || cnt == 2*n) {
            answer++;
            return;
        }
        
        if(plus < minus) {
            return;
        }
        
        DFS(cnt+1, plus+1, minus, n);
        DFS(cnt+1, plus, minus+1, n);       
    }
}