import java.util.*;
class Solution {
    public int solution(String[][] relation) {
        int r = relation.length;
        int c = relation[0].length;
        
        List<Integer> list = new ArrayList<>();
        
        List<Integer> subsets = new ArrayList<>();
        for(int i=1; i < (1<<c); i++) { // 111111.. 길이가 c만큼 
            subsets.add(i); // 경우의수 다넣기 (부분 집합)
            subsets.sort((x,y) -> 
                         ((int)Integer.bitCount(x) - (int)Integer.bitCount(y))); // 비트 1의 개수 오름차순 정렬
        }
        
        for(int mask : subsets) {
            boolean isMin = false;
            for(int key : list) {
                if((mask & key) == key) { // 리스트 후보키 값이 mask에 포함된다면 false
                    isMin = true;
                    break;
                }
            }
            if(isMin) continue;
            
            if(check(mask, relation)) {
                list.add(mask);
            }
        }
        
       return list.size();
    }
    
    public boolean check(int mask, String[][] relation) {
        Set<String> set = new HashSet<>();
        for(int i=0; i<relation.length; i++) {
            StringBuilder sb = new StringBuilder();
            for(int col = 0; col < relation[0].length; col++) {
                if((mask & (1 << col)) != 0) {
                    sb.append(relation[i][col]).append(",");
                }
            }
            set.add(sb.toString());
        }
        if(set.size() == relation.length) {
            return true;
        }
        return false;
    }
}