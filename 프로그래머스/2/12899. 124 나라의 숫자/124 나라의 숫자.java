class Solution {
    String[] value = {"1", "2", "4"};
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();

        // 3^자리수가 개수 
        // 1. 자리수 판별
        // 2. n에서 나머지 구간합 빼주기 
        
        int jarisu = 0;
        for(int i=1; i<=30; i++) {
            int len = (int)Math.pow(3, i);
            if(n > len) { // 다음자리
                n = n - len;
                continue;
            }
            jarisu = i;
            break;
        }
        
        n--;
        int cnt = 0;
        int max = (int)Math.pow(3, jarisu);
        while(cnt < jarisu) {
            max = max/3;
            sb.append(value[n/max]);
            n = n%max;            
            cnt++;
        }
                
        return sb.toString();
    }
}