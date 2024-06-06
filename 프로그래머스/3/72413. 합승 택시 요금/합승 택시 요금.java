class Solution {
    int[][] dist;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        // 거리 배열 초기화
        dist = new int[n+1][n+1];
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(i != j) {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        floydWarshall(n, fares);
        int min = Integer.MAX_VALUE;
        // 출발 노드에서 특정 지점까지의 최단 거리 + 그 지점에서 각각 A, B까지의 최단 거리 
        for(int i=1; i<=n; i++) {
            if(dist[s][i] != Integer.MAX_VALUE && dist[i][a] != Integer.MAX_VALUE
              && dist[i][b] != Integer.MAX_VALUE) {
                min = Math.min(min, (dist[s][i] + dist[i][a] + dist[i][b]));
            }
        }
        
        return min;
    }
    
    public void floydWarshall(int n, int[][] fares) {
        
        // 초기 간선 정보 저장 
        for(int i=0; i<fares.length; i++) {
            dist[fares[i][0]][fares[i][1]] = fares[i][2];
            dist[fares[i][1]][fares[i][0]] = fares[i][2];
        }
        
        for(int k=1; k<=n; k++) { // 거쳐가는 노드
            for(int i=1; i<=n; i++) { // 출발 노드
                for(int j=1; j<=n; j++) { // 도착 노드
                    // 거리 배열값이 이 무한대가 아니라면 
                    if(dist[i][k] != Integer.MAX_VALUE && 
                       dist[k][j] != Integer.MAX_VALUE) {
                        if(dist[i][j] > dist[i][k] + dist[k][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }
        }
    }
}