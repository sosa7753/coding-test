// n개의 행에 대해 모든 행은 뒤집거나 뒤집지 않을 수 있음. -> 2^n
// 각 map에 대해 열을 반복하며 체크
// 만약 해당 열이 target의 해당 열과 같거나 정반대면 가능 -> 정반대면 뒤집기 ++
// 그렇지도 않으면 불가능 -> -1
class Solution {
    int b;
    int bl;
    int[][] map;
    int[][] t;
    int answer;
    public int solution(int[][] beginning, int[][] target) {
        answer = Integer.MAX_VALUE;
        
        b = beginning.length;
        bl = beginning[0].length;
        
        t = target;
        
        // map으로 복사 
        map = new int[b][bl];
        for(int i=0; i<b; i++) {
            map[i] = beginning[i].clone();
        }
        
        DFS(0,0);
        
        if(answer == Integer.MAX_VALUE) {
            return -1;
        }
                
        return answer;
    }
    
    // 특정 행의 모든 열 뒤집기
    public void flip(int row) {
        for(int i=0; i<bl; i++) {
            map[row][i] = (map[row][i] + 1)%2;
        }
    }
    
    // 해당 열이 target 열과 같거나 정반대거나 다르거나 판별
    public int check(int col) {
        int result = 0;
        for(int i=0; i<b; i++) {
            if(t[i][col] == map[i][col]) {
                result++;
            }
        }
        
        if(result == b) { // 전부 일치 
            return 0;
        }else if(result == 0) { // 전부 불일치
            return 1; //
        }else {
            return -1;
        }
    }
    
    // 행을 뒤집는 모든 경우의 수 체크 0~n-1 까지 
    public void DFS(int row, int cnt) {
        if(row==b) {
            boolean isfalse = true;
            for(int i=0; i<bl; i++) { // 열 반복
                int result = check(i);
                if(result==-1) { // 불가능 
                    isfalse = false;
                    break;
                }
                cnt += result; // 가능할 경우 뒤집어주는 열 개수 추가.
            }
            
            if(isfalse) {
                answer = Math.min(cnt, answer);
            }
            return;
        }
        
        flip(row); // 해당 행 뒤집기 
        DFS(row+1, cnt+1);
        flip(row); // 다시 뒤집기
        DFS(row+1, cnt);
    }
}