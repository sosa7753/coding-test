import java.util.*;

class Solution {
    public int solution(int n) {
        int ans = 0;

        while(n >=2) {
            if(n%2 == 1) {
                n--;
                ans++;               
            }
            n = n/2;
        }
        
        return ans+1;
    }
}