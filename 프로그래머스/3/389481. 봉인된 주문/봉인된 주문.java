import java.util.*;
class Solution {
    public String solution(long n, String[] bans) {
        long[] num = new long[bans.length];
        for(int i=0; i<bans.length; i++) {
            num[i] = idx(bans[i]);           
        }
        Arrays.sort(num);
        
        int last = 0;
        int front = 0;
        for(int i=0; i<num.length; i++) {
            if(num[i] - i - 1 < n) {
                front++;
                continue;
            }
            last = i;
            break;
        }
        
        while(n+(long)front == num[last]) {
            front++;
            last++;
        }
        
        return st(n + (long)front);
    }
    
    public String st(long n) {
        StringBuilder sb = new StringBuilder();
        while(true){
            sb.append((char)('a'+ (long)(n%(long)26 == 0 ? 26 : n%(long)26) - (long)1));
            n=(n-1)/(long)26;
            if(n==0) {
                break;
            }
        }
       
        return sb.reverse().toString();
    }
    
    public long idx(String str) {
        long total = (long)Math.pow(26, str.length());      
        long answer = 0;

        for(int i=0; i<str.length(); i++) {
            total /= 26;
            answer += total * (str.charAt(i) -'a' + 1L);
        }
        return answer;
    }
}