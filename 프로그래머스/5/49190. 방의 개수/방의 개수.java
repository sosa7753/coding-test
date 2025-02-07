import java.util.*;
class Solution {
    int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
    public int solution(int[] arrows) {
        int answer = 0;
        Map<String, Set<Integer>> map = new HashMap<>();
    
        int curX = 0;
        int curY = 0;
        String now = key(curX, curY);
        map.put(now, new HashSet<>());
        for(int i=0; i<arrows.length; i++) {
            for(int j=0; j<2; j++) {
                int nx = curX + dx[arrows[i]];
                int ny = curY + dy[arrows[i]];
                String s = key(nx, ny);
                              
                if(!map.containsKey(s)) { // 첫 방문하는 점
                    map.put(s, new HashSet<>());                    
                }else if(!map.get(now).contains(arrows[i])) { // 처음 보는 간선
                    answer++;
                }
                
                map.get(s).add((arrows[i]+4)%8); // 역방향
                map.get(now).add(arrows[i]); // 정방향
                curX = nx;
                curY = ny;
                now = s;       
            }
        } 
        
        return answer;
    }
    
    public String key(int x, int y) {
        StringBuilder sb = new StringBuilder();
        return sb.append(x).append(",").append(y).toString();
    }
}
