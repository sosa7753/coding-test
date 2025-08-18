import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        // 누적 시간을 계산할 맵
        Map<String, Integer> remain = new HashMap<>();
        // 입차 중인 차량 맵 --> 23:59 에서 빼줘야함
        Map<String, Integer> in = new HashMap<>();
        
        for(int i=0; i<records.length; i++) {
            String[] s = records[i].split(" ");
            int c = time(s[0]);
            String car = s[1];
            String type = s[2];
            
            if("IN".equals(type)) {
                in.put(car, c);
                continue;
            }            
            
            if("OUT".equals(type)) {
                int plus = c - in.get(car);
                remain.compute(car, (key, value) -> value == null ? plus : value + plus);
                in.remove(car);
            }       
        }
        
        int max = time("23:59");
        for(Map.Entry<String, Integer> entry : in.entrySet()) {
            int plus = max - entry.getValue();
            remain.compute(entry.getKey(), (key, value) -> value == null ? plus : value + plus);
        }
        
        // 누적 시간 순회해서 계산하기    
        // (X의 누적시간 - 기본시간) / 단위 시간 (올림) *요금
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> 
            (Integer.parseInt(x.name) - Integer.parseInt(y.name)));
                                                     
        for(Map.Entry<String, Integer> entry : remain.entrySet()) {
            pq.offer(new Node(entry.getKey(), entry.getValue()));
        }
                 
        int[] answer = new int[pq.size()];
        int idx = 0;
        while(!pq.isEmpty()) {
            int val = pq.poll().val;
            if(fees[0] >= val) {
                answer[idx++] = fees[1];
            }else {
                answer[idx++] = fees[1] + (int)Math.ceil(((double)val - fees[0])/fees[2]) * fees[3];
            }     
        }                                            

        return answer;
    }
    
    public int time(String clock) {
        String[] str = clock.split(":");
        return Integer.parseInt(str[0])*60 + Integer.parseInt(str[1]);
    }
}

class Node {
    String name;
    int val;
    Node(String name, int val) {
        this.name = name;
        this.val = val;
    }
}