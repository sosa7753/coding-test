class Solution {
    public int solution(int n, int a, int b) {
        // 좌표 N = 1
        int A = (n + a) -1;
        int B = (n + b) -1;
        
        int max =(int)(Math.log(n) / Math.log(2));
        int answer = 1;
        while(answer <= max) {
            if(A/2 == B/2) {
                break;
            }
            A = A/2;
            B = B/2;
            answer++;
        }
        return answer;
    }
}