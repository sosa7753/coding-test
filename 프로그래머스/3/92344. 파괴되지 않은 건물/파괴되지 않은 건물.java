class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        int[][] S = new int[board.length+1][board[0].length+1];
        for(int i=0; i<skill.length; i++) {
            int power = skill[i][5];
            if(skill[i][0] == 1) {
                power = -power;
            }
            
            // 더하는 시작에 power, 더하는 끝에 -power
            S[skill[i][1]][skill[i][2]] += power;
            S[skill[i][1]][skill[i][4]+1] += -power;
            S[skill[i][3]+1][skill[i][2]] += -power;
            S[skill[i][3]+1][skill[i][4]+1] += power;                        
        }
              
        // 누적합 당겨오기 
        // 상하 합산
        for(int i=1; i<S.length; i++) {
            for(int j=0; j<S[0].length; j++) {
                S[i][j] += S[i-1][j];
            }
        }
        
        // 좌우 합산 
        for(int i=1; i<S[0].length; i++) {
            for(int j=0; j<S.length; j++) {
                S[j][i] += S[j][i-1];
            }
        }
        
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                if(board[i][j] + S[i][j] > 0) {
                    answer++;
                }
            }
        }
                
        return answer;
    }
}