import java.util.*;
class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        int idx = 0;
        
        // 인덱스, 시작시각, 남은시각 
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (x[1] - y[1]));
        
        // 인덱스 시작시간, 남은시간
        Stack<int[]> stack = new Stack<>();
        
        // 데이터 넣기
        for(int i=0; i<plans.length; i++) {
            pq.offer(new int[] {i, time(plans[i][1]), Integer.parseInt(plans[i][2])});
        }
    
        int[] pre = pq.poll();
        int time = pre[1];
        
        while(!pq.isEmpty()) {
            int[] now = pq.peek();
            
            // 새로운 과제 시작
            if(time + pre[2] > now[1]) {
                stack.push(new int[] {pre[0], pre[1], pre[2] + time - now[1]});
            }else if(time + pre[2] == now[1]) {
                answer[idx++] = plans[pre[0]][0];
            }else { // 과제를 끝내고 시작까지 남은 시간이 있음. 
                answer[idx++] = plans[pre[0]][0];
                int r = now[1] - time - pre[2];
                while(!stack.isEmpty()) {
                    int[] remain = stack.pop();
                    
                    if(r >= remain[2]) {
                        answer[idx++] = plans[remain[0]][0];
                        System.out.print(remain[0] + " ");
                        r -= remain[2];
                    }else {
                        remain[2] -= r;
                        stack.push(remain);
                        break;
                    }
                }
            }      
            pre = pq.poll();
            time = pre[1];
        }
        
        answer[idx++] = plans[pre[0]][0];
        
        while(!stack.isEmpty()) {
            int[] plan = stack.pop();
            answer[idx++] = plans[plan[0]][0];
        }
                
        return answer;
    }
    
    public int time(String clock) {
        String[] s = clock.split(":");
        
        return Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
    }
}