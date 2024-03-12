class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {-1, 0, 1, 0};
    public int[] solution(String[] park, String[] routes) {
        int p = park.length;
        int r = routes.length;
               
        int[] now = new int[2];
        
        // 시작 지점 찾기
        boolean isStart = false;
        for(int i=0; i<p; i++) {
            String s = park[i];
            
            String[] str = s.split("");
            
            for(int j=0; j<str.length; j++) {
                if("S".equals(str[j])) {
                    now[0] = i;
                    now[1] = j; 
                    isStart = true;
                    break;
                }
            }
            if(isStart) {
                break;
            }
        }
        
        // 이동하기
        for(int i=0; i<routes.length; i++) {
            String[] str = routes[i].split(" ");
            int direct = direction(str[0]);
            int go = Integer.parseInt(str[1]);
            
            // 현 위치에서 이동 방향이 맵을 초과하는지 판별
            int row = now[0] + dy[direct] * go;
            int col = now[1] + dx[direct] * go;
            
            if(row < 0 || row > park.length-1 || col < 0 || col > park[0].length()-1) {
                continue;
            }
            
            // 현 위치에서 갈 때 장애물이 있을 경우 판별 
            int cnt = go;
            int mul = 1;
            boolean istrue = false;
            while(cnt > 0) {
                int row1 = now[0] + dy[direct] * mul;
                int col1 = now[1] + dx[direct] * mul;
                
                String s = park[row1];
                
                // 장애물을 만나면 true;
                if('X' == s.charAt(col1)) {
                    istrue = true;
                    break;
                }
                cnt--;
                mul++;
            }
            
            if(istrue) {
                continue;
            }
            
            now[0] = row;
            now[1] = col;          
        }
        
        return now;
    }
    
    // 방향 구하기
    public int direction(String s) {
        if("N".equals(s)) {
            return 0;
        }else if("E".equals(s)) {
            return 1;
        }else if("S".equals(s)) {
            return 2;
        }else if("W".equals(s)) {
            return 3;
        }else {
            return 4;
        }
    }  
}