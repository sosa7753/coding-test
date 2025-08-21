import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize ==0) {
            return cities.length * 5;
        }
        
        int answer = 0;        
        List<String> list = new ArrayList<>();
        for(String city : cities) {
            String str = city.toLowerCase();
            if(list.contains(str)) {
                answer++;
                list.remove(str);
                list.add(str);
            }else {
                if(list.size() == cacheSize) {
                    list.remove(0);
                }    
                list.add(str);
                answer += 5;
            }
        }
        return answer;
    }
}