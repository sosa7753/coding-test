import java.util.*;
class Solution {
    List<int[]> list = new ArrayList<>();
    public double[] solution(int k, int[][] ranges) {      
        // 1. n 횟수 구하기
        // 2.좌표를 리스트에 넣기 
        // 3. 각 좌표에 대해 사다리꼴 값 구하기
        // 4. 그에 대한 부분합 구하기
        
        int n = makeOne(k);
        
        double[] ract = new double[list.size()];
        int[] pre = new int[2];
        for(int i=0; i<list.size(); i++) {
            int[] now = list.get(i);
            
            if(i == 0) {
                pre[0] = now[0];
                pre[1] = now[1];
                ract[i] = 0;
                continue;
            }
            
            double sum = (double)(pre[1] + now[1])/2;
            ract[i] = sum;
            pre[0] = now[0];
            pre[1] = now[1];
        }
               
        double[] S = new double[ract.length];
        S[0] = ract[0];
        for(int i=1; i<ract.length; i++) {
            S[i] = S[i-1] + ract[i];
        }
        
        
        double[] answer = new double[ranges.length];
        // [0~7], [1,5] ,[3,4];
        for(int i=0; i<ranges.length; i++) {
            int start = ranges[i][0];          
            int end = n + ranges[i][1];
            
            double sum = 0.0;
            if(start == end) {
                sum = 0.0;
            }else if(start > end) {
                sum = -1.0;
            }else if(start == 0) {
                sum = S[end];
            }else {
                sum = S[end] - S[start];
            }
                        
            answer[i] = sum;
        }
        System.out.print(Arrays.toString(S));
        
        return answer;
    }
    public int makeOne(int k) {
        int cnt = 0;
        
        while(k != 1) {
            list.add(new int[] {cnt, k});            
            if(k%2 == 0) {
                k = k/2;
            }else {
                k = k * 3 + 1;
            }
            cnt++;
        }
        list.add(new int[] {cnt, 1});
        return cnt;      
    }
}