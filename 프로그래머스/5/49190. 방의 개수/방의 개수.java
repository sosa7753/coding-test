import java.util.*;
class Solution {
    int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
    public int solution(int[] arrows) {
        int answer = 0;
        
        Map<String, Set<Integer>> map = new HashMap<>();
        int x = 0; int y = 0; String now = makeKey(x, y);
        map.put(now, new HashSet<>());
        for(int i=0; i<arrows.length; i++) {
            for(int j=0; j<2; j++) {
                int nx = x + dx[arrows[i]];
                int ny = y + dy[arrows[i]];
                String nextKey = makeKey(nx, ny);
                
                if(!map.containsKey(nextKey)) {
                    map.put(nextKey, new HashSet<>());
                }else if(!map.get(now).contains(arrows[i])) {
                    answer++;
                }
                
                map.get(nextKey).add((arrows[i]+4)%8);
                map.get(now).add(arrows[i]);
                x = nx; y = ny; 
                now = nextKey;
            }
        }
        
        return answer;
    }
    
    public String makeKey(int x, int y) {
        StringBuilder sb = new StringBuilder();
        return sb.append(x).append(",").append(y).toString();
    }
}