import java.util.*;
class Solution {
    int win;
    int[] answer;
    public int[] solution(int n, int[] info) {
        answer = new int[info.length];
             
        win = -1;
             
        int[] lion = new int[info.length];
        
        DFS(info, lion, 0, n);
        
        if(win == -1) {
            return new int[] {-1};
        }
        return answer;
    }
    
    public void DFS(int[] info, int[] lion, int cnt, int n) {
        if(cnt == info.length || n == 0) {
            cal(info, lion);
            return;
        }
        
       for(int j=n; j>=0; j--) {
           lion[cnt] = j; 
           DFS(info, lion, cnt+1, n-j);
       }           
    }
    
    public void cal(int[] info, int[] lion) {
        
        int a = 0; // 어피치 점수
        int l = 0; // 라이언 점수
        
        // 10 -> 0 반복
        for(int i=0; i<info.length; i++) {
            if(info[i] == 0 && lion[i] == 0) {
                continue;
            }
            if(info[i] >= lion[i]) {
                a += 10-i;
            }else {
                l += 10-i;
            }
        }
        
        int result = l - a;
        
        if(result > 0) {
            if(win < result) {
                win = result;
                answer = Arrays.copyOf(lion, lion.length);
            }else if(win == result) {
                for(int i=lion.length-1; i>=0; i--) {
                    if(lion[i] > answer[i]) {
                        answer = Arrays.copyOf(lion, lion.length);
                        break;
                    }
                    
                    if(lion[i] == answer[i]) {
                        continue;
                    }
                    break;
                }
            }         
        }
    }
}