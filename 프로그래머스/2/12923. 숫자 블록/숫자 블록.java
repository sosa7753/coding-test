class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)(end-begin) + 1];
        int idx = 0;
            
        for(int i= (int)begin; i<=(int)end; i++) {
            answer[idx++] = prime(i);
        }
        return answer;
    }
    
    public int prime(int num) {
        if(num == 1) {
            return 0;
        }
        
        int max = 1;
                
        for(int i=2; i<=Math.sqrt(num); i++) {
            if(num%i == 0) {
                max = Math.max(max, i);
                if(num/i <= 10000000) {
                    return num/i;
                }
            }
        }     
        return max;        
    }
}