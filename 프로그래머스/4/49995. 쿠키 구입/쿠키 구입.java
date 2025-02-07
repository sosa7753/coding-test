class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        
        int[] S = new int[cookie.length+1];       
        for(int i=1; i<S.length; i++) {
            S[i] = S[i-1] + cookie[i-1];
        }
                
        for(int i=1; i<S.length-1; i++) {
            for(int j=i+1; j<S.length; j++) {
                int sum = S[j] - S[i-1];              
                if(sum%2 == 1 || answer * 2 > sum) {
                    continue;
                }
                
                int L = i;
                int R = j;
                while(L<=R) {
                    int mid = (L + R)/2;
                    int check = S[j] - S[mid-1];
                    if(check == sum/2) {
                        answer = sum/2;
                        break;
                    }
                    
                    if(check < sum/2) {
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