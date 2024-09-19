class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        
        int end = time(video_len);      
        int start = time(pos);
        
        int ops = time(op_start);
        int ope = time(op_end);
        
        int now = start;
        if(ops <= now && now <= ope) {
            now = ope;
        }
        for(int i=0; i<commands.length; i++) {         
            if("next".equals(commands[i])) { // 10초 뒤 
                now = Math.min(now+10, end);
            }else if("prev".equals(commands[i])) { // 10초 전
                now = Math.max(now-10, 0);
            }
            
            if(ops <= now && now <= ope) {
                now = ope;
            }
        }
            
        return clock(now);
    }
    
    public int time(String str) {
        String[] s = str.split(":");
        return Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
    }
    
    public String clock(int time) {
        String m = String.valueOf(time/60);
        String s = String.valueOf(time%60);
        
        if(m.length()==1) {
            m = "0" + m;
        }
        
        if(s.length()==1) {
            s = "0" + s;
        }
        
        return m + ":" + s;
    }
}