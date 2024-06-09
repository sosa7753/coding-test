import java.util.*;
// 큐에 먼저 다 넣기
// 완료까지 남은 일수로 큐에 넣기 ex) 7 3 9
// 큐에서 하나 꺼내서, time에 추가 : 0 -> 7
// peek()값이 time보다 작으면 작지 않은 값까지 꺼내기 
// 그 개수만큼 배포한 뒤, 반복 
// time = 7, peek() = 9; -> peek-time만큼 다시 소진하기 
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        // 정답을 저장할 리스트 
        List<Integer> list = new ArrayList<>();
        
        // 프로세스 큐 생성 및 초기화
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<progresses.length; i++) {
            queue.offer((100-progresses[i] + speeds[i]-1)/speeds[i]);
        }
         
        int time = 0;
        while(!queue.isEmpty()) {

            int remain = queue.poll();
            
            time += remain - time;
            
            int cnt = 1;
            while(!queue.isEmpty()) {
                if(time >= queue.peek()) { // 이미 완료된 프로세스
                    queue.poll();
                    cnt++;
                    continue;
                }
                break;
            }
            list.add(cnt);
        }
        
        int[] answer = new int[list.size()];
        int idx = 0;
        for(int i=0; i<list.size(); i++) {
            answer[idx++] = list.get(i);
        }
        
        return answer;
    }
}