class Solution {
    int answer = Integer.MAX_VALUE;
    int n;
    public int solution(int[][] cost, int[][] hint) {
        n = cost.length;
        int[] ticket = new int[n];
        
        DFS(cost, hint, ticket, 0, 0); 
        // 2^15로 각 스테이지에서 살지 말지 정할 수 있음
        
        return answer;
    }
    
    public void DFS(int[][] cost, int[][] hint, int[] ticket, int cnt, int v) {
        if(cnt == n) {
            answer = Math.min(answer, v);
            return;
        }
        
        int idx = ticket[cnt] > n-1 ? n-1 : ticket[cnt];
        
        v += cost[cnt][idx];
        
        // 구매하기 -> 복구
        if(cnt < n-1) {
            v += hint[cnt][0];
            for(int i=1; i<hint[cnt].length; i++) {
                ticket[hint[cnt][i]-1]++;               
            }
            
            DFS(cost, hint, ticket, cnt+1, v);
        
            v -= hint[cnt][0];
            for(int i=1; i<hint[cnt].length; i++) {
                ticket[hint[cnt][i]-1]--;
            }
        }

        DFS(cost, hint, ticket, cnt+1, v);     
    }
}