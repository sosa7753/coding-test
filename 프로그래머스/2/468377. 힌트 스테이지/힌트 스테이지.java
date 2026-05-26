class Solution {
    int answer = Integer.MAX_VALUE;
    int[] arr;
    public int solution(int[][] cost, int[][] hint) {  
        int stage = cost.length;
        arr = new int[stage]; 
        
        // DFS로 현재 스테이지에서 힌트권을 살지 말지 분기
        DFS(cost, hint, 0, 0); 
        return answer;
    }
    
    public void DFS(int[][] cost, int[][] hint, int now, int w) {
        if(now == cost.length) {
            answer = Math.min(answer, w);
            return;
        }
        
        // 깨기 위한 최소 비용
        int cnt = Math.min(arr[now], cost[now].length -1); // 힌트권 최대 사용 수
        int nw = w + cost[now][cnt];     
        arr[now] -= cnt;
                     
        if(now < hint.length) { // 마지막 스테이지가 아닐 경우
            nw += hint[now][0];
            for(int i=1; i<hint[now].length; i++) {
                arr[hint[now][i]-1]++;
            }
        
            DFS(cost, hint, now+1, nw); // 힌트권 사고 보내기
        
            nw -= hint[now][0];
            for(int i=1; i<hint[now].length; i++) {
                arr[hint[now][i]-1]--;
            }
        }  
        
         DFS(cost, hint, now+1, nw); // 안사고 보내기
         arr[now] += cnt;
    }
}