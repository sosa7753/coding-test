import java.util.*;
class Solution {
    Set<Integer> set = new HashSet<>();
    Map<Integer, Set<Integer>> map = new HashMap<>();
    public int solution(int N, int number) {
        int answer = -1;
                   
        for(int i=1; i<=8; i++) {
            set = updateSet(N, i);          
            if(set.contains(number)) {
                answer = i;
                break;
            }
            
        }
        return answer;
    }
    
    public Set updateSet(int N, int cnt) {
        Set<Integer> next = new HashSet<>();
        for(int i=1; i<=cnt/2; i++) {
            Set<Integer> set1 = map.get(i);
            Set<Integer> set2 = map.get(cnt-i);
            
            for(int s1 : set1) {
                for(int s2 : set2) {
                    next.add(s1 + s2);
                    next.add(Math.abs(s1-s2));
                    next.add(s1 * s2);
                    if(s2 != 0) {
                        next.add(s1 / s2);
                    }
                    
                    if(s1 != 0) {
                        next.add(s2 / s1);
                    }                               
                }
            }   
        }
              
        String str = String.valueOf(N).repeat(cnt);
        next.add(Integer.parseInt(str)); 
        
        map.put(cnt, next);  
        return next;
    }
}