import java.util.*;
class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> list = new ArrayList<>();
        
        for(int i=0; i<=8; i++) {
            list.add(new HashSet<>());
        }
        list.get(1).add(N);
        
        for(int i=2; i<=8; i++) {
            Set<Integer> set = list.get(i);
            
            // j번으로 만들 수 있는 Set 집합으로 i번째 집합을 만들기 
            for(int j=1; j<=i; j++) {
                Set<Integer> set1 = list.get(j);
                Set<Integer> set2 = list.get(i-j);
                
                for(int num1 : set1) {
                    for(int num2 : set2) {
                        set.add(num1 + num2);
                        set.add(num1 - num2);
                        set.add(num1 * num2);
                        if(num2 != 0) {
                            set.add(num1 / num2);
                        } 
                    }
                }
                set.add(Integer.parseInt(String.valueOf(N).repeat(i)));
            }       
        }
        
        boolean check = false;
        for(int i=1; i<9; i++) {
            if(list.get(i).contains(number)) {
                return i;
            }
        }
        
        return -1;
    }
}