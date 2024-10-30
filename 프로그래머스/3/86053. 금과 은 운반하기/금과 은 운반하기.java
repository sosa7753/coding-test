class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long L = 0;
        long R = 4 * (long)Math.pow(10, 14);
        
        while(L <= R) {
            long mid = (L + R)/2;
                     
            long maxG = 0;
            long maxS = 0;
            long gs = 0;
            for(int i=0; i<t.length; i++) {
                long cnt = ((mid+(long)t[i])/(long)t[i])/2L; 
                long k = Math.min((long)g[i], (long)(w[i])*cnt);
                maxG += k; 
                gs += k + Math.min((long)s[i], (long)(w[i])*cnt - k); 
                maxS += Math.min((long)s[i], (long)(w[i])*cnt);                
            }
            
            if((long)a <= maxG && (long)b <= maxS && (long)(a+b) <= gs) {
                R = mid - 1;
            }else {
                L = mid + 1;
            }
        }
        return L;
    }
}