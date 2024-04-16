class Solution {
    int[] discount = {10, 20, 30, 40};
    int max = 0;
    int cash = 0;
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        
        int[] percent = new int[emoticons.length];
        
        DFS(users, emoticons, percent, 0);
        answer[0] = max;
        answer[1] = cash;
        
        return answer;
    }
    
    public void DFS(int[][] users, int[] emoticons, int[] percent, int cnt) {
        // 이모티콘 할인 책정 완료
        if(cnt == emoticons.length) {
            cal(users, emoticons, percent);
            return;
        }
        
        for(int i=0; i<discount.length; i++) {
            percent[cnt] = discount[i]; 
            DFS(users, emoticons, percent, cnt+1);
        }
    }
    
    public void cal(int[][] users, int[] emoticons, int[] percent) {
        int sub = 0;
        int result = 0;
        
        for(int i=0; i<users.length; i++) { // 각 유저에 대해 
            int sum = 0;
            boolean isfalse = false;
            for(int j=0; j<emoticons.length; j++) { // 이모티콘 마다 
                
                if(percent[j] < users[i][0]) {
                    continue;
                }
                
                sum += emoticons[j] * (100-percent[j]) / 100;
                if(sum >= users[i][1]) {
                    sub++;
                    isfalse = true;
                    break;
                }
            }
            
            if(!isfalse) {
                result += sum;
            }
        }
        
        if(max < sub) {
            max = sub;
            cash = result;
            return;
        }else if( max == sub && result > cash) {
            max = sub;
            cash = result;
            return;
        }
    } 
}