import java.util.*;
class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[][] time = new int[m][n];
        for(int[] row : time) Arrays.fill(row, Integer.MAX_VALUE);
        for(int i=0; i<drops.length; i++) {
            time[drops[i][0]][drops[i][1]] = i+1;
        }
        
        // 행 기준 슬라이딩 윈도우 최솟값 기입
        int[][] rowMin = new int[m][n-w+1];
        for(int r=0; r<m; r++) {
            Deque<Integer> dq = new ArrayDeque<>();
            for(int c=0; c<n; c++) {
                while(!dq.isEmpty() && dq.peekFirst() < c-w+1) dq.pollFirst();
                while(!dq.isEmpty() && time[r][dq.peekLast()] >= time[r][c]) dq.pollLast();
                dq.addLast(c);
                if(c >= w-1) rowMin[r][c-w+1] = time[r][dq.peekFirst()];
            }
        }
        
        // 열 기준 슬라이딩 윈도우 최솟값
        int bestTime = -1;
        int bestR = 0, bestC = 0;
        int cols = n-w+1;
        
        for(int c=0; c<cols; c++) {
            Deque<Integer> dq = new ArrayDeque<>();
            for(int r=0; r<m; r++) {
                while(!dq.isEmpty() && dq.peekFirst() < r-h+1) dq.pollFirst();
                while(!dq.isEmpty() && rowMin[dq.peekLast()][c] >= rowMin[r][c]) dq.pollLast();
                dq.addLast(r);
                if(r >= h-1) {
                    int minVal = rowMin[dq.peekFirst()][c];
                    int topR = r - h + 1;
                    if(minVal > bestTime || (minVal == bestTime && (topR < bestR || (topR == bestR && c < bestC)))) {
                        bestTime = minVal;
                        bestR = topR;
                        bestC = c;
                    }
                }
            }
        }
        
        return new int[]{bestR, bestC};
    }
}