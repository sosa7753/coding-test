class Solution {
    int[][] b;
    int[][] t;
    int answer = Integer.MAX_VALUE;
    public int solution(int[][] beginning, int[][] target) {
        b = beginning;
        t = target;
        
        DFS(0, 0);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    public void DFS(int row, int cnt) {
        if(row == b.length) {
            int r = check();
            if(r != -1) {
                answer = Math.min(answer, cnt + r);
            }
            return;
        }
        
        filp(row);
        DFS(row+1, cnt+1);
        filp(row);
        DFS(row+1, cnt);
    }
    
    public int check() {
        int result = 0;
        for(int i=0; i<b[0].length; i++) { // 열 체크 
            int cnt = 0;
            for(int j=0; j<b.length; j++) {
                if(b[j][i] == t[j][i]) {
                    cnt++;
                }
            }
            if(cnt == 0) {
               result++; 
            }else if(cnt == b.length) {
               continue;
            }else {
                return -1;
            }
        }
        return result;
    }
    
    public void filp(int row) {
        for(int i=0; i<b[0].length; i++) {
            b[row][i] = (b[row][i] + 1)%2;
        }
    }
}