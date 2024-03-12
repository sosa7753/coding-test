class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];
        
        int n = wallpaper.length;
        int m = wallpaper[0].length();
        
        int rowMin = Integer.MAX_VALUE;
        int rowMax = 0;
        int colMin = Integer.MAX_VALUE;
        int colMax = 0;
        
        for(int i=0; i<n; i++) { // 행 
            String s = wallpaper[i];
            
            String[] str = s.split("");
            
            for(int j=0; j<str.length; j++) { // 열 
                if("#".equals(str[j])) {
                    int row = i;
                    int col = j;
                    
                    rowMin = Math.min(row, rowMin);
                    rowMax = Math.max(row+1, rowMax);
                    colMin = Math.min(col, colMin);
                    colMax = Math.max(col+1, colMax);
                }
            }
        }
        
        answer[0] = rowMin;
        answer[1] = colMin;
        answer[2] = rowMax;
        answer[3] = colMax;
            
        return answer;
    }
}