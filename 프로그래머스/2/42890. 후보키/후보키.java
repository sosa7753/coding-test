import java.util.*;
class Solution {
    List<String> list = new ArrayList<>(); // 튜플만 저장 
    public int solution(String[][] relation) {
        BFS(relation);        
        return list.size();
    }
    
    public void BFS(String[][] relation) {
        Queue<String> queue = new LinkedList<>();
        
        for(int i=0; i<relation[0].length; i++) {
            queue.offer(String.valueOf(i));
        }
        
        while(!queue.isEmpty()) {
            String now = queue.poll();
            
            boolean isTrue = true;
            if(check(relation, now)) { // 튜플 후보 중에 
                for(String l : list) {
                    int count = 0;
                    for(int i=0; i<l.length(); i++) {                     
                        if(now.contains(String.valueOf(l.charAt(i)))) {
                           count++; 
                        }                      
                    }
                    if(count == l.length()) {
                        isTrue = false;
                        break;
                    }
                }
                
                if(isTrue) {
                    list.add(now);
                }
            }
                      
            int start = (now.charAt(now.length()-1) - '0') + 1;
            for(int i=start; i<relation[0].length; i++) {
                queue.offer(now + String.valueOf(i));
            }
        }      
    } 
    
    public boolean check(String[][] relation, String key) {
        Set<String> set = new HashSet<>();
        
        for(int i=0; i<relation.length; i++) {
            String s = "";
            
            for(int j=0; j<key.length(); j++) {
                int idx = key.charAt(j) - '0';
                s += relation[i][idx] + " ";
            }
            set.add(s);
        }
        
        if(set.size() == relation.length) {
            return true;
        } 
        
        return false;
    }
}