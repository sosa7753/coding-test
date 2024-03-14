import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {     
        // 사람별 신고당한 횟수 Map
        Map<String, Integer> reportMap = new HashMap<>();
        
        // 해당 사람을 신고한 사람들 Map
        Map<String, List<String>> whoMap = new HashMap<>();
        
        // 사람 별 신고 처리 횟수 Map
        Map<String, Integer> resultMap = new HashMap<>();
        
        // 신고 처리하기
        for(int i=0; i<report.length; i++) {
            String[] str = report[i].split(" ");
            
            // 신고한적 없다면, 당사자 신고 횟수 +1
            if(isReport(reportMap, whoMap, str)) { 
                // 신고자 리스트에 추가 
                List<String> list = whoMap.getOrDefault(str[1], new ArrayList<>());
                list.add(str[0]);
                whoMap.put(str[1], list);
                
                // 신고 횟수 추가 
                reportMap.put(str[1], reportMap.getOrDefault(str[1], 0) + 1);
            }
        }
        
        // 정지 대상 추출
        for(Map.Entry<String, Integer> entry : reportMap.entrySet()) {
            // 정지 대상 아님 
            if(entry.getValue() < k) {
                continue;
            }
            
            // 정지 대상 
            List<String> members = whoMap.get(entry.getKey());
        
            for(String member : members) {
                resultMap.put(member, resultMap.getOrDefault(member, 0) + 1);
            }
        }
        
        int[] answer = new int[id_list.length];
        int idx = 0;
        // 배열에 저장 
        for(int i=0; i<id_list.length; i++) {
            // 신고한 적이 없는 사람 
            if(!resultMap.containsKey(id_list[i])) { 
                answer[idx++] = 0;
                continue;
            }
            
            // 신고한 적이 있는 사람
            answer[idx++] = resultMap.get(id_list[i]);           
        }
     
        return answer;
    }
    
    public boolean isReport(Map<String, Integer> reportMap,
                            Map<String, List<String>> whoMap, String[] str) {
        
        boolean result = false;
        
        if(whoMap.get(str[1]) == null) { // 한 번도 신고당하지 않은 대상인 경우
            return true;
        }
        
        List<String> list = whoMap.get(str[1]);
        
        if(!list.contains(str[0])) { // 당사자를 처음 신고한 사람일 경우 
            return true;
        }
        
        return result;       
    }
}