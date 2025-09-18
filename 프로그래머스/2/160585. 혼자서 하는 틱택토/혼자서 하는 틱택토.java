class Solution {
    public int solution(String[] board) {
        // 안되는 경우를 생각해보자.
        /*
        0. O와 X 개수 차이가 2개 이상인 경우 
        1. X가 더 많은 경우
        2. O가 빙고인데, O <= X 인 경우
        3. X가 빙고인데 O가 더 많은 경우 
        */
        
        int[] now = counting(board);
        int o = now[0];
        int x = now[1];
        
        if(Math.abs(o - x) >=2) {
            return 0;
        }
        
        if(x > o) {
            return 0;
        }
        
        if(binggo(board, 'O') && (o <= x)) {
            return 0;
        }
        
        if(binggo(board, 'X') && (o > x)) {
            return 0;
        }
        
        return 1;
    }
    
    public boolean binggo(String[] board, char v) {
        int[] check = new int[8]; // 1행 2행 3행 1열 2열 3열 왼대각 오른대각
        for(int i=0; i<3; i++) { // 행반복
            for(int j=0; j<3; j++) { // 열반복 
                if(board[i].charAt(j) != v) {
                    continue;
                }
                
                check[i]++;
                check[j+3]++;
                if(i == j) check[6]++;
                if(i + j == 2) check[7]++;      
            }
        } 
        
        for(int i=0; i<check.length; i++) {
            if(check[i] == 3) {
                return true;
            }
        }
        return false;
    }
    
    public int[] counting(String[] board) {
        int o  = 0;
        int x  = 0;
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(board[i].charAt(j) == 'O') {
                    o++;
                }else if(board[i].charAt(j) == 'X') {
                    x++;
                }
            }
        }
        
        return new int[]{o, x};
    }
}