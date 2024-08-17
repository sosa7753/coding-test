import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        Map<String, Integer> map = new HashMap<>();
        int sum = 0;
        for(int i=0; i<want.length; i++) {
            map.put(want[i], number[i]);
            sum += number[i];
        }
        
        for(int i=0; i<discount.length; i++) {
            if(i < sum) {
                if(map.containsKey(discount[i])) {
                    map.put(discount[i], map.get(discount[i])-1);
                }
                continue;
            }
            
            if(check(map)) {
                answer++;
            }
            
            if(map.containsKey(discount[i])) {
                map.put(discount[i], map.get(discount[i]) - 1);
            }
            
            if(map.containsKey(discount[i-sum])) {
                map.put(discount[i-sum], map.get(discount[i-sum]) + 1);
            }
        }     
        
        if(check(map)) {
            answer++;
        }
        return answer;
    }
    
    public boolean check(Map<String, Integer> map) {
        for(int value : map.values()) {
            if(value > 0) {
                return false;
            }
        }
        return true;
    }
}