import java.util.*;
class Solution {
    List<Integer> num;
    int h;
    int[] arr;
    int cnt;
    boolean isfalse;
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for(int i=0; i<numbers.length; i++) {
            num = binary(numbers[i]);           
            h = treeH(num.size());
            arr = new int[(int)Math.pow(2, h+1)]; 
            int idx = arr.length-1;
            for(int j=0; j<num.size(); j++) {
                arr[idx--] = num.get(j);
            }
        
            cnt = 0;
            isfalse = true;
            
            tree(0, 1);
            
            if(isfalse) {
                answer[i] = 1;
            }                               
        }
        return answer;
    }
    
    // 트리
    public int tree(int depth, int idx) {
        if(depth == h) {
            int value = arr[arr.length-1-cnt];
            cnt++;
            return value;
        }

        int right = tree(depth+1, 2 * idx + 1);
        int mid = arr[arr.length-1 - cnt];
        cnt++;
        int left = tree(depth+1, 2 * idx);
        
        if(mid == 0 && (right == 1 || left == 1)) {
            isfalse = false;
        }

        return mid;          
    }
    
    public int treeH(int size) {
        int k = 0;
        while(size >= (int)Math.pow(2, k))  {
            k++;
        }   
                                      
        return k-1;
    }
    
    // 이진수 만들기 
    public List<Integer> binary(long number) {        
        List<Integer> list = new ArrayList<>();
        
        long value = number;
        while(value != 1L) {
            list.add((int)value%2);
            value /= 2;
        }
        list.add(1);
        
        return list;
    }  
}