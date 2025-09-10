import java.util.*;
class Solution {
    public int solution(int[] topping) {
        
        Map<Integer, Integer> b = new HashMap<>();
        for(int top : topping) {
            b.put(top, b.getOrDefault(top, 0) + 1);
        }
        
        int answer = 0;
        
        Map<Integer, Integer> a = new HashMap<>();    
        for(int top : topping) {
            a.put(top, a.getOrDefault(top, 0) + 1);
            
            int v = b.get(top);
            if(v == 1) {
                b.remove(top);        
            }else {
                b.put(top, v-1);
            }
            
            if(a.size() == b.size()) {
                answer++;
            }else if(a.size() > b.size()) {
                break;
            }
        }
        
        return answer;
    }
}