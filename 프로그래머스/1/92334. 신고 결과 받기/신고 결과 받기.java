import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Set<String> set = new HashSet<>();
        Map<String, Integer> stop = new HashMap<>();
        Map<String, List<String>> who = new HashMap<>();
        
        for(String re : report) {
            if(set.contains(re)) {
                continue;
            }
            String[] str = re.split(" ");
            stop.compute(str[1], (key, value) -> value == null ? 1 : value + 1);
            who.computeIfAbsent(str[1], key -> new ArrayList<>()).add(str[0]);
            set.add(re);
        }
        
        Map<String, Integer> email = new HashMap<>();
        for(String id : id_list) {
            email.put(id, 0);
        }
        
        for(Map.Entry<String, Integer> entry : stop.entrySet()) {
            if(entry.getValue() >= k) {
                List<String> users = who.get(entry.getKey());
                for(String user : users) {
                    email.put(user, email.get(user) + 1);
                }
            }
        }
        
        int[] answer = new int[id_list.length];
        for(int i=0; i<id_list.length; i++) {
            answer[i] = email.get(id_list[i]);
        }
        return answer;
    }
}