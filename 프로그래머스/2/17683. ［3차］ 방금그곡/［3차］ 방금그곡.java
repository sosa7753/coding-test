import java.util.*;
class Solution {
    String originsh[] = {"A#", "C#", "D#", "F#", "G#"};
    String nextsh[] = {"O", "P", "Q", "R", "S"};
    PriorityQueue<Song> pq = new PriorityQueue<>((x,y) -> 
                                (x.startTime - y.startTime));
    public String solution(String m, String[] musicinfos) {      
        for(String music : musicinfos) {
            String[] str = music.split(",");
            int start = time(str[0]); int end = time(str[1]);
            int gap = end - start;

            String value = invert(str[3]); // 원본
            value = value.repeat(gap/value.length()) + value.substring(0, gap%value.length());
            
            pq.offer(new Song(start, gap, str[2], value));
        }
        
        m = invert(m);
               
        int max = 0; // 재생 시간
        String answer = "";
        while(!pq.isEmpty()) {
            Song now = pq.poll();
            if(max >= now.gap) continue;
            
            if(now.value.contains(m)) {
                max = now.gap;
                answer = now.name;
            }
        }
        
        if(max == 0) return "(None)";     
        return answer;
    }
    
    public String invert(String s) {
        for(int i=0; i<originsh.length; i++) {
            s = s.replace(originsh[i], nextsh[i]);
        }
        return s;
    }
    
    public int time(String s) {
        String[] str = s.split(":");
        return Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
    }
}

class Song {
    int startTime;
    int gap;
    String name;
    String value;
    Song(int startTime, int gap, String name, String value) {
        this.startTime = startTime;
        this.gap = gap;
        this.name = name;
        this.value = value;
    }
}