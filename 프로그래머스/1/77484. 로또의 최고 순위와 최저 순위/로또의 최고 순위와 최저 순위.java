import java.util.*;
class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        
        Arrays.sort(win_nums);
        
        int save = 0;
        int cnt = 0;
        for(int i=0; i<lottos.length; i++) {
            if(lottos[i] == 0) {
                cnt++;
                continue;
            }
            
            int left = 0;
            int right = 5;
            
            while(left <= right) {
                int mid = (left + right)/2;
                
                if(win_nums[mid] == lottos[i]) {
                    save++;
                    break;
                }else if(win_nums[mid] < lottos[i]) {
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }          
            }
        }
        
        if(save < 2) {
            answer[1] = 6;
            if(cnt + save < 2) {
                answer[0] = 6;
            }else {
                answer[0] = 7 - save - cnt;
            }           
        }else {
            answer[1] = 7 - save;
            answer[0] = 7 - save - cnt;
        }
        
        return answer;
    }
}