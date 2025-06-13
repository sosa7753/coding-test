class Solution {
    int[][] map;
    int[][] answer;
    int min = Integer.MAX_VALUE;
    public int solution(int[][] beginning, int[][] target) {
        map = beginning;
        answer = target;
        
        DFS(0, 0);
    
        if(min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }
    
    public void DFS(int row, int cnt) {
        if(row == map.length) {
            for(int i=0; i<map[0].length; i++) {
                int result = check(i);
                if(result == -1) {
                    return;
                }
                cnt += result;
            }
            
            min = Math.min(min, cnt);
            return;
        }
        
        filp(row);
        DFS(row+1, cnt+1);
        filp(row);
        DFS(row+1, cnt);
    }
    
    public int check(int col) {
        int cnt = 0;
        for(int i=0; i<map.length; i++) {
            if(map[i][col] == answer[i][col]) {
                cnt++;
            }
        }
        
        if(cnt == map.length) {
            return 0;
        }else if(cnt == 0) {
            return 1;
        }else {
            return -1;
        }
    }
    
    public void filp(int row) {
        for(int i=0; i<map[0].length; i++) {
            map[row][i] = (map[row][i] + 1)%2;
        }
    }
}