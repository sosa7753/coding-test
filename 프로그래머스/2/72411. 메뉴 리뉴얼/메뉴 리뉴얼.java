import java.util.*;
class Solution {
    List<String> list;
    Map<String, Integer> map;
    int[] max;
    public String[] solution(String[] orders, int[] course) {
      
        list = new ArrayList<>();
        map = new HashMap<>();
        
        max = new int[course.length];
        
        int number = 0;
        // 최대 알파벳 찾기 
        for(int i=0; i<orders.length; i++) {
            for(int j=0; j<orders[i].length(); j++) {
                int com = orders[i].charAt(j) - 'A';
                if(com > number) {
                    number = com;
                }
            }
        }
        
        for(int i=0; i<course.length; i++) {
            boolean[] visited = new boolean[number+1];
            String[] alpa = new String[course[i]];
            DFS(alpa, orders, visited, 0, course[i], 0, i);
        }
        
        // Map에서 가져와서 계산하기
        for(String s : map.keySet()) {
            for(int i=0; i<course.length; i++) {
                if(course[i] == s.length() && max[i] == map.get(s)) {
                    list.add(s);                  
                }
            }
        }
        
        Collections.sort(list);
        int len = list.size();
        String[] answer = new String[len];
        int idx = 0;
              
        for(String str : list) {
            answer[idx++] = str;
        }       
        return answer;
    }
    
    public void DFS(String[] alpa, String[] orders, boolean[] visited, 
                    int now, int max, int cnt, int idx) {
        if(max == cnt) {
            String tmp = make(alpa);
            cal(orders, tmp, idx);        
            return;
        }
        
        // A - Z 
        for(int i=0; i<visited.length; i++) {            
            if(i < now) {
                continue;
            }
            
            if(visited[i]) {
                continue;
            }
            
            char c = (char)('A' + i);
            
            alpa[cnt] = String.valueOf(c);
            visited[i] = true;
            DFS(alpa, orders, visited, i, max, cnt+1, idx);
            visited[i] = false;
        }
    }
    
    public String make(String[] alpa) {
        StringBuilder sb = new StringBuilder();
        
        for(String al : alpa) {
            sb.append(al);
        }
        return sb.toString(); 
    }
    
    public void cal(String[] orders, String str, int idx) {
        
        int result = 0;
        for(int i=0; i<orders.length; i++) {      
            
            boolean isfalse = true;
            for(int j=0; j<str.length(); j++) {
                // 주문 메뉴에 포함이 안된 메뉴인 경우 
                if(!orders[i].contains(str.substring(j,j+1))) {
                    isfalse = false;
                    break;
                }    
            }
            
            if(isfalse) {
                result++;
            }
        }
        
        if(result >=2) {
            map.put(str, result);
            if(result > max[idx]) {
                max[idx] = result;
            }
        }    
    }
}