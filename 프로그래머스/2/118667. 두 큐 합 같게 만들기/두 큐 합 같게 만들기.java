import java.util.*;
class Solution {
    int max;
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        max = Integer.MAX_VALUE;
        
        int len = queue1.length + queue2.length;
        
        long sum = 0;
        long target = 0;
        Queue<Integer> q1 = new LinkedList<>(); 
        Queue<Integer> q2 = new LinkedList<>(); 
        
        long sum1 = 0;
        for(int value : queue1) {
            q1.add(value);
            sum += (long)value;
            sum1 += (long)value;
        }
               
        for(int value : queue2) {
            q2.add(value);
            sum += (long)value;
        }
                        
        // 홀수 
        if(sum%2 == 1) {
            return -1;
        }
        
        target = sum/2;
        
        int cnt = 0;
        while(cnt < len*2) {
            if(sum1 == target) {
                max = Math.min(max, cnt);
                break;
            }
                        
            if(sum1 > target || q2.isEmpty()) {
                int now = q1.poll();
                q2.add(now);
                sum1 -= now;
                cnt++;
                continue;
            }
            
            if(sum1 < target || q1.isEmpty()) {
                int now = q2.poll();
                q1.add(now);
                sum1 += now;
                cnt++; 
            }          
        }
        
        if(cnt == len * 2) {
            return -1;
        }
        
        return max;
    }
 
}