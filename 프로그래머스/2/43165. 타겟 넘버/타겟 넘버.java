class Solution {
    int answer = 0;
    public int solution(int[] numbers, int target) {
        DFS(numbers, 0, target, 0);        
        return answer;
    }

    public void DFS(int[] numbers, int sum, int target, int cnt) {
        if(cnt == numbers.length) {
            if(target == sum) {
                answer++;
            }
            return;
        } 
        
        DFS(numbers, sum+numbers[cnt], target, cnt+1);
        DFS(numbers, sum-numbers[cnt], target, cnt+1);
    }
}