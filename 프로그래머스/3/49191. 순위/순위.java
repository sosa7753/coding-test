class Solution {
    public int solution(int n, int[][] results) {
        int[][] map = new int[n+1][n+1];
        
        for(int i=0; i<results.length; i++) {
            map[results[i][0]][results[i][1]] = 1;
            map[results[i][1]][results[i][0]] = -1;
        }
        
        for(int i=1; i<=n; i++) { // 출발
            for(int j=1; j<=n; j++) { // 도착
                for(int k=1; k<=n; k++) {
                    if(map[i][k] == 1 && map[k][j] == 1) {
                        map[i][j] = 1; // 이기고 이기기
                        map[j][i] = -1; // 반대쪽은 패배
                    }
                    
                    if(map[i][k] == -1 && map[k][j] == -1) {
                        map[i][j] = -1;
                        map[j][i] = 1;
                    }
                }
            }
        }
        
        int answer = 0;
        for(int i=1; i<=n; i++) {
            int cnt = 0;
            for(int j=1; j<=n; j++) {
                if(map[i][j] != 0) {
                    cnt++;
                }
            }
            if(cnt == n-1) {
                answer++;
            }
        }
          
        return answer;
    }
}