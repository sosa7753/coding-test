import java.util.*;
class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1 ,0};
    int[][] map;
    public int solution(String dirs) {
        // 길이를 두배로 하고, -10 -> 0으로 보정하기 (+10)
        // 요청마다 2칸을 이동할건데, Map으로 저장하기
        map = new int[11][11];
        
        int x = 10;
        int y = 10;
        Set<String> set = new HashSet<>();
        for(int i=0; i<dirs.length(); i++) {
            int idx = dir(dirs.charAt(i));
            x += dx[idx];
            y += dy[idx];
            if(x < 0 || x > 20 || y < 0 || y > 20) {
                x -=dx[idx];
                y -=dy[idx];
                continue;
            }
            
            set.add(x + " " + y);
            x += dx[idx];
            y += dy[idx];
        }
        return set.size();
    }
    
    public int dir(char c) {
        if(c == 'U') {
            return 0;
        }else if(c == 'R') {
            return 1;
        }else if(c == 'D') {
            return 2;
        }else {
            return 3;
        }
    }
}