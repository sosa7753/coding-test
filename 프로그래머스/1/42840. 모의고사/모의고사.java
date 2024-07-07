import java.util.*;
class Solution {
    int[] first = {1, 2, 3, 4, 5};
    int[] second = {2, 1, 2, 3, 2, 4, 2, 5};
    int[] third = {3, 3, 1, 1, 2, 2, 4, 4 ,5, 5};
    public int[] solution(int[] answers) {
        // 인덱스, 값
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> {
            if(x[1] == y[1]) {
                return x[0] - y[0];
            }
            return y[1] - x[1];
        });
        
        int[] result = new int[3];  // 1, 2, 3
        
        for(int i=0; i<answers.length; i++) {
            if(first[i%5] == answers[i]) {
                result[0]++;
            }
            
            if(second[i%8] == answers[i]) {
                result[1]++;
            }
            
            if(third[i%10] == answers[i]) {
                result[2]++;
            }
        }
        
        for(int i=0; i<result.length; i++) {
            pq.add(new int[] {i+1, result[i]});
        }
        
        List<Integer> list = new ArrayList<>(); 
        int[] large = pq.poll();
        list.add(large[0]);
        int max = large[1];
        
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            if(max == now[1]) {
                list.add(now[0]);
                continue;
            }
            break;
        } 
        
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}