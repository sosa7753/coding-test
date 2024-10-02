import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> max = new PriorityQueue<>((x,y) -> (y-x));
        PriorityQueue<Integer> min = new PriorityQueue<>();
        
        // 해당 값 제거시, 최대면++, 최소면--
        Map<Integer, Integer> map = new HashMap<>(); 
        
        int cnt = 0;
        int remove = 0;
        for(int i=0; i<operations.length; i++) {
            String[] s = operations[i].split(" ");
            
            // 숫자 입력 
            if("I".equals(s[0])) {
                max.offer(Integer.parseInt(s[1]));
                min.offer(Integer.parseInt(s[1]));
                cnt++;
                continue;
            }
            
            // 작동 
            if("D".equals(s[0]) && !max.isEmpty() && !min.isEmpty()) {
                remove++;
                if(cnt == remove) {
                    max.clear();
                    min.clear();  
                    map.clear();
                }else {
                    if("-1".equals(s[1])) { // 오름차순 큐에서 삭제
                        while(!min.isEmpty()) {
                            int check = min.poll(); 
                            if(map.containsKey(check) && map.get(check) >= 1) {
                                map.put(check, map.get(check)-1);
                            }else {
                                map.put(check, map.getOrDefault(check, 0) -1); 
                                break;
                            }
                        }
                    }else {
                        while(!max.isEmpty()) {
                            int check = max.poll();                            
                            if(map.containsKey(check) && map.get(check) <= -1) {
                                map.put(check, map.get(check)+1);
                            }else {
                                map.put(check, map.getOrDefault(check, 0) + 1);
                                break;
                            }
                        }
                    }
                }              
            }        
        }
                      
        if(max.isEmpty() || min.isEmpty()) {
            return new int[] {0,0};
        }
        
        int[] answer = new int[2];
        
        // 최소큐 보정 
        while(!min.isEmpty()) {
              int check = min.poll(); 
              if(map.containsKey(check) && map.get(check) >= 1) {
                 map.put(check, map.get(check)-1);
              }else {                 
                 answer[1] = check;
                 break;
              }
        }
        
        // 최대큐 보정
        while(!max.isEmpty()) {
              int check = max.poll();                            
              if(map.containsKey(check) && map.get(check) <= -1) {
                 map.put(check, map.get(check)+1);
              }else {                
                 answer[0] = check;
                 break;
              }
        }

        return answer;
    }
}