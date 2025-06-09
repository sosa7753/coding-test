import java.util.*;
class Solution {
    List<String> list;
    Map<String, Integer> map;
    int[] max;
    public String[] solution(String[] orders, int[] course) {
     
        list = new ArrayList<>();
        map = new HashMap<>();
        max = new int[course.length];
        
        
        for(int i=0; i<orders.length; i++) { // 각 order 문자열에 대해
            char[] c = orders[i].toCharArray();
            Arrays.sort(c);
            String str = String.copyValueOf(c);
            for(int j=0; j<course.length; j++) { // 각 길이 만큼 
                DFS(0, "", str, course[j], 0, j);              
            }
        }
        
        // Map에서 찾기 
        for(String s : map.keySet()) {
            for(int i=0; i<course.length; i++) {
                if(course[i] == s.length() && max[i] != 1 
                   && map.get(s) == max[i]) {
                    list.add(s);
                    System.out.print(s + " ");
                }
            }
        }
        
        // list 정렬 후 answer에 저장하기 
        Collections.sort(list);
        int size = list.size();
        String[] answer = new String[size];
        int idx = 0;
        for(String s : list) {
            answer[idx++] = s;
        }
        return answer;
    }
    
    // 해당 문자열의 해당 크기만큼 조합을 만들어서 Map에 넣기 
    // 현재 알파벳 인덱스 ,시작 문자열 , 해당 문자열, 조합 길이, 
    // 현재 채우는 인덱스, max배열 idx
    public void DFS(int cur, String start, String str, 
                    int len, int cnt, int idx) {
        
        if(cnt == len) {
            map.put(start, map.getOrDefault(start, 0) + 1);
            max[idx] = Math.max(max[idx], map.get(start));
            return;
        }
        
        for(int i=cur; i<str.length(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(start);
            sb.append(str.substring(i,i+1));
            DFS(i+1, sb.toString(), str, len, cnt+1, idx);
        }      
    }
}