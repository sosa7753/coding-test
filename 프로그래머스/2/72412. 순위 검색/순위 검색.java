import java.util.*;
class Solution {
    Map<String, List<Integer>> map = new HashMap<>();
    public int[] solution(String[] info, String[] query) {
        
        for(String s : info) {
            DFS("", s.split(" "), 0);
        }
        
        for(List<Integer> l : map.values()) {
            Collections.sort(l);
        }
        
        int[] answer = new int[query.length];
        for(int i=0; i<query.length; i++) {
            String[] str = query[i].replace(" and ", "").split(" ");
            int score = Integer.parseInt(str[1]);
            answer[i] = binarysearch(str[0], score);
        }   
        return answer;
    }
    
    public void DFS(String now, String[] user, int cnt) {
        if(cnt == 4) {
            if(!map.containsKey(now)) {
                List<Integer> list = new ArrayList<>();
                list.add(Integer.parseInt(user[4]));
                map.put(now, list);
            }else {
                map.get(now).add(Integer.parseInt(user[4]));
            }
            return;               
        }    
        
        DFS(now + "-", user, cnt+1);
        DFS(now + user[cnt], user, cnt+1);
    }  
    
    public int binarysearch(String query, int score) {
        if(!map.containsKey(query)) {
            return 0;
        }
                 
        List<Integer> list = map.get(query);
        int last = list.size()-1;
        if(list.get(last) < score) {
            return 0;
        }
        
        int l = 0;
        int r = last;
        int result = 0;
        while(l<=r) {
            int mid = (l+r)/2;
            if(list.get(mid) < score) {
                l = mid+1;
            }else {
                r = mid-1;
                result = mid;
            }
        }
        return list.size() - result;
    }
}  