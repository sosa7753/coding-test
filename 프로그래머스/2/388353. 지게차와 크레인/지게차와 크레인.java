class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    char[][] map;
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        
        map = new char[storage.length][storage[0].length()];
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[0].length; j++) {
                map[i][j] = storage[i].charAt(j);
            }
        }
        
        for(int i=0; i<requests.length; i++) {
            char c = requests[i].charAt(0);
            if(requests[i].length() == 1) {
                one(c);
            }else {
                two(c);
            }
        }
        
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[i].length; j++) {
                if(map[i][j] != '0') {
                    answer++;
                }
            }
        }
        return answer;
    }
    
    public void one(char c) {
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[0].length; j++) {
                if(map[i][j] == c) {
                   boolean[][] visited = new boolean[map.length][map[0].length];
                   visited[i][j] = true;
                   if(DFS(i, j, visited)) {
                       map[i][j] = '1';
                   }
                }
            }
        }
        
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[i].length; j++) {
                if(map[i][j] == '1') {
                    map[i][j] = '0';
                }
            }
        }
    }
    
    public void two(char c) {
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[0].length; j++) {
                if(map[i][j] == c) {
                    map[i][j] = '0';
                }
            }
        }
    }
    
    
   public boolean DFS(int r, int c, boolean[][] visited) {
     if(edge(r,c)) {
        return true;
      }
 
      for(int i=0; i<4; i++) {
          int row = r + dr[i];
          int col = c + dc[i];
            
          if(map[row][col] != '0') {
             continue;
          }
          
          if(visited[row][col]) {
              continue;
          }
          visited[row][col] = true;
            
          if(DFS(row, col, visited)) {
             return true;
          }
      }
      return false;
    }
    
    public boolean edge(int r, int c) {
        if(r == map.length-1 || r == 0 || c == map[0].length-1 || c == 0) {
            return true;
        }
        return false;
    }
 }