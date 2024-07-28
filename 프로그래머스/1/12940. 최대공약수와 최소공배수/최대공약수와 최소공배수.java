class Solution {
    public int[] solution(int n, int m) {
        int[] answer = new int[2];
        int gc = gcd(n, m);
        
        answer[0] = gc;
        answer[1] = n * m / gc;
        return answer;
    }
    
    public int gcd(int a, int b) {
        if(a == 0) {
            return b;
        }
        return gcd(b%a, a);        
    }
}