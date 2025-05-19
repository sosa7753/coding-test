public class Solution {
    public long solution(int[][] land, int P, int Q) {
        long answer = 0;
        long L = land[0][0]; 
        long R = land[0][0];
        for(int i=0; i<land.length; i++) {
            for(int j=0; j<land.length; j++) {
                L = Math.min(L, land[i][j]);
                R = Math.max(R, land[i][j]);
            }
        }
        
        while(L<=R) {
            long mid = (L + R)/2;
            
            long f = check(land, mid, P, Q);
            long s = check(land, mid+1, P, Q);
            
            if(f > s) {
                L = mid+1;
                answer = s;
            }else {
                R = mid-1;
                answer = f;
            }
        }
        
        return answer;
    }
    
    public long check(int[][] land, long h, int P, int Q) {
        long result = 0;
        for(int i=0; i<land.length; i++) {
            for(int j=0; j<land.length; j++) {
                if(h > land[i][j]) {
                    result += P * (h - land[i][j]);
                }else {
                    result += Q * (land[i][j] - h); 
                }
            }
        }
        return result;
    }
}