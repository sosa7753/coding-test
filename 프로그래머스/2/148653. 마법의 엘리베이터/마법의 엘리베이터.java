class Solution {
    public int solution(int storey) {        
        
        return DFS(storey, 0);
    }
    
    public int DFS(int storey, int cnt) {
        if(storey == 0) {
            return cnt;
        } 
        int nextStorey = storey/10;
        
        int count = 0; 
        // 끝 자리가 0이면 패스, 1~4면 감소, 6~9면 증가
        // 5일 경우 다음 값이 0~4면 감소, 5~9면 증가
        
        int value = storey%10;
        
        if(value == 0) {
            return DFS(nextStorey, cnt);
        }else if(value <= 4) {
            count = value;
            return DFS(nextStorey, cnt + count);
        }else if(value >= 6) {
            count = 10 - value;
            return DFS(nextStorey+1, cnt + count);
        }else {
            int next = nextStorey%10;
            if(next <= 4) {
                count = value;
                return DFS(nextStorey, cnt + count);
            }else {
                count = 10 - value;
                return DFS(nextStorey+1, cnt + count);
            }
        }
    }
}