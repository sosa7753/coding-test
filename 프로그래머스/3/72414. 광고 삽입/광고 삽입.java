class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int adTime = time(adv_time);
        int end = time(play_time);
        
        long[] ad = new long[end + 2];
        
        for(String log : logs) {
            String[] str = log.split("-");
            int s = time(str[0]);
            int e = time(str[1]);
            
            ad[s]++;
            ad[e]--;
        }
        
        for(int i=1; i<=end; i++) { // 누적합 연산
            ad[i] += ad[i-1];
        }
        
        for(int i=1; i<=end; i++) { // 총 시청시간 연산
            ad[i] += ad[i-1];
        }
        
       long max = ad[adTime - 1];
       int start = 0;
       for (int i = adTime; i <= end; i++) {
            long current = ad[i] - ad[i - adTime];
            if (current > max) {
                max = current;
                start = i - adTime + 1;
            }
        }

        return clock(start);
    }
    
    public int time(String str) {
        String[] s = str.split(":");
        return Integer.parseInt(s[0]) * 3600 +
               Integer.parseInt(s[1]) * 60 + 
               Integer.parseInt(s[2]);
    }
    
    public String clock(int t) {
        int h = t / 3600;
        t %= 3600;
        int m = t / 60;
        t %= 60;
        int s = t % 60;
        
        return String.format("%02d:%02d:%02d", h,m,s);

    }
}