class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int b = balls.length;
        
        int[] answer = new int[b];
        for(int i=0; i<b; i++) {
            int min = Integer.MAX_VALUE;
            
            // x == 0
            if(startY != balls[i][1] || startX < balls[i][0]) {
                double d = Math.pow((double)(balls[i][0] + startX), 2) + 
                           Math.pow((double)(balls[i][1] - startY), 2);
                
                min = Math.min(min, (int)d);
            }
            
            // x == m
            if(startY != balls[i][1] || startX > balls[i][0]) {
               double d = Math.pow((double)(2*m - startX - balls[i][0]), 2) + 
                          Math.pow((double)(balls[i][1] - startY), 2);  
                
               min = Math.min(min, (int)d);
            }
            
            // y = 0 
            if(startX != balls[i][0] || startY < balls[i][1]) {
               double d = Math.pow((double)(balls[i][1] + startY), 2) + 
                          Math.pow((double)(balls[i][0] - startX), 2);
                
               min = Math.min(min, (int)d);
            }
            
            // y = n
            if(startX != balls[i][0] || startY > balls[i][1]) {
               double d = Math.pow((double)(2*n - balls[i][1] - startY), 2) + 
                          Math.pow((double)(balls[i][0] - startX), 2); 
            
               min = Math.min(min, (int)d);
            } 
            answer[i] = min;
        }
        
        return answer;
    }
}