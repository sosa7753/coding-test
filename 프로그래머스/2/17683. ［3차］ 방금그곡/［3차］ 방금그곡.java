import java.util.*;
class Solution {
    PriorityQueue<Song> pq = new PriorityQueue<>((x,y) -> {
        if(x.interval == y.interval) {
            return x.s - y.s;
        }else {
            return y.interval - x.interval;
        }
    });
    public String solution(String m, String[] musicinfos) {
        String M = change(m);
        
        for(String music : musicinfos) {
            String[] mu = music.split(",");
            int start = time(mu[0]);
            int gap = time(mu[1]) - start;
            
            String check = change(mu[3]);   
            char[] c = check.toCharArray();
            
            int cnt = 0;
            StringBuilder sb = new StringBuilder();
            while(cnt < gap) {
                sb.append(c[cnt%c.length]);
                cnt++;
            }
            
            
            if(sb.toString().contains(M)) {
                Song song = new Song(start, gap, mu[2]);
                pq.offer(song);
            }
            
        }
        
        if(pq.isEmpty()) {
            return "(None)";
        }
        
        return pq.poll().name;
    }
    
    public int time(String s) {
        String[] str = s.split(":");
        return Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
    }
    
    public String change(String s) {
        String result = s;
        String[] origin = {"A#", "B#", "C#", "D#", "F#", "G#"};
        String[] next = {"H", "I", "J", "K", "L", "M"};
        
        for(int i=0; i<next.length; i++) {
            result = result.replace(origin[i], next[i]);
        }
        return result;
    }
}

class Song {
    int s;
    int interval;
    String name; 
    Song(int s, int interval, String name) {
        this.s = s;
        this.interval = interval;
        this.name = name;
    }
}