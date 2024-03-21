import java.util.*;
class Solution {
    int[][] pirodo = {{1,1,1}, {5,1,1}, {25,5,1}};
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        answer = DFS(picks, minerals, 0);
        return answer;
    }
    
    public int DFS(int[] picks, String[] minerals, int idx) {
        
        // 곡괭이가 없거나 광물이 없을 때 리턴
        if((picks[0] == 0 && picks[1] == 0 && picks[2] == 0) 
           || idx >= minerals.length) {
            
            return 0;       
        }
        
        int p = picks.length;
        int m = minerals.length;
        
        int min = Integer.MAX_VALUE;
        
        for(int i=0; i<p; i++) { // 각 곡괭이에 대해
            // 곡괭이가 없다면 패스
            if(picks[i] == 0) {
                continue;
            }
            
            int sum = 0;
            
            for(int j=idx; j<Math.min(m, idx+5); j++) { // 최대 5번 반복 
                // 광물에따른 인덱스 변환
                int col = -1;
                if("diamond".equals(minerals[j])) {
                    col = 0;
                }else if("iron".equals(minerals[j])) {
                    col = 1;
                }else {
                    col = 2;
                }
                
                sum += pirodo[i][col];
            }
            
            // 다음 인덱스 DFS 더해주기
            picks[i] = picks[i] - 1;
            sum += DFS(picks, minerals, idx + 5);
            picks[i] = picks[i] + 1;
            
            if(sum < min) {
                min = sum;
            }
        }
        
        return min;       
    }
}