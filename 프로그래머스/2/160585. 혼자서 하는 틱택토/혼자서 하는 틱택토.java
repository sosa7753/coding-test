class Solution {
    public int solution(String[] board) {        
        if(check(board)) {
            return 1;
        }
        
        // X 개수가 많을 떄
        // O 개수가 2개 이상 많을 때 
        // O 빙고 == 1 && X 빙고 == 1 인 경우
        // O 빙고 == 1 && O 개수랑 X개수가 같을 때 
        // X 빙고 == 1 && O 가 1개더 많은 경우 
        return 0;
    }
    
    public boolean check(String[] board) {
        int cntO = 0;
        int cntX = 0;
        int clearO = 0;
        int clearX = 0;
        
        // 총 개수 , 행 빙고 세기, 열 빙고 세기 
        for(int i=0; i<board.length; i++) {
            int rowO = 0;
            int rowX = 0;
            int colO = 0; 
            int colX = 0;
            for(int j=0; j<board[i].length(); j++) {
                if('O' == board[i].charAt(j)) {
                    cntO++;
                    rowO++;
                }else if('X' == board[i].charAt(j)) {
                    cntX++;
                    rowX++;
                }
                
                if('O' == board[j].charAt(i)) {
                    colO++;
                }else if('X' == board[j].charAt(i)) {
                    colX++;
                }
            }
            
            if(rowO == 3) {
                clearO++;
            }
            
            if(rowX == 3) {
                clearX++;
            }
            
            if(colO == 3) {
                clearO++;
            }
            
            if(colX == 3) {
                clearX++;
            }
        }
        
        // X가 많을 때, O가 두 개이상 많을 때 
        if(cntX > cntO || cntO - cntX >= 2) {
            return false;
        }
        
        // 양쪽 대각선 세기  
        int sameO = 0;
        int sameX = 0;
        int reverseO = 0;
        int reverseX = 0;
        for(int i=0; i<board.length; i++) { // 행           
            // 정
            if(board[i].charAt(i) == 'O') {
                sameO++;
            }else if(board[i].charAt(i) == 'X') {
                sameX++;
            }
            
            // 반
            if(board[i].charAt(2-i) == 'O') {
                reverseO++;
            }else if(board[i].charAt(2-i) == 'X') {
                reverseX++;
            }
            
            if(sameO == 3) {
                clearO++;
            }
            
            if(sameX == 3) {
                clearX++;
            }
            
            if(reverseO == 3) {
                clearO++;
            }
            
            if(reverseX == 3) {
                clearX++;
            }    
        }
        
        if(clearO >=1 && cntO == cntX) {
            return false;
        }
        
        if(clearX >=1 && cntO - cntX == 1) {
            return false;
        }
        
        if(clearO ==1 && clearX ==1) {
            return false;
        }
        
        return true;
    }
}