class Solution {
    // (0,0) -> (8,12)   (1,1.5)   =  (2/3 , 1)
    public long solution(int w, int h) {   
        long x = (long)w;
        long y = (long)h;
        
        return lose(w, h);
    }
    
    public long lose(long x, long y) {
        long result = x * y;
        
        long first = 0;
        long second = 0;
        if(x >= y) {
            first = x;
            second = y;
        }else {
            first = y;
            second = x;
        }
        
        double startS = 0;
        // first 순회
        for(int i=1; i<=first; i++) {
            double nextY = ((double)second/(double)first) * (double)i;
            
            if(nextY < startS + 1) {
                result--;
            }else if(nextY == startS + 1) {
                result--;
                startS = nextY;
            }else {
                result = result - ((int)nextY - (int)startS + 1);   
                int tmp = (int)nextY;
                startS = (double)tmp;
            }
        }
        return result;
    }
}