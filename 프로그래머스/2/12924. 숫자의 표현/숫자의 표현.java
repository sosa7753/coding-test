class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int p1 = 1;
        int p2 = 1;
        
        int[] number = new int[n+1];
        for(int i=1; i<=n; i++) {
            number[i] = i;
        }
        
        int sum = 1;
        while(p1<=n && p2<=n && p1<=p2) {
            if(sum == n) {
                answer++;
                p2++;
                if(p2 > n) {
                   break;
                }
                sum += number[p2];
            }else if(sum > n) {
                sum -= number[p1];
                p1++;
            }else {
                p2++;
                if(p2 > n) {
                    break;
                }
                sum += number[p2];
            }
        }
              
        return answer;
    }
}