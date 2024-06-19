class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int n1 = binary(n);
        int number = n+1;
        while(true) {
            if(binary(number) == n1) {
                answer = number;
                break;
            }
            number++;
        }
        return answer;
    }
    
    public int binary(int num) {
        int tmp = num;
        
        int cnt = 0;
        
        while(num !=1) {
            if(num%2 == 1) {
               cnt++; 
            }
            
            num = num/2;
        }
        return cnt+1;
    }
}