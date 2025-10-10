class Solution {
    int[][] map;
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        
        map = new int[n+1][m+1];
        for(int[] s : skill) {
            int power = (s[0] == 1) ? -s[5] : s[5]; 
            
            int r1 = s[1]; int c1 = s[2]; int r2 = s[3]; int c2 = s[4];
            map[r1][c1] += power;
            map[r1][c2+1] -= power;
            map[r2+1][c1] -= power;
            map[r2+1][c2+1] += power;
        }
        
        for(int i=1; i<n; i++) { // 상하 합산 
            for(int j=0; j<m; j++) {
                map[i][j] += map[i-1][j];
            }
        }
        
        for(int i=1; i<m; i++) {
            for(int j=0; j<n; j++) {
                map[j][i] += map[j][i-1];
            }
        }
        
        int answer = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j] + board[i][j] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
}