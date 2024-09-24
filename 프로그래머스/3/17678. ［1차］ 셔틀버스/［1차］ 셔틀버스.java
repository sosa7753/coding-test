import java.util.*;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        List<List<Integer>> bus = new ArrayList<>(); // 버스 간격
        for(int i=0; i<n; i++) {
            bus.add(new ArrayList<>());
        }
        
        int start = time("09:00");
            
        int value = 0;
        int[] arrive = new int[timetable.length];
        for(int i=0; i<timetable.length; i++) {
            arrive[i] = time(timetable[i]);
        }
        Arrays.sort(arrive);
        
        int p = 0;
        for(int i=0; i<bus.size(); i++) {
            int now = start + i * t; // 버스 시간
            int cnt = 0;
            while(p <= arrive.length-1 && arrive[p] <= now && cnt < m) {
                bus.get(i).add(arrive[p]);
                cnt++;
                p++;
            }
        } 
        
        for(int i=bus.size()-1; i>=1; i--) { // 막차부터 연산
            int cur = start + i * t;
            List<Integer> list = bus.get(i);
            
            if(list.size() < m) {
                return clock(cur);
            }else {
                if(list.get(0) == list.get(list.size()-1)) { // 모든 값이 같음.
                    if(list.get(0) == cur) {
                        continue;
                    }else {
                        return clock(list.get(0)-1);
                    }
                }else {
                    return clock(list.get(list.size()-1) -1);
                }
            }
        }
        
        List<Integer> last = bus.get(0);
        
        if(last.size() < m) {
                return clock(start);
        }else {
                if(last.get(0) == last.get(last.size()-1)) { // 모든 값이 같음.
                    return clock(last.get(0) - 1);
                }else {
                    return clock(last.get(last.size()-1) -1);
                }
        }
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