import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        // 각 토핑 종류 개수 세주기 
        Map<Integer, Integer> two = new HashMap<>();
        for(int i=0; i<topping.length; i++) {
            two.put(topping[i], two.getOrDefault(topping[i], 0) + 1);
        }
                
        int p = 0;
        
        Map<Integer, Integer> one = new HashMap<>();
        while(p <= topping.length-2) {
            one.put(topping[p], one.getOrDefault(topping[p], 0) + 1);
            
            if(two.containsKey(topping[p])) {
                if(two.get(topping[p]) == 1) {
                    two.remove(topping[p]);
                }else {
                    two.put(topping[p], two.get(topping[p]) -1);
                }
            }
            
            p++;
            
            if(one.size() == two.size()) {
                answer++;
            }
        }   
        return answer;
    }
}