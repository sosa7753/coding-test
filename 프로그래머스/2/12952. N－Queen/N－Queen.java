// 1차원 배열로 0~n-1로 만들어줌.
// idx 행에서 숫자를 채워 나가서 모두 채워지면 ++

class Solution {
    int[] board;
    int answer;
    public int solution(int n) {
        answer = 0;
        
        board = new int[n];
        
        nQueen(0,n);
        return answer;
    }
    
    public void nQueen(int row, int n) {
        if(row == n) {
            answer++;
            return;
        }
        
        for(int i=0; i<n; i++) {
            board[row] = i;
            
            if(check(row)) {
            nQueen(row+1,n);
            }   
        }      
    }
    
    public boolean check(int row) {
        for(int i=0; i<row; i++) {
            if(board[row] == board[i] || row - i == Math.abs(board[row] - board[i])) {
                return false;
            }
        }
        return true;
    } 
}