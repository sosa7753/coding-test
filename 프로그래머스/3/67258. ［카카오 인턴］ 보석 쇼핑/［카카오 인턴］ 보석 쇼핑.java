import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        
        for(int i=0; i<gems.length; i++) {
            set.add(gems[i]);
        }        
        int len = set.size(); // 최소 보석 수 
        
        int min = gems.length + 1; // 범위 최소 
        int p1 = 0;
        int p2 = 0;
        while(p1 <= p2 && p2 < gems.length) {
            map.put(gems[p2], map.getOrDefault(gems[p2], 0) + 1);
            
            if(map.size() == len) { // 조건 만족
                if(min > p2 - p1 + 1) {
                    min = p2 - p1 + 1;
                    answer[0] = p1+1;
                    answer[1] = p2+1;
                }
                
                minus(map, gems[p1]);
                p1++;
                minus(map, gems[p2]);                
            }else {
                p2++;
            }
        } 
    
        return answer;
    }
    
    public void minus(Map<String, Integer> map, String key) {
        if(map.containsKey(key)) {
            int tmp = map.get(key);
            if(tmp == 1) {
                map.remove(key);
            }else {
                map.put(key, tmp-1);
            }
        }       
    }
}