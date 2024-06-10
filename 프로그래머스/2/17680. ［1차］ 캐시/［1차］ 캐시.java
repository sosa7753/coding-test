import java.util.*;

// 리스트에 캐시 넣기 
// 캐시에 없으면 +5 , 캐시에 추가
// 있으면 +1  리스트에서 꺼내고 다시 넣기
class Solution {
    public int solution(int cacheSize, String[] cities) {
        
        if(cacheSize == 0) {
            return cities.length * 5;
        }
        
        // 리스트 생성
        List<String> list = new ArrayList<>();
        
        int answer = 0;
        
        for(int i=0; i<cities.length; i++) {
            String str = cities[i].toUpperCase();
            
            // 캐시에 있다면
            if(list.contains(str)) {
                answer += 1;
                list.remove(str);
                list.add(str);
                continue;
            }
            
            // 캐시에 없다면, 
            if(list.size() == cacheSize) { // 캐시가 다 찼다. 
                list.remove(0);
                list.add(str);
                answer += 5;
                continue;
            }
            
            list.add(str);
            answer += 5;            
        }         
        return answer;
    }
}