import java.util.*;
class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    int[][] map;
    int cnt = 0;
    public int solution(int[][] board, int r, int c) {         
        map = board;
        for(int i=0; i<4; i++) {
           for(int j=0; j<4; j++) {
               if(map[i][j] != 0) {
                   cnt++;
               }
           }
        }
        cnt = cnt/2;
        return DFS(new Node(r, c, 0), 0);
    }
    
    // 현재 위치에서 이동 기술하기 
    public int DFS(Node now, int depth) {  
        if(depth == cnt) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        
        for(int num=1; num<=6; num++) {
            List<Node> list = new ArrayList<>();
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    if(map[i][j] == num) {
                        list.add(new Node(i,j,0));
                    }                
                }
            }
            
            if(list.isEmpty()) {
                continue;
            }
            
            int forward = BFS(now, list.get(0)) + BFS(list.get(0), list.get(1)) + 2;
            int opposite = BFS(now, list.get(1)) + BFS(list.get(1), list.get(0)) + 2;
            
            map[list.get(0).row][list.get(0).col] = 0;
            map[list.get(1).row][list.get(1).col] = 0;
            
            forward += DFS(list.get(1), depth+1);
            opposite += DFS(list.get(0), depth+1);
            
            result = Math.min(result, Math.min(forward, opposite)); 
            
            map[list.get(0).row][list.get(0).col] = num;
            map[list.get(1).row][list.get(1).col] = num;            
        }
        if(result == Integer.MAX_VALUE) {
            return 0;
        }        
        return result;        
    }
    
    public int BFS(Node start, Node end) {
        boolean[][] visited = new boolean[4][4];
        Queue<Node> q = new LinkedList<>();
        q.add(start);
        visited[start.row][start.col] = true;
        
        while(!q.isEmpty()) {
            Node cur = q.poll();
            
            if(cur.row == end.row && cur.col == end.col) {
                return cur.dist;
            }
            
            for(int i=0; i<4; i++) {
                int row = cur.row + dr[i];
                int col = cur.col + dc[i];
                
                if(row < 0 || row > 3 || col < 0 || col > 3) {
                    continue;
                }
                
                // 한칸이동
                if(!visited[row][col]) {
                    visited[row][col] = true;
                    q.add(new Node(row, col, cur.dist+1));
                }
                
                // 여러칸 이동
                while(true) {
                    if(map[row][col] != 0) {
                        break;
                    }
                    if(row + dr[i] < 0 || row + dr[i] > 3 || 
                       col + dc[i] < 0 || col + dc[i] > 3) {
                        break;
                    }
                                      
                    row += dr[i];
                    col += dc[i];
                }
               
                if(!visited[row][col]) {
                    visited[row][col] = true;
                    q.add(new Node(row, col, cur.dist+1));
                }           
            }
        }
        return -1;
    }
}

class Node {
    int row;
    int col;
    int dist;
    Node(int row, int col, int dist) {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }
} 