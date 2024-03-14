import java.util.*;
class Solution {
    String[] value = {"R", "T", "C", "F", "J", "M", "A", "N"};
    public String solution(String[] survey, int[] choices) {
        
        int n = survey.length;
        
        Map<String, Integer> map = new HashMap<>();
        
        // 초기 값 0 저장 
        for(String str : value) {
            map.put(str, 0);
        }
        // 질문
        for(int i=0; i<n; i++) {
            String[] str = survey[i].split("");
            
            if(choices[i] < 4) {
                // 비동의 -> (4 - 값)이 점수 
                map.put(str[0], map.get(str[0]) + 4 - choices[i]); 
            }else if(choices[i] > 4) {
                // 동의 -> (값 - 4)이 점수 
                map.put(str[1], map.get(str[1]) + choices[i] - 4);
            }else {
                continue;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<value.length; i = i+2) {
            if(map.get(value[i]) >= map.get(value[i+1])) {
                sb.append(value[i]);
            }else {
                sb.append(value[i+1]);
            }
        }
        
        String answer = sb.toString();
        return answer;
    }
}