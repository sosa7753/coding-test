class Solution {
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        
        String value = board[h][w];
        for(int i=0; i<4; i++) {
            int nr = h + dr[i];
            int nc = w + dc[i];
            
            if(nr < 0 || nr > board.length-1 || nc < 0 || nc > board[0].length-1) {
                continue;
            }
            
            if(value.equals(board[nr][nc])) {
                answer++;
            }
        }
        return answer;
    }
}