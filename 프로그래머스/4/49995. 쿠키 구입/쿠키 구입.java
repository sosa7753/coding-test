class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        
        int[] S = new int[cookie.length];
        S[0] = cookie[0];
        for(int i=1; i<S.length; i++) {
            S[i] = S[i-1] + cookie[i];
        }
        

        for(int i=0; i<S.length-1; i++) { // 시작
            for(int j=i+1; j<S.length; j++) { // 끝 
                int check = 0;
                if(i == 0) {
                    check = S[j];
                }else {
                    check = S[j] - S[i-1];
                }
                
                if(check%2 == 1 || answer * 2 > check) {
                    continue;
                }
                
                int L = i;
                int R = j;                
                while(L <= R) {
                    int mid = (L + R)/2;
                    int tmp = 0;
                    if(mid == 0) {
                        tmp = S[j];
                    }else {
                        tmp = S[j]-S[mid-1];
                    }
                    
                    if(check/2 == tmp) {
                        answer = check/2;
                        break;
                    }else if(check/2 > tmp) {
                        R = mid-1;
                    }else {
                        L = mid+1;
                    }
                }
            }
        }
        return answer;
    }
}