import java.util.*;
class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        
        boolean[][] boardVisited = new boolean[game_board.length][game_board.length];
        boolean[][] tableVisited = new boolean[table.length][table.length];
        
        ArrayList<ArrayList<int[]>> boardList = new ArrayList<>();
        ArrayList<ArrayList<int[]>> tableList = new ArrayList<>();
        
        for(int i=0; i<game_board.length; i++) {
            for(int j=0; j<game_board.length; j++) {
                if(game_board[i][j] == 0 && !boardVisited[i][j]) {
                    bfs(i, j, 0, game_board, boardVisited, boardList);
                }
                
                if(table[i][j] == 1 && !tableVisited[i][j]) {
                    bfs(i, j, 1, table, tableVisited, tableList);
                }
            }
        }
        
        boolean[] visited = new boolean[boardList.size()];
        
        for(int i=0; i<tableList.size(); i++) {
            ArrayList<int[]> tablePoints = tableList.get(i);
            
            for(int j=0; j<boardList.size(); j++) {
                ArrayList<int[]> boardPoints = boardList.get(j);
                
                if(tablePoints.size() == boardPoints.size() && !visited[j]) {
                    if(checkPuzzle(boardPoints, tablePoints)) {
                        answer += tablePoints.size();
                        visited[j] = true;
                        break;
                    }
                }
            }
        }
        
        return answer;
    }
    
    public void bfs(int x, int y, int status, int[][] matrix, boolean[][] visited, ArrayList<ArrayList<int[]>> list) {
        ArrayList<int[]> tempList = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x, y});
        tempList.add(new int[] {x-x, y-y});
        visited[x][y] = true;

        while(!q.isEmpty()) {
            int[] now = q.poll();
            int sx = now[0];
            int sy = now[1];
            
            for(int i=0; i<4; i++) {
                int nx = sx + dy[i];
                int ny = sy + dx[i];
                
                if(nx < 0 || ny < 0 || nx >= matrix.length || ny >= matrix.length || visited[nx][ny] || matrix[nx][ny] != status) continue;
                
                visited[nx][ny] = true;
                q.add(new int[] {nx, ny});
                tempList.add(new int[] {nx-x, ny-y});
            }
        }
        
        list.add(tempList);
    }
    
    public boolean checkPuzzle(ArrayList<int[]> boardPoints, ArrayList<int[]> tablePoints) {
        boolean correctPuzzle = true;
        
        // board 좌표 정렬
        Collections.sort(boardPoints, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]); 
        
        for(int i=0; i<4; i++) {
            correctPuzzle = true;
            
            // 퍼즐 조각 회전 했으니 다시 좌표 정렬
            Collections.sort(tablePoints, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]); 
            
            // 회전했으니 다시 좌표 (0, 0)을 기준으로 조정
            int zeroX = tablePoints.get(0)[0];
            int zeroY = tablePoints.get(0)[1];
            for(int j=0; j<tablePoints.size(); j++) {
                tablePoints.get(j)[0] -= zeroX;
                tablePoints.get(j)[1] -= zeroY;
            }
            
            for(int j=0; j<tablePoints.size(); j++) {
                int[] boardPoint = boardPoints.get(j);
                int[] tablePoint = tablePoints.get(j);
                if(boardPoint[0] != tablePoint[0] || boardPoint[1] != tablePoint[1]) {
                    correctPuzzle = false;
                    break;
                }
            }
            
            if(correctPuzzle) return true;
                
            // 좌표 90도 회전 : (x,y) = (-y,x) => 배열 90도 회전 -> [x][y] = [y][-x]
            for(int j=0; j<tablePoints.size(); j++) {
                int temp = tablePoints.get(j)[0];
                tablePoints.get(j)[0] = tablePoints.get(j)[1];
                tablePoints.get(j)[1] = -temp;
            }
        }
        
        return false;
    }
    
}