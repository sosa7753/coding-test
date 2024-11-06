class Solution {
    public int solution(int[] stones, int k) {
        int L = 1;
        int R = 200000000;
        while(L <= R) {
            int mid = (L + R)/2;
            
            if(check(stones, mid, k)) {
                L = mid+1;
            }else {
                R = mid-1;
            }
        }
        return R;
    }
    
    public boolean check(int[] stones, int mid, int k) {
        int cnt = 0;
        for(int i=0; i<stones.length; i++) {
            if(stones[i] < mid) {
                cnt++;
                if(cnt == k) {
                    return false;
                }
            }else {
                cnt = 0;
            }
        }
        return true;
    }
}