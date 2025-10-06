class Solution {
    public long solution(int w, int h) {
        int div = gcd(w, h);
        int r = w/div;
        int c = h/div;
        
        long cnt = 0;
        double pre = 0;
        for(int i=1; i<=r; i++) {
            cnt += (long)Math.ceil(i * (double)c/r) - (long)pre;
            pre = i * (double)c/r;
        }
        return (long)w*h - cnt * div;
               
    }
    
    public int gcd(int a, int b) {
        if(b == 0) {
            return a;
        }
        
        return gcd(b, a%b);       
    }
}