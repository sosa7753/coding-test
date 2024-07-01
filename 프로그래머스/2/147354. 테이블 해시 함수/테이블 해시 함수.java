import java.util.*;
class Solution {
    // col 번째 행을 맨 앞으로, 그 외에는 data[0] 오름차순 정렬
    // row_bigin -1 행 부터 row_end - 1행 까지 mode 진행 
    // 모든 값을 XOR 진행
    public int solution(int[][] data, int col, int row_begin, int row_end) {       
        Arrays.sort(data, new Comparator<int[]>() {
            public int compare(int[] x, int[] y) {
                if(x[col-1] == y[col-1]) {
                    return y[0] - x[0];
                }else {
                    return x[col-1] - y[col-1];
                }
            }
        }); 
        
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<data.length; i++) {
            if(i < row_begin - 1) {
                continue;
            }
            
            if(i > row_end - 1) {
                break;
            }
            
            int[] now = data[i];
            int sum = mod(now, i+1);
            
            if(stack.isEmpty()) {
                stack.push(sum);
            }else {
                int first = stack.pop();
                stack.push(first ^ sum);
            }
        }
        
        return stack.peek();
    }
    
    public int mod(int[] arr, int div) {
        int result = 0;
        
        for(int i=0; i<arr.length; i++) {
            result += arr[i]%div;
        }
        return result;
    }
}