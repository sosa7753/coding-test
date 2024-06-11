import java.util.*;
class Solution {
    int[][][] dp; // numbers에서 눌러야 할 인덱스, 왼손 위치, 오른손 위치
    int[][] w; // 각 위치에서 각 위치까지 이동 가중치 배열 0~9
    String number;
    int n;
    public int solution(String numbers) {
        
        number = numbers;
        n = numbers.length();
        w = new int[10][10];
        
        dp = new int[n][10][10];
        
        // 왼손 오른손 위치 초기화 
        for(int i=0; i<n; i++) {
            for(int j=0; j<10; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
       
        // 이동 배열 초기화
        move();
                             
        return solve(0, 4, 6);
    }
    
    public int solve(int idx, int left, int right) {
        // 문자열 끝까지 탐색한 경우
        if(idx==n) {
            return 0;
        }
        
        // 메모이제이션 
        if(dp[idx][left][right] != -1) {
            return dp[idx][left][right];
        }
        
        int num = number.charAt(idx) -'0'; // 가야할 숫자
        int value = Integer.MAX_VALUE;
        
        // 왼손 이동 
        if(num != right) {
            value = Math.min((solve(idx+1, num, right)+w[left][num]), value);
        }
        
        // 오른손 이동
        if(num != left) {
            value = Math.min((solve(idx+1, left, num) +w[right][num]), value);
        }
        
        return dp[idx][left][right] = value;          
    }
    
    public void move() {
        
        int[] start = new int[2];
        int[] end = new int[2];
             
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                // 자기 자신을 누르는 경우
                if(i==j) {
                    w[i][j] = 1;
                    continue;
                }
                
                // 시작 버튼에서 다음 버튼으로 갈 때                
                if(i==0) {
                   start[0] = 3;
                   start[1] = 1;
                }else {
                   start[0] = (i-1)/3;
                   start[1] = (i-1)%3;
                }
                
                if(j==0) {
                   end[0] = 3;
                   end[1] = 1;
                }else {
                   end[0] = (j-1)/3;
                   end[1] = (j-1)%3;
                }
                
                int[] moving = new int[2];
                moving[0] = Math.abs(start[0] - end[0]);
                moving[1] = Math.abs(start[1] - end[1]);
                
                int min = Math.min(moving[0], moving[1]); // 1마다 3씩 증가
                int max = Math.max(moving[0], moving[1]) - min; // 나머지는 2씩 증가
                
                w[i][j] = min * 3 + max * 2;              
            }
        }
    }
}