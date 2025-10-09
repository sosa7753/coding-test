class Solution {
    long INF = 10007L;
    public int solution(int n, int[] tops) {
        long[] ok = new long[n]; // 오른쪽 마름모만
        long[] no = new long[n]; // 그 외
        
        ok[0] = 1;
        if(tops[0] == 1) {
            no[0] = 3;
        }else {
            no[0] = 2;
        }
        
        for(int i=1; i<n; i++) {
            ok[i] = (ok[i-1] + no[i-1])%INF;
            
            if(tops[i] == 1) {
                no[i] = (no[i-1] * 3 + ok[i-1] * 2)%INF;
            }else {
                no[i] = (no[i-1] * 2 + ok[i-1])%INF;
            }
        }
        return (int)((ok[n-1] + no[n-1])%INF);
    }
}