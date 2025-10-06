import java.util.*;
class Solution {
    List<Integer> list = new ArrayList<>();
    double[] sum;
    public double[] solution(int k, int[][] ranges) {
        list.add(k);
        
        int n = 0;
        while(k != 1) {
            if(k%2 == 0) {
                k /= 2;
            }else {
                k = k*3 +1;
            }
            list.add(k);
            n++;
        }
        
        sum = new double[n+1];
        for(int i=1; i<=n; i++) {
            sum[i] = sum[i-1] + (list.get(i) + list.get(i-1))/(double)2.0;
        }
        
        double[] answer = new double[ranges.length];
        for(int i=0; i<ranges.length; i++) {
            answer[i] = integral(ranges[i][0], n + ranges[i][1], n);
        }
        
        return answer;
    }
    
    public double integral(int s, int e, int n) {
        if(s > e) {
            return -1.0;
        }
            
        return sum[e] - sum[s]; 
    }
}