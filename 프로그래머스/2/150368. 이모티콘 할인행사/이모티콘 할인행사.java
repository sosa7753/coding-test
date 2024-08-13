class Solution {
    int[] percent = {10, 20, 30, 40};
    int[] arr;
    int member = 0;
    int cash = 0;
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        
        arr = new int[emoticons.length];
        
        DFS(users, emoticons, 0);
        
        answer[0] = member;
        answer[1] = cash;
        return answer;
    }
    
    public void DFS(int[][] users, int[] emoticons, int cnt) {
        if(cnt == emoticons.length) {
            cal(users, emoticons);
            return;
        }
        
        for(int i=0; i<4; i++) {
            arr[cnt] = percent[i];
            DFS(users, emoticons, cnt+1);
        }      
    }
    
    public void cal(int[][] users, int[] emoticons) {
        
        int user = 0;
        int money = 0;
        
        for(int[] u : users) {
            int sum = 0;
            int us = 0;
            for(int i=0; i<arr.length; i++) {
                if(arr[i] >= u[0]) {
                    sum += emoticons[i] * (100 - arr[i]) / 100;
                }
                
                if(sum >= u[1]) {
                    us++;
                    break;
                }
            }
            
            if(us == 0) {
                money += sum;
            }else {
                user++;
            }
        }        
        
        if(user > member) {
            member = user;
            cash = money;
        }else if(user == member) {
            if(money > cash) {
                cash = money;
            } 
        }
    }
}