import java.util.*;
class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    int m,n;
    int[][] time;
    long[][] dist;
    public int[] solution(int m, int n, int s, int[][] time_map) {
        this.time = time_map;
        this.m = m;
        this.n = n;
        dist = new long[m][n];
        
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                dist[i][j] = s; 
            }
        }
        dist[0][0] = 0;
        
        return dijkstra();
    }
    
    public int[] dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> {
            if(x.cnt == y.cnt) {
                return (int)(x.cost - y.cost);
            }else {
                return x.cnt - y.cnt;
            }
        });
        
        pq.offer(new Node(0, 0, 0, 0));  
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            if(now.r == m-1 && now.c == n-1) {
                return new int[]{now.cnt, (int)now.cost};
            }
            
            for(int i=0; i<4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                
                if(nr < 0 || nr > m-1 || nc < 0 || nc > n-1) {
                    continue;
                }
                
                if(time[nr][nc] == -1) {
                    continue;
                }
                
                long ncost = now.cost + time[nr][nc];
                if(ncost <= dist[nr][nc]) {
                    dist[nr][nc] = ncost;
                    pq.offer(new Node(nr, nc, ncost, now.cnt+1));
                }
            }
        }
        return new int[]{-1, -1};
    }
}

class Node {
    int r;
    int c;
    long cost;
    int cnt;
    Node(int r, int c, long cost, int cnt) {
        this.r = r;
        this.c = c;
        this.cost = cost;
        this.cnt = cnt;
    }
}