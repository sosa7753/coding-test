import java.util.*;
class Solution {
    public int solution(int n, int[][] data) {       
        Arrays.sort(data, (x,y) -> {
            if(x[0] == y[0]) {
                return x[1] - y[1];
            }else {
                return x[0] - y[0];
            }
        });
        
        int answer = 0;
        for(int i=0; i<data.length-1; i++) { 
            for(int j=i+1; j<data.length; j++) {
                // 넓이가 0
                if(data[i][0] == data[j][0] || data[i][1] == data[j][1]) {
                    continue;
                }
                
                // 박스권 안에 쐐기가 있는지 체크
                int start = Math.min(data[i][1], data[j][1]);
                int end = Math.max(data[i][1], data[j][1]);
                    
                boolean check = true;
                for(int k=i+1; k<j; k++) {
                    if(data[i][0] < data[k][0] && 
                       data[j][0] > data[k][0] &&
                       start < data[k][1] && end > data[k][1]) {
                        check = false;
                        break;
                    }
                }
                
                if(check) {
                   answer++;  
                }
            }     
        }       
        return answer;
    }
}