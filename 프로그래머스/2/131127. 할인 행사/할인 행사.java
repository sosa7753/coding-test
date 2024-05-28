import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        // 초기화 
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<want.length; i++) {
            map.put(want[i], number[i]);
        }
        
        // 10번 계산
        for(int i=0; i<10; i++) {
            if(map.containsKey(discount[i])) {  
                map.put(discount[i], map.get(discount[i]) -1);              
            }
        }
        
        if(check(map)) {
            answer++;
        }
       
        int p1 = 0;
        int p2 = 10; 
        while(p2 <= discount.length-1) {
            
            if(map.containsKey(discount[p1])) {
                map.put(discount[p1], map.get(discount[p1]) + 1);
            }
            
            if(map.containsKey(discount[p2])) {
                map.put(discount[p2], map.get(discount[p2]) - 1);
            }
            
            if(check(map)) {
                answer++;
            }
            p1++;
            p2++;
        }
        return answer;
    }
    
    public boolean check(Map<String, Integer> map) {
        // for(Map.Entry<String, Integer> entry : map.entrySet()) {
        //     if(entry.getValue() !=0) {
        //         return false;
        //     }
        // }
        
        for(int value : map.values()) {
            if(value != 0) {
                return false;
            }
        }
        return true;
    }        
}