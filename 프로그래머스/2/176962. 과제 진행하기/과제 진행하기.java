import java.util.*;
class Solution {
    public String[] solution(String[][] plans) {
        int p = plans.length;
        String[] answer = new String[p];
        int idx = 0;
        
        // 멈춘 과제 저장소
        Stack<String[]> stack = new Stack<>();
        
        // 시작 시각으로 배열 정렬
        Arrays.sort(plans, (x,y) -> (time(x[1]) - time(y[1])));
        
        // plans 반복 
        for(int i=0; i<p-1; i++) {
            String[] planF = plans[i];
            String[] planN = plans[i+1];
            
            // 다음 과목 시작 시간 - 현재 과목 시작 시간 + playTime
            int gap = time(planN[1]) - (time(planF[1]) + Integer.parseInt(planF[2]));
            
            if(gap < 0) { // 시간안에 못 끝냄. 
                stack.push(new String[] { planF[0], planF[1], String.valueOf(-gap)});
                continue;
            }
            
            if(gap == 0) { // 딱 맞음.
                answer[idx++] = planF[0];
                continue;
            }
            
            if(gap > 0) { // 시간안에 끝내고 여유 시간이 있음.
                answer[idx++] = planF[0];
                
                int gapTmp = gap;
                while(!stack.isEmpty()) { // Stack에 멈춘 과제가 존재 할 때 
                    String[] plan = stack.pop();
                    int remain = Integer.parseInt(plan[2]);
                    
                    if(gapTmp > remain) { // 최근에 멈춘 과제 남은시간 보다 더 여유 있음.
                        answer[idx++] = plan[0];
                        gapTmp = gapTmp - remain;
                        continue;
                    }
                    
                    if(gapTmp == remain) { // 딱 남은 시간 만큼 있음.
                        answer[idx++] = plan[0];
                        break;
                    }
                    
                    if(gapTmp < remain) { // 여유 시간으로 과제를 못끝냄. 
                        plan[2] = String.valueOf(remain - gapTmp);
                        stack.push(plan);
                        break;
                    }
                }
            }
        }
        
        // 마지막 과목 처리
        answer[idx++] = plans[p-1][0];
        
        while(!stack.isEmpty()) {
            String[] plan = stack.pop();
            answer[idx++] = plan[0];
        }
   
        return answer;
    }
    
    // 시작 시각을 변환
    public int time(String clock) {
        int result = 0;
        
        String[] str = clock.split(":");
        
       return result = Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
    }
}