import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(int i=0; i<skill_trees.length; i++) {
            if(save(skill, skill_trees[i])) {
                answer++;
            }
        }
        return answer;
    }
    
    public boolean save(String skill, String myTrees) {
        StringBuilder sb = new StringBuilder();
        
        Queue<String> queue = new LinkedList<>();
        
        String[] str = myTrees.split("");
        for(String s : str) {
            queue.offer(s);
        }
        
        while(!queue.isEmpty()) {
            String sk = queue.poll();
            
            if(!skill.contains(sk)) {
                continue;
            }
            sb.append(sk);
        }
        
        for(int i=0; i<sb.toString().length(); i++) {
            if(skill.charAt(i) != sb.toString().charAt(i)) {
                return false;
            }
        }
        return true;
    }
}