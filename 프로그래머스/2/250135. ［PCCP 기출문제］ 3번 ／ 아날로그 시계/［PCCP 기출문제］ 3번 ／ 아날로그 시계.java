class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = -1;
        
        int start = toSecond(h1, m1, s1);
        int end = toSecond(h2, m2, s2);
        
        int n = 0;
        if(now(start)) {
            n = 1;
        }
        // 끝 시간까지 울리는 횟수 - 시작 시간까지 울리는 횟수 + 현재 시간에 울리는지
        answer = cal(end) - cal(start) + n;
        
        
        return answer;
    }
    
    public int cal(int time) {
        int sm = time * 59/3600; // 분침이 한바퀴 도는데 초침이랑 59번 겹침.
                                 // 1번 울리는데 3600/59초가 걸린다.
                                 // 1초에는 59/3600 번이 울린다.
        
        int sh = time * 719/43200; // 시침이 한바퀴 도는데 43200초가 걸림.
                                   // 60초에 한번 겹치고 따라서 60분에 60번 겹친다.
                                   // 즉 12시간에는 720번이 겹치는데, 직전까지 올 때
                                   // 한 번을 빼주면 719번이 된다.
        
        int a = 0;
        if(43200 <= time) { // time이 12시를 넘어가면 2번 마다 겹친다.
            a = 2;            
        }else {
            a = 1;
        }
        
        return sm + sh - a;
    }
    
    public int toSecond(int h, int m, int s) {
        return h * 3600 + m * 60 + s;
    }
    
    public boolean now(int time) {
        return time*59%3600 == 0 || time * 719%43200==0; // 현재 울리는지
    }
}