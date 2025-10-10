import java.util.*;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {       
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(String u : timetable) {
            pq.offer(time(u));
        }
        
        int start = time("09:00");
        int last = pq.peek();
        int lastBus = start + (n-1) * t;
        
        int cnt = 0;
        for(int i=0; i<n; i++) {
            int now = start + i * t;
            cnt = 0;
            
            while(!pq.isEmpty()) {
                if(now < pq.peek() || cnt >= m) {
                    break;
                }
                last = pq.poll();
                cnt++;
            }
        }
        
        if(cnt < m) { // 마지막 버스가 미달
            return clock(lastBus);
        }
        return clock(last - 1);
    }
    
    public int time(String clock) {
        String[] str = clock.split(":");
        return Integer.parseInt(str[0]) * 60  + Integer.parseInt(str[1]);
    }
    
    public String clock(int time) {
        return String.format("%02d:%02d", time/60, time%60);
    }
}