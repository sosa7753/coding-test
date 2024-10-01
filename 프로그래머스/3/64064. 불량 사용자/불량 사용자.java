import java.util.*;
class Solution {
    String[] user;
    String[] ban;
    boolean[] visited;
    Set<String> set = new HashSet<>();
    Set<String> result = new HashSet<>();       
    public int solution(String[] user_id, String[] banned_id) {
        user = user_id;
        ban = banned_id;
        visited = new boolean[user_id.length];
        
        DFS(0);
        return result.size();
    }
    
    public void DFS(int cnt) {
        if(cnt == ban.length) {
            if(set.size() == ban.length) {
                StringBuilder sb = new StringBuilder();
                for(int i=1; i<visited.length; i++) {
                    if(visited[i]) {
                        sb.append(i);
                    }
                }    
                result.add(sb.toString());
            }
            return;
        }
        
        String check = ban[cnt];        
        for(int i=0; i<user.length; i++) {
            if(visited[i]) { // 이미 사용 
                continue;
            }
            
            if(check.length() != user[i].length()) { // 길이가 다름 
                continue;
            }
            
            // 될 때 넣기 
            if(isPoor(check, user[i])) {
                set.add(user[i]);  
                visited[i] = true;
                DFS(cnt+1);
                visited[i] = false;
                set.remove(user[i]);
            }
        }
    }
    
    public boolean isPoor(String ban, String me) {
        for(int i=0; i<ban.length(); i++) {
            if(ban.charAt(i) == '*') {
                continue;
            }
            
            if(ban.charAt(i) != me.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}