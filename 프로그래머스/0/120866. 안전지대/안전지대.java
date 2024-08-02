class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        
        int n = board.length;
        
        boolean[][] visited = new boolean[n][n];
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(board[i][j] == 1) {                
                    for(int k= Math.max(0, i-1); k<=Math.min(n-1, i+1); k++) { 
                        for(int w= Math.max(0, j-1); w<=Math.min(n-1, j+1); w++) {
                            visited[k][w] = true; 
                        }
                    }
                } 
            }
        }
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(!visited[i][j]) {
                    answer++;
                }
            }
        }
        return answer;
    }
}