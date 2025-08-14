class Solution {
    public int[] solution(String[] wallpaper) {
        int n = wallpaper.length;
        int m = wallpaper[0].length();
        
        // 2차원 배열을 순회해서 범위 찾기
        // 계산하기
        int[] answer = new int[4]; 
        boolean[] visited = new boolean[4]; // rmin, rmax, cmin, cmax
        int rmin = 0;
        int rmax = 0;
        int cmin = 0;
        int cmax = 0;
        // 행 체크 
        int cnt = 0;
        for(int i=0; i<n; i++) {
            if(cnt == 2) {
                break;
            }
            
            String min = wallpaper[i];
            String max = wallpaper[n -i -1];
            if(!visited[0] && min.contains("#")) {
                visited[0] = true;
                rmin = i;
                cnt++;
            }
            
            if(!visited[1] && max.contains("#")) {
                visited[1] = true;
                rmax = n - i;
                cnt++;
            }
        }
        
        // 열 체크
        cnt = 0;
        for(int i=0; i<m; i++) {
            if(cnt == 2) {
                break;
            }
            
            for(int j=0; j<n; j++) {
                if(!visited[2] && wallpaper[j].charAt(i) == '#') {
                    visited[2] = true;
                    cmin = i;
                    cnt++;
                }
                
                if(!visited[3] && wallpaper[j].charAt(m - i - 1) == '#') {
                    visited[3] = true;
                    cmax = m - i;
                    cnt++;
                }
            }  
        }
        
        answer[0] = rmin;
        answer[1] = cmin;
        answer[2] = rmax;
        answer[3] = cmax;
        
        return answer;
    }
}