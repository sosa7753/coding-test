class Solution {
    public int[] solution(int n, long left, long right) {   
        // i행 i열은 i + 1
        
        // 범위만큼 정답 배열 만들기
        int[] answer = new int[(int)(right - left) + 1];
        int idx = 0;
        for(long i=left; i<=right; i++) {
            int row = (int)(i/n); 
            int col = (int)(i%n);
            
            answer[idx++] = Math.max(row, col) + 1;
        }
        return answer;
    }
}