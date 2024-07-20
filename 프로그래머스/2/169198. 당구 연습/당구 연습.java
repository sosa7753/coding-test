class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        int idx = 0;
        
        // (0,5) (5, n), (5, 0), (m,5)
        for(int i=0; i<balls.length; i++) {
            int min = Integer.MAX_VALUE;
            
            // x = 0
            if(startY != balls[i][1] || startX < balls[i][0]) {
                double d1 = Math.pow((double)(balls[i][0] + startX), 2) + 
                            Math.pow((double)(balls[i][1] - startY), 2);
                
                min = Math.min((int)d1, min);
            }
            
            
            // x = m
            if(startY != balls[i][1] || startX > balls[i][0]) {
                double d2 = Math.pow((double)(2*m - balls[i][0] - startX) , 2) + 
                            Math.pow((double)(balls[i][1] - startY), 2);
                
                min = Math.min((int)d2, min);
            }
            
            // y = 0;
            if(startX != balls[i][0] || startY < balls[i][1]) {
                double d3 = Math.pow((double)(balls[i][0] - startX), 2) + 
                            Math.pow((double)(balls[i][1] + startY), 2);
                
                min = Math.min((int)d3, min);
            }
            
            // y = n;
            if(startX != balls[i][0] || startY > balls[i][1]) {
                double d4 = Math.pow((double)(balls[i][0] - startX), 2) + 
                            Math.pow((double)(2*n - balls[i][1] - startY), 2);
                
                min = Math.min((int)d4, min);
            }
            
            answer[idx++] = min;
        }
        
        return answer;
    }
}