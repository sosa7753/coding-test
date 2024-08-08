import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<skill.length(); i++) {
            map.put(skill.charAt(i), i);
        }
        
        
        for(int i=0; i<skill_trees.length; i++) {
            int idx = 0;
            for(int j=0; j<skill_trees[i].length(); j++) {
                char c = skill_trees[i].charAt(j);
                if(map.containsKey(c)) {
                    if(idx == map.get(c)) {
                        idx++;
                    }else {
                        break;
                    }
                }
                
                if(idx == skill.length() || j == skill_trees[i].length()-1) {
                    answer++;
                    break;
                }
            }           
        }
        return answer;
        
    }
}