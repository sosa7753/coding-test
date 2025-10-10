import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int r = rocks.length;
        
        if(r == n) {
            return distance;
        }
        
        Arrays.sort(rocks);
        int[] dist = new int[r+1];  
        for(int i=0; i<r; i++) {
            dist[i] = rocks[i];
        }
        dist[r] = distance; // 2 11 14 17 21 25
        
        int answer = 0;
        int L = 0;
        int R = distance;
        while(L <= R) {
            int mid = (L+R)/2;
            if(check(mid, dist, n)) {
                answer = mid;
                L = mid + 1;
            }else {
                R = mid - 1;
            }
        }
        return answer;
    }
    
    public boolean check(int val, int[] dist, int n) {
        int cnt = n;
        int pre = 0;
        for(int i=0; i<dist.length; i++) {
            if(val > dist[i] - pre) {
                if(cnt == 0) {
                    return false;
                }else {
                    cnt--;
                }
            }else {
                pre = dist[i];
            }
        }
        return true;
    }  
}