class Solution {
    int MOD = 10007;
    public int solution(int n, int[] tops) {       
        int[] ok = new int[n]; // 오른쪽 마름모를 사용 
        int[] no = new int[n]; // 오른쪽 마름모를 사용 X 
        
        ok[0] = 1;
        if(tops[0] == 1) {
            no[0] = 3;
        }else {
            no[0] = 2;
        }
        
        for(int i=1; i<n; i++) {
            ok[i] = ok[i-1] + no[i-1]; // 오른쪽 마름모를 쓰면 전부 가능
            
            if(tops[i] == 1) { 
                no[i] = (no[i-1] * 3 + ok[i-1] * 2) % MOD; 
            }else {
                no[i] = (no[i-1] * 2 + ok[i-1]) % MOD;
            }
        }
        
        return (ok[n-1] + no[n-1]) % MOD;
    }
}