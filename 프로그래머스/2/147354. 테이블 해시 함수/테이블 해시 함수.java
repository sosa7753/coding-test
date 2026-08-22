import java.util.*;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (x,y) -> {
            if(x[col-1] == y[col-1]) {
                return y[0] - x[0];
            }else {
                return x[col-1] - y[col-1];
            }
        });
        
        int cur = 0;
        for(int i=row_begin-1; i<=row_end-1; i++) {
            int v = 0;
            for(int j=0; j<data[0].length; j++) {
                v += data[i][j]%(i+1);
            }
            cur = cur^v;
        }
        return cur;
    }
}