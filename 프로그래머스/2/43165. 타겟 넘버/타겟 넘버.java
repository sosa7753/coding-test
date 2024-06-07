class Solution {
    int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        
        DFS(numbers, target, 0, 0);
        return answer;
    }
    
    public void DFS(int[] numbers, int target, int cnt, int now) {
        if(cnt == numbers.length) {
            if(target == now) {
                answer++;
                return;
            }
            return;
        }     
        DFS(numbers, target, cnt+1, now+numbers[cnt]);
        DFS(numbers, target, cnt+1, now-numbers[cnt]);
    }
}