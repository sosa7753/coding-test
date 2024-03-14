import java.util.*;
class Solution {
    public String solution(String X, String Y) {
        
        Map<String, Integer> map = new HashMap<>();
        
        List<String> list = new ArrayList<>();
        
        boolean allZero = true;
        
        // X먼저 넣기
        for(String s : X.split("")) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        
        // Y로 찾기 
        for(String s : Y.split("")) {
            if(!map.containsKey(s)) {
                continue;
            }
            
            // 존재하는데 0이면, 
            if(map.get(s) == 0) {
                continue;
            }
            
            
            list.add(s);
            map.put(s, map.get(s)-1);    
            
            // 0이 아닐 때 딱 한번 확인 
            if(!"0".equals(s)) {
                allZero = false;
            } 
        }
        
        // 내림차순 정렬하기 
        Collections.sort(list, Collections.reverseOrder());
        
        int n = list.size();
        
        // 겹치지 않은 경우 
        if(list.size() == 0) {
            return "-1";
        }
        
        // 0만 존재 
        if(allZero) {
            return "0";
        }
             
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<n; i++) {
            sb.append(list.get(i));
        }
        String answer = sb.toString();
        return answer;
    }
}