class Solution {
    int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        
        DFS(numbers, 0, 0, target);
        return answer;
    }
    
    public void DFS(int[] numbers, int value, int depth, int target) {
        if(depth == numbers.length) {
            if(value == target) {
                answer++;                
            }
            return;
        }
        
        DFS(numbers, value+numbers[depth], depth+1, target);
        DFS(numbers, value-numbers[depth], depth+1, target);        
    }
}