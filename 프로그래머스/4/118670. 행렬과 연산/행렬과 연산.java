import java.util.*;
class Solution {
    Deque<Integer> left = new LinkedList<>();
    Deque<Integer> right = new LinkedList<>();
    Deque<Deque<Integer>> mid = new LinkedList<>();
    int row;
    int col;
    public int[][] solution(int[][] rc, String[] operations) {
        init(rc);
       
        for(int i=0; i<operations.length; i++) {
            if("ShiftRow".equals(operations[i])) {
                shiftRow(col);
            }else {
                rotate(col);
            }
        }
        
        int[][] answer = new int[row][col];
        for(int i=0; i<row; i++) {
            answer[i][0] = left.poll(); // 왼쪽
            answer[i][col-1] = right.poll();
            
            if(col > 2) {
                Deque<Integer> d = mid.poll();
                for(int j=1; j<=col-2; j++) {
                    answer[i][j] = d.poll();
                }
            }
        }

        return answer;
    }
    
    public void init(int[][] rc) {
        row = rc.length;
        col = rc[0].length;
        
        for(int i=0; i<row; i++) { // 왼쪽, 오른쪽
            left.add(rc[i][0]);
            right.add(rc[i][col-1]);
        }
        
        if(col > 2) {
            for(int i=0; i<row; i++) {
                Deque<Integer> d = new LinkedList<>();
                for(int j=1; j<=col-2; j++) {
                    d.add(rc[i][j]);
                }
                mid.add(d);
            }
        }
    }
    
    public void shiftRow(int col) {
        left.addFirst(left.pollLast());
        right.addFirst(right.pollLast());
        
        if(col <= 2) {
            return;
        }
        mid.addFirst(mid.pollLast());
    }
    
    public void rotate(int col) {
        if(col > 2) {
            mid.getFirst().addFirst(left.poll());
            right.addFirst(mid.getFirst().pollLast());
            mid.getLast().add(right.pollLast());
            left.add(mid.getLast().poll());
            return;
        }
        
        right.addFirst(left.poll());
        left.add(right.pollLast());
    }
}