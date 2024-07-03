class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        
        int cnt = n;
        while(cnt >= a) {
            int change = (cnt/a) * b;
            answer += change;
            cnt = cnt%a + change;
        }
        return answer;
    }
}