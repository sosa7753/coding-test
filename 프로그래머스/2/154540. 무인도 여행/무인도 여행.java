import java.util.*;
class Solution {
    int[] dx = {0,1,0,-1};
    int[] dy = {-1,0,1,0};
    boolean[][] visited;
    public int[] solution(String[] maps) {
        List<Integer> list = new ArrayList<>();
        
        visited = new boolean[maps.length][maps[0].length()];
             
        for(int i=0; i<maps.length; i++) {
            for(int j=0; j<maps[i].length(); j++) {
                if(maps[i].charAt(j) == 'X') {
                    continue;
                }
                
                if(visited[i][j]) {
                    continue;
                }
                
                int tmp = BFS(i,j,maps);      
                list.add(tmp);
            }
        }
        Collections.sort(list);
        
        if(list.size() == 0) {
            return new int[] {-1};
        }
        
        return list.stream().mapToInt(value -> value).toArray();
    }
    
    public int BFS(int row, int col, String[] maps) {
        int result = 0;
        
        Queue<int[]> queue = new LinkedList<>();
        visited[row][col] = true;
        queue.offer(new int[] {row, col});
        
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
                    
            result = result + Integer.parseInt(maps[now[0]].substring(now[1],now[1]+1));
          
            for(int i=0; i<4; i++) {
                int row1 = now[0] + dy[i];
                int col1 = now[1] + dx[i];
                
                if(row1 < 0 || row1 > maps.length-1 || col1 < 0 || col1 > maps[row].length()-1) {
                    continue;
                }
                
                if(visited[row1][col1]) {
                    continue;
                }
                
                if(maps[row1].charAt(col1) =='X') {
                    continue;
                }
                
                visited[row1][col1] = true;
                queue.offer(new int[] {row1, col1});
            }
            
        }
        return result;
    } 
}