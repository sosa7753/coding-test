import java.util.*;
class Solution {
    public int[] solution(String s) {
        PriorityQueue<Set<Integer>> pq = new PriorityQueue<>(
            (x,y) -> (x.size() - y.size()));
        
        int max = 0;
        
        String s1 = sideCut(s);
        
        String s2 = s1.replace("{", " ").replace("}", " ");
        
        String s3 = sideCut(s2);
        
        String[] str = s3.split(" , ");
        
        
        for(int i=0; i<str.length; i++) {
            String[] num = str[i].split(",");
            Set<Integer> set = new HashSet<>();
            
            int cnt = 0;
             
            for(int j=0; j<num.length; j++) {
                set.add(Integer.parseInt(num[j]));
                cnt++;                
            }
            pq.add(set);
            max = Math.max(max, cnt);        
        }
        
        int[] answer = new int[max];
        int idx = 0;
        
        boolean[] visited = new boolean[100001];
        
        while(!pq.isEmpty()) {
            Set<Integer> set = pq.poll();
            
            for(int number : set) {
                if(visited[number]) {
                    continue;
                }
                visited[number] = true;
                answer[idx++] = number;
            }
        }
        
        return answer;
    }
    
    public String sideCut(String s) {
        
        return s.substring(1, s.length()-1);
    }
}
