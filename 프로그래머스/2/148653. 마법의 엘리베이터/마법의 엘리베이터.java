class Solution {
    public int solution(int storey) {
        return DFS(storey);
    }
    
    // 16 -> 20 -> 0
    public int DFS(int storey) {
        if(storey == 0) {
            return 0;
        }
            
        int value = storey%10;
       
        // 0~4 일 때 n만큼 감소, 6~9일 때 10 - n 만큼 감소 
        // 5일 땐 다음 값이 0~4면 +5, 5~9면 10 - n만큼 감소 
        if(value <= 4) {
            return value + DFS(storey/10);
        }else if(value >= 6) {
            return 10 - value + DFS(storey/10 + 1) ;
        }else {
            int check = (storey%100)/10;
            if(check <=4) {
                return value + DFS(storey/10);
            }else {
                return value + DFS(storey/10 + 1);
            }
        }
    } 
}