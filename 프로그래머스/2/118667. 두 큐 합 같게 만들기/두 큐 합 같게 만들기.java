import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        int n = queue1.length;
        
        long Q1 = 0;
        long Q2 = 0;
        for(int i=0; i<n; i++) {
            Q1 += queue1[i];
            q1.offer(queue1[i]);
            
            Q2 += queue2[i];
            q2.offer(queue2[i]);
        }
        
        int cnt = 0;
        while(cnt < 3 * n) {
            if(Q1 == Q2) {
                break;
            }else if(Q1 > Q2) {
                int tmp = q1.poll();
                Q1 -= tmp;
                Q2 += tmp;
                q2.offer(tmp);
            }else {
                int tmp = q2.poll();
                Q2 -= tmp;
                Q1 += tmp;
                q1.offer(tmp);
            }
            cnt++;
        }
        return cnt == 3 * n ? -1 : cnt;
    }
}