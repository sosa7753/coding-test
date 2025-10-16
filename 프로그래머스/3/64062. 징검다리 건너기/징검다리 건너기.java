class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        // 이진탐색으로 몇 명을 정하기
        // stone[i]-mid 만큼 빼주자. 그때 k이상의 0이하인 구간이 생기면 실패
        
        int L = 0;
        int R = Integer.MAX_VALUE;
        while(L<=R) {
            int mid = (L+R)/2;
            
            if(check(stones, k , mid)) {
                answer = mid;
                L = mid + 1;
            }else {
                R = mid - 1;
            }
        }
        return answer + 1;
    }
    
    public boolean check(int[] stones, int k, int mid) {
        int gap = 0;
        for(int i=0; i<stones.length; i++) {
            if(gap >= k) {
                return false;
            }
            
            if(stones[i] - mid <= 0) {
                gap++;
            }else {
                gap = 0;
            }
         }
        
         if(gap >= k) {
             return false;
         }
         return true;
    }
}