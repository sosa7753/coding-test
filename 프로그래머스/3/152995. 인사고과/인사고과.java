import java.util.*;
class Solution {
    public int solution(int[][] scores) {
        int answer = 0;
        int[] result = new int[200001];
        
        int a = scores[0][0];
        int b = scores[0][1];
        int value = a+b;
        boolean ok = false;
        
        Stack<int[]> stack = new Stack<>();
        
        Arrays.sort(scores, (x,y) -> {
            if(x[0] == y[0]) {
                return y[1] - x[1];
            }else {
                return x[0] - y[0];
            }
        });
        
        for(int i=0; i<scores.length; i++) {
            if(stack.isEmpty()) {
                stack.push(scores[i]);
                continue;
            }
            
            while(!stack.isEmpty() && stack.peek()[0] < scores[i][0] 
                  && stack.peek()[1] < scores[i][1]) {
                stack.pop();
            }
            stack.push(scores[i]);
        }
        
        int dep = stack.size();
        for(int i=0; i<dep; i++) {
            int[] t = stack.pop();
            if((t[0] == a && t[1] == b) && ok == false) {
                ok = true;
            }
            
            int idx = t[0] + t[1];
            result[idx]++;         
        }
        
        if(!ok) {
            return -1;
        }
        
        for(int i=200000; i>value; i--) {
            answer += result[i];
        }
        
        if(answer == 0) {
            return 1;
        }

        return answer + 1;
    }
}