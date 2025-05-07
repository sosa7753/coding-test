import java.util.*;
class Solution {
    List<Integer> A = new ArrayList<>();
    List<Integer> B = new ArrayList<>();
    int max = 0;
    int[] number;
    public int[] solution(int[][] dice) {
        int[] front = new int[dice.length/2];
        number = new int[dice.length/2];
        DFS(front, dice, 0, 0);
                     
        return number;
    }
    
    public void DFS(int[] front, int[][] dice, int cnt, int idx) {
        if(cnt == front.length) {
            cal(front, dice);            
            return;
        }
        
        for(int i=idx; i<dice.length; i++) {
            front[cnt] = i;
            DFS(front, dice, cnt+1, i+1);
        }
    }
    
    public void setting(int[] front, int[] back, int[][] dice, int cnt, int sumA, int sumB) {
        if(cnt == dice.length / 2) {
            A.add(sumA);
            B.add(sumB);
            return;
        }
        
        for(int i=0; i<6; i++) {
            int nextA = sumA + dice[front[cnt]][i];
            int nextB = sumB + dice[back[cnt]][i];
            setting(front, back, dice, cnt+1, nextA, nextB);    
        }
    }
    
    public int check(int value) {
        int L = 0;
        int R = B.size()-1;
        
        while(L <= R) {
            int mid = (L + R)/2;
            if(B.get(mid) < value) {
                L = mid + 1;
            }else {
                R = mid - 1;
            }
        }
        return R;
    }
    
    public void cal(int[] front, int[][] dice) {
        int[] back = new int[front.length];
        int b = 0;
        int a = 0;
        
        for(int i=0; i<dice.length; i++) {
            if(a != front.length && front[a] == i) {
                a++;
                continue;
            }
            back[b++] = i;         
        }

        A = new ArrayList<>();
        B = new ArrayList<>();
        setting(front, back, dice, 0, 0, 0);
            
        Collections.sort(B);
            
        int result = 0;
        for(int i=0; i<A.size(); i++) {
            result += check(A.get(i));
        }
            
        if(max < result) {
            max = result;
            for(int i=0; i<front.length; i++) {
                number[i] = front[i] + 1;
            }
        }
    }
}