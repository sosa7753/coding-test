import java.util.*;
class Solution {
    Map<String, List<Integer>> map;
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        // 모든 경우의 문자열 조합을 key로 저장
        map = new HashMap<>();
        
        // 모든 문자열과 값 저장
        for(int i=0; i<info.length; i++) {
            DFS("", info[i].split(" "), 0);
        }
        
        // 모든 점수 리스트 정렬 
        for(String key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        
        // 각 쿼리에 대해 사람 수 구하기
        for(int i=0; i<query.length; i++) {
            String[] str = query[i].replace(" and ", "").split(" "); // [0] 쿼리 [1] 점수
            int score = Integer.parseInt(str[1]);
            answer[i] = binarySearch(str[0], score);
        }
           
        return answer;
    }
    
    public int binarySearch(String str, int score) {
        if(map.containsKey(str)) {
            List<Integer> list = map.get(str);
            
            int left = 0;
            int right = list.size()-1;
            
            // 점수를 넘은 사람이 없으면 종료 
            if(list.get(right) < score) {
                return 0;
            }
            
            // 이진 탐색
            while(left < right) {
                int mid = (left + right)/2;
                
                int value = list.get(mid);
                if(value < score) {
                    left = mid + 1;                 
                }else {
                    right = mid;
                }                      
            }
             return list.size() - left;         
        }
        return 0;
    }
    
    
    public void DFS(String str, String[] user, int cnt) {
        if(cnt == 4) {
            if(!map.containsKey(str)) { // 맵에 존재하지 않는 쿼리 
                List<Integer> list = new ArrayList<>();
                list.add(Integer.parseInt(user[4]));
                map.put(str, list);
            }else {
                // 객체는 참조를 저장하기 떄문에 다시 넣어줄 필요가 없다.
                map.get(str).add(Integer.parseInt(user[4])); 
            }
            return;
        }
        
        DFS(str+"-", user, cnt+1);
        DFS(str+user[cnt], user, cnt+1);
    }
}