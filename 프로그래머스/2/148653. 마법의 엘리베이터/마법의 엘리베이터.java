class Solution {
    int answer = 0;
    public int solution(int storey) {
        
        updown(storey, 0);
        return answer;
    }
    
    public void updown(int storey, int cnt) {
        if(storey == 0) {
            answer = cnt;
            return;
        }
        
        int value = storey%10;
        int next = storey/10;
        
        int check = next%10; 
            
        if(value <= 4) {
            updown(next, cnt + value);
        }else if(value >=6) {
            updown(next + 1, cnt + 10 - value);
        }else {
            if(check <= 4) {
                updown(next, cnt + value);
            }else {
                updown(next+1, cnt + 10 - value);
            }
        }
    }
}