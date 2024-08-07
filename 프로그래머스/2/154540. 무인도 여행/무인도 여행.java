import java.util.*;
class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {-1, 0, 1, 0};
    boolean[][] visited;
    public int[] solution(String[] maps) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
    
        visited = new boolean[maps.length][maps[0].length()];
        
        for(int i=0; i<maps.length; i++) {
            for(int j=0; j<maps[0].length(); j++) {
                if(visited[i][j] || maps[i].charAt(j) == 'X') {
                    continue;                
                }                
                pq.offer(DFS(maps, i, j));
            }
        }
        if(pq.isEmpty()) {
            return new int[] {-1};
        }
        
        int[] answer = new int[pq.size()];
        int idx = 0;
        while(!pq.isEmpty()) {
            answer[idx++] = pq.poll();
        }
        return answer;
    }
    
    public int DFS(String[] maps, int r, int c) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[] {r, c});
        
        visited[r][c] = true;  
        int sum = 0;
            
        while(!stack.isEmpty()) {
            int[] now = stack.pop();
            
            sum += maps[now[0]].charAt(now[1]) - '0';
            System.out.print(sum +  " ");
            
            for(int i=0; i<4; i++) {
                int row =  now[0] + dy[i];
                int col =  now[1] + dx[i];
            
                if(row < 0 || row > maps.length-1 || 
                   col < 0 || col > maps[0].length()-1) {
                    continue;
                }
            
                            
                if(maps[row].charAt(col) == 'X') {
                    continue;
                }
                
                if(visited[row][col]) {
                    continue;
                }
                
                visited[row][col] = true;
                stack.push(new int[] {row, col});
            }           
        }
        return sum;
    }
}