import java.util.*;
class Solution {
    public String[] solution(int[][] line) {     
        long sx = Long.MAX_VALUE;
        long sy = Long.MAX_VALUE;
        long ex = Long.MIN_VALUE;
        long ey = Long.MIN_VALUE;
        
        List<long[]> list = new ArrayList<>(); // 교점 좌표 
        
        for(int i=0; i<line.length-1; i++) {
            for(int j=i+1; j<line.length; j++) {
                
                long a = line[i][0]; long b = line[i][1]; long e = line[i][2];
                long c = line[j][0]; long d = line[j][1]; long f = line[j][2];
                
                long div = a * d - b * c;
                if(div !=0) { // 나누는 수가 0이 아닐 때
                    double x = (double)(b * f - e * d)/(double)div;
                    double y = (double)(e * c - a * f)/(double)div;
                    
                    if(x % 1 == 0 && y % 1 == 0) { // 정수 좌표면 
                        sx = Math.min((long)x, sx);
                        sy = Math.min((long)y, sy);
                        ex = Math.max((long)x, ex);
                        ey = Math.max((long)y, ey);
                        
                        list.add(new long[] {(long)x, (long)y});
                    }                  
                }
            }
        }
        
        // StringBuilder 배열
        StringBuilder[] sb = new StringBuilder[(int)(ey - sy) + 1];
        String init = ".".repeat((int)(ex - sx) + 1);
        for(int i=0; i<sb.length; i++) {
           sb[i] = new StringBuilder(init);
        }
        
        // 점 찍기
        for(int i=0; i<list.size(); i++) {
            long[] now = list.get(i);
            sb[(int)(ey - now[1])].setCharAt((int)(now[0] - sx), '*');
        }
        
        
        String[] answer = new String[(int)(ey - sy) + 1];  
        for(int i=0; i<sb.length; i++) {
            answer[i] = sb[i].toString();
        }

        return answer;
    }
}