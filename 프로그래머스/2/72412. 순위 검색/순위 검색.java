import java.util.*;
class Solution {
    Map<String, List<Integer>> map = new HashMap<>();
    public int[] solution(String[] info, String[] query) {
        for(String in : info) {
            DFS(in.split(" "), "", 0);
        }
        
        for(List<Integer> list : map.values()) {
            Collections.sort(list);
        }
        
        int n = query.length;
        int[] answer = new int[n];
        for(int i=0; i<n; i++) {
            String[] str = query[i].replace(" and ", "").split(" ");
            int value = Integer.parseInt(str[1]);
            answer[i] = binarysearch(value,  str[0]);
        }
        
        return answer;
    }
    
    public void DFS(String[] s, String now, int cnt) {
        if(cnt == 4) {
            map.computeIfAbsent(now, 
                key -> new ArrayList<>()).add(Integer.parseInt(s[4]));
            return;
        }
        
        DFS(s, now+"-", cnt+1);
        DFS(s, now+s[cnt], cnt+1);
    }
    
    public int binarysearch(int val, String str) {
        if(!map.containsKey(str)) {
            return 0;
        }
        
        List<Integer> list = map.get(str);
        int last = list.size()-1;
        if(list.get(last) < val) {
            return 0;
        }
        
        int l = 0;
        int r = last;
        int result = 0;
        while(l<=r) {
            int mid = (l+r)/2;
            if(list.get(mid) < val) {
                l = mid + 1;
            }else {
                r = mid - 1;
                result = mid;
            }
        }
        return list.size() - result;
    }
}