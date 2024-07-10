import java.util.*;
class Solution {
    public String[] solution(int[][] line) {
        
        // 교점 저장 
        List<Index> list = new ArrayList<>();
        
        for(int i = 0; i<line.length-1; i++) {
            for(int j = i+1; j<line.length; j++) {
                
                long a = line[i][0]; long b = line[i][1]; long e = line[i][2];
                long c = line[j][0]; long d = line[j][1]; long f = line[j][2];
                
                if(a * d - b * c != 0) {
                    long div = a * d - b * c;
                    
                    double x = (double)(b * f - e * d)/div;                
                    double y = (double)(e * c - a * f)/div;
                    
                    if(x % 1 == 0 && y % 1 == 0) { // 정수일 때만 저장
                        list.add(new Index((long)x, (long)y));
                    }
                }
            }
        }
        
        Index min = new Index(Long.MAX_VALUE, Long.MAX_VALUE);
        Index max = new Index(Long.MIN_VALUE, Long.MIN_VALUE);
        
        for(Index in : list) {            
            min.setX(Math.min(in.x, min.x));
            min.setY(Math.min(in.y, min.y));
            max.setX(Math.max(in.x, max.x));
            max.setY(Math.max(in.y, max.y));           
        }
        
        int width = (int)(max.x - min.x + 1);
        int height = (int)(max.y - min.y + 1);
        
        char[][] arr = new char[height][width];
        
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                arr[i][j] = '.';
            }
        }
        
        // 교점 찍기
        for(Index in : list) {
            arr[(int)(max.y-in.y)][(int)(in.x - min.x)] = '*';
        }
        
        String[] answer = new String[height];
        for(int i=0; i < answer.length; i++) {
            // char 배열을 new String(char[]) 하면 문자열됨
            answer[i] = new String(arr[i]); 
        }
        
        return answer;
    }
}

class Index{
    long x;
    long y;
    Index(long x, long y) {
        this.x = x;
        this.y = y;
    }
    
    void setX(long x) {
        this.x = x;
    }
    
    void setY(long y) {
        this.y = y;
    }
}