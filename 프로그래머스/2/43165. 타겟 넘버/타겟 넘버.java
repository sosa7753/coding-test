class Solution {
    public int solution(int[] numbers, int target) {
        int answer = DFS(numbers, 0, 0, target);
        return answer;
    }
    
    public int DFS(int[] numbers, int value, int depth, int target) {
        if(depth == numbers.length) {
            if(value == target) {
                return 1;              
            }
            return 0;
        }
               
       return DFS(numbers, value+numbers[depth], depth+1, target) +         
           DFS(numbers, value-numbers[depth], depth+1, target);        
    }
}