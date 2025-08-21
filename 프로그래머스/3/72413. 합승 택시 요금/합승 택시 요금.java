class Solution {
    int[][] map;
    int INF = Integer.MAX_VALUE;
    public int solution(int n, int s, int a, int b, int[][] fares) {   
        map = new int[n+1][n+1];
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(i != j) {
                    map[i][j] = INF;
                }
            }
        }
        
        for(int[] fare : fares) {
            int start = fare[0];
            int end = fare[1];
            int w = fare[2];
            map[start][end] = w;
            map[end][start] = w;
        }

        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    if(map[i][k] == INF || map[k][j] == INF) {
                        continue;
                    }
                    
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
        
        int min = INF;
        for(int i=1; i<=n; i++) {
            if(map[s][i] != INF &&
               map[i][a] != INF &&
               map[i][b] != INF) {
            
                min = Math.min(min, map[s][i] + map[i][a] + map[i][b]);
            }          
        }
              
        return min;
    }
}