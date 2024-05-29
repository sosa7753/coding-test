import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        PriorityQueue<Car> pq = new PriorityQueue<>((x,y) -> {
            if(Integer.parseInt(x.number) > Integer.parseInt(y.number)) {
                return 1;
            }else {
                return -1;
            }
        });
               
        Map<String, Integer> inMap = new HashMap<>(); // 차번호 - 입차시간
        Map<String, Integer> cashMap = new HashMap<>(); // 차번호 - 누적시간
        
        for(int i=0; i<records.length; i++) {
            String[] str = records[i].split(" ");
            
            // 입차
            if("IN".equals(str[2])) {
                inMap.put(str[1], time(str[0]));
                continue;
            }
            
            // 출차
            if("OUT".equals(str[2])) {
                int in = inMap.get(str[1]); // 최근 입차 시간 
                int out = time(str[0]);
                              
                cashMap.put(str[1], cashMap.getOrDefault(str[1], 0) + out-in);
                inMap.remove(str[1]);
            }
        }
        
        // 아직 출차를 안한 차 계산 
        for(Map.Entry<String, Integer> entry : inMap.entrySet()) {
            int in = entry.getValue();
            int out = time("23:59");
            
            cashMap.put(entry.getKey(), 
                        cashMap.getOrDefault(entry.getKey(), 0) + out-in);
        }
        
        
        for(Map.Entry<String, Integer> entry : cashMap.entrySet()) {
            pq.add(new Car(entry.getKey(), cash(fees, entry.getValue())));
        }
        
        int[] answer = new int[cashMap.size()];
        int idx = 0;
        while(!pq.isEmpty()) {
            Car car = pq.poll();
            answer[idx++] = car.cash;
        }
        
        return answer;
    }
    
    // 시각을 시간으로 변환 
    public int time(String str) {
        String[] s = str.split(":");
        
        return Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
    }
    
    public int cash(int[] fees, int time) {
                  
        // 기본 시간보다 작음
        if(time <= fees[0]) {
            return fees[1];
        }
        
        // 추가 요금 올림 처리
        int add = ((time-fees[0] + fees[2] - 1)/fees[2]) * fees[3]; 
        return add + fees[1];
    }
}

class Car {
    String number;
    int cash;
    
    Car(String number, int cash) {
        this.number = number;
        this.cash = cash;
    }
}