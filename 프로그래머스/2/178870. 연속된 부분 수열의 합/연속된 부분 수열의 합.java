import java.util.*;
class Solution {
    public int[] solution(int[] sequence, int k) {
        List<int[]> list = new ArrayList<>();
        
        int[] answer = new int[2];
      
        int n = sequence.length;
        
        int p1 = 0; // 시작 포인터
        int p2 = 0; // 끝 포언터
        
        int sum = sequence[p1];
        
        // 합이 k인 부분 수열을 찾는 로직
        while(p1<=p2 && p1 <= n-1 && p2 <= n-1) {      
           if(sum < k) { 
               if(p2 == n-1) {
                   break;
               }
               p2++;

               sum = sum + sequence[p2];
               continue;
           }
           
           if(sum == k) {
               list.add(new int[] {p1, p2});
               
               if(p2 == n-1) {
                   break;
               }
               p2++;
               p1++;
               sum = sum - sequence[p1-1] + sequence[p2];
               continue;
           }
            
           if(sum > k) {
               p1++;
               sum = sum - sequence[p1-1];
               continue;
           }         
        }
        
        int m = list.size();
        int min = Integer.MAX_VALUE;
        
        for(int i=0; i<m; i++) {
            int[] value = list.get(i);
            
            if(min > value[1] - value[0]) {
                min = value[1] - value[0];
                answer[0] = value[0];
                answer[1] = value[1];
            }
        }
        return answer;
    }
}