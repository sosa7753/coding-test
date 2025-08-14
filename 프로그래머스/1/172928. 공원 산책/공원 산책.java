import java.util.*;
class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    int[][] map;
    Map<String, Integer> direct = new HashMap<>();
    int r = 0;
    int c = 0;
    public int[] solution(String[] park, String[] routes) {
        init();
        // 공원을 먼저 만들어서 S 찾기 
        // 명령 수행 메서드 돌리기
        map = new int[park.length][park[0].length()];     
        for(int i=0; i<park.length; i++) {
            String s = park[i];
            for(int j=0; j<park[i].length(); j++) {
                if('S' == park[i].charAt(j)) {
                    r = i; c = j;
                }else if('X' == park[i].charAt(j)) {
                    map[i][j] = -1;
                }
            }
        }
        
        for(String route : routes) {
            String[] str = route.split(" ");
            String dir = str[0];
            int val = Integer.parseInt(str[1]);
            
            move(dir, val);
        }
        
        int[] answer = new int[2];
        answer[0] = r;
        answer[1] = c;
        return answer;
    }
    
    public void move(String dir, int val) {
        int d = direct.get(dir);
        
        int nr = r + dr[d]*val;
        int nc = c + dc[d]*val;
        if(nr < 0 || nr > map.length-1 || nc < 0 || nc > map[0].length-1) {
            return;   
        }
        
        nr = r;
        nc = c;
        int cnt = 0;
        while(cnt < val) {
            nr += dr[d];
            nc += dc[d];
            if(map[nr][nc] == -1) {
                return;
            }
            cnt++;
        }
        r = nr;
        c = nc;
    }
    
    public void init() {
        direct.put("N", 0);
        direct.put("E", 1);
        direct.put("S", 2);
        direct.put("W", 3);
    }
}