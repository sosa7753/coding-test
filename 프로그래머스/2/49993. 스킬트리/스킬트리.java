import java.util.*;
import java.util.*;
class Solution {
    Map<Character, Integer> map = new HashMap<>();
    public int solution(String skill, String[] skill_trees) {
        init(skill);
        int answer = 0;
        
        for(String s : skill_trees) {
            boolean check = true;
            char[] cs = s.toCharArray();
            int idx = 0;
            for(char c : cs) {
                if(!map.containsKey(c)) {
                    continue;
                }
                int cnt = map.get(c);
                if(idx != cnt) {
                    check = false;
                    break;
                }
                idx++;
            }
            if(check) {
                answer++;
            }
        }
                                     
        return answer;
    }
    
    public void init(String skill) {
        char[] c = skill.toCharArray();
       
        for(int i=0; i<c.length; i++) {
            map.put(c[i], i);
        }
    }
}