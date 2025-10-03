class Solution {
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        char[] c = {'1', '2', '4'};

        while(n > 0) {
            n--; // 0기반으로 이동 
            sb.append(c[n%3]); // 0,1,2
            n /=3;        
        }
        return sb.reverse().toString();
    }
}