import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        Set<Integer> set = new HashSet<>();
        
        for(int i=0; i<numbers.length-1; i++) {
            for(int j=i+1; j<numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }
        
        for(int value : set) {
            pq.offer(value);
        }
        int[] answer = new int[pq.size()];
        int idx = 0;
        while(!pq.isEmpty()) {
            answer[idx++] = pq.poll();
        }
        return answer;
    }
}