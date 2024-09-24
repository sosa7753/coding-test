import java.util.*;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        int start = time("09:00");            
        for(int i=0; i<timetable.length; i++) {
            pq.offer(time(timetable[i]));
        }
        
        int cnt = 0;
        int last = pq.peek();
        int lastBus = start + (n-1) * t;
        for(int i=0; i<n; i++) {
            int now = start + i * t; // 버스 시간
            cnt = 0;
            
            while(!pq.isEmpty()) {
                if(now < pq.peek() || cnt >= m) {
                    break;
                }
                last = pq.poll();
                cnt++;               
            }    
        }
        
        if(cnt < m) {
            return clock(lastBus);
        }
        return clock(last - 1);
    }
       
    public int time(String str) {
        String[] s = str.split(":");
        return Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
    }
    
    public String clock(int time) {
        String h = String.valueOf(time/60);
        String m = String.valueOf(time%60);
        
        StringBuilder sb = new StringBuilder();
        
        if(h.length() == 1) {
            sb.append("0");
        }
        sb.append(h);
        sb.append(":");
        if(m.length() == 1) {
            sb.append("0");
        }
        sb.append(m);
        
        return sb.toString();
    }
}