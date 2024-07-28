import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x,y) -> {
            int first = Integer.parseInt(String.valueOf(x) + String.valueOf(y));
            int second = Integer.parseInt(String.valueOf(y) + String.valueOf(x));
            return second - first;
        });
        
        for(int i=0; i<numbers.length; i++) {
            pq.offer(numbers[i]);
        }
        
        StringBuilder sb = new StringBuilder();
        
        int f = pq.peek();
        if(f == 0) {
            return "0";
        }
        
        while(!pq.isEmpty()) {
            int now = pq.poll();
            sb.append(String.valueOf(now));
        }
        
        return sb.toString();
    }
}