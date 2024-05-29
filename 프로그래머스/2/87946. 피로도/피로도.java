class Solution {
    int max;
    public int solution(int k, int[][] dungeons) {
        
        boolean[] visited = new boolean[dungeons.length];
        
        DFS(k, dungeons, visited, 0);
        return max;
    }
    
    public void DFS(int k, int[][] dungeons, boolean[] visited, int cnt) {
        if(cnt == dungeons.length) {
            max = Math.max(max, cnt);
            return;
        }
        
        // 1~8번째 던전 
        for(int i=0; i<dungeons.length; i++) {
            // 던전을 돌 수 있으면 들어가기
            if(visited[i]) {
                continue;
            }
            
            if(k >= dungeons[i][0]) {
                visited[i] = true;
                DFS(k-dungeons[i][1], dungeons, visited, cnt+1);
                visited[i] = false;
            }
        }
        max = Math.max(max, cnt);       
    }
}