import java.util.*;
class Solution {
    List<List<Integer>> list = new ArrayList<>();
    Set<Integer> set = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        int n = user_id.length;
        
        // 각 ban별 할 수 있는 목록 List에 담기
        // DFS를 벤 배열 길이만큼 돌려서 하나씩 뽑기
        // Set에 비트마스크로 저장 
        // 사이즈 출력
        for(String ban : banned_id) {
            List<Integer> l = new ArrayList<>();
            for(int i=0; i<n; i++) {
                if(match(ban, user_id[i])) {
                    l.add(i);
                }
            }
            list.add(l);
        }
        
        DFS(0, 0);
        return set.size();
    }
    
    public void DFS(int idx, int mask) {
        if(idx == list.size()) {
            set.add(mask);
            return;
        }
        
        for(int v : list.get(idx)) {
            if((mask & (1 << v)) != 0) continue; // 실제 값이 치환되기 때문에 !=0으로 검증
            DFS(idx+1, mask | (1 << v));
        }
    }
    
    public boolean match(String ban, String user) {
        if(ban.length() != user.length()) return false;
        
        for(int i=0; i<ban.length(); i++) {
            if(ban.charAt(i) == '*') continue;
            
            if(ban.charAt(i) != user.charAt(i)) return false;
        }
        return true;
    }
}