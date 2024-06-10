import java.util.*;
class Solution {
    int[] dx = {1, 0, 1};
    int[] dy = {0, 1 ,1};
    char[][] map;
    // 좌측 상단의 한 점에서 우측 하단까지 4칸을 비교. -> List에 담음
    // List에 담겨있는 정보로 맵 업데이트 -> [] board
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        // map 초기화
        map = new char[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }
        
        while(true) {
            List<int[]> list = block(m,n,map);
            
            if(list.size() == 0) {
                break;
            }
            
            answer += play(list,m,n,map);
                
            update(m,n,map);
        }
             
        return answer;
    }
    
    public void update(int m, int n, char[][] map) {
        
        int[] max = new int[n]; // 놓을 수 있는 열 인덱스
        boolean[] visited = new boolean[n];
        
        // 우측 하단에서 왼쪽 상단으로 역 순회 
        for(int i=m-1; i>=0; i--) { // 행 
            for(int j=n-1; j>=0; j--) { // 열 
                if(visited[j]) {
                    continue;
                }
                
                if('0' != map[i][j]) {
                    continue;
                }              
                max[j] = i;
                visited[j] = true;
            }
        }
        
        // 왼쪽부터 순회 , max-1부터 0까지 순회
        for(int i=0; i<n; i++) { // 열 
            int bottom = max[i];
            for(int j=max[i]-1; j>=0; j--) { // 행 
                if('0' == map[j][i]) {
                    continue;
                }
                
                map[bottom][i] = map[j][i];
                bottom--;
                map[j][i] = '0';                
            }
        }
    }
    
    public int play(List<int[]> blocks, int m, int n, char[][] map) {
        boolean[][] visited = new boolean[m][n];
        
        int result = 0;
        
        // 블록깨기
        for(int i=0; i<blocks.size(); i++) {
            int[] block = blocks.get(i);
            
            if(!visited[block[0]][block[1]]) {
                map[block[0]][block[1]] = '0';
                result++;
                visited[block[0]][block[1]] = true;                
            }
            
            for(int j=0; j<3; j++) {
                if(!visited[block[0] + dy[j]][block[1] + dx[j]]) {
                    map[block[0] + dy[j]][block[1] + dx[j]] = '0';
                    result++;
                    visited[block[0] + dy[j]][block[1] + dx[j]] = true;               
                }
            }
        }    
        return result;
    }

    public List<int[]> block(int m, int n, char[][] map) {   
        List<int[]> list = new ArrayList<>();
        
        // 리스트에 추가 
        for(int i=0; i<m-1; i++) { // 행
            for(int j=0; j<n-1; j++) { // 열 
                if(map[i][j] == '0') {
                    continue;
                }
                char target = map[i][j];
                
                boolean isfalse = true;
                for(int k=0; k<3; k++) {
                    if(target != map[i+dy[k]][j+dx[k]]) {
                        isfalse = false;
                        break;
                    }
                }
                if(isfalse) {
                   list.add(new int[] {i,j}); 
                }                
            }
        }  
        return list;
    }
}