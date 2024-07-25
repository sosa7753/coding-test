class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];
        
        int len = arr1[0].length;
        for(int i=0; i<answer.length; i++) { // arr1 의 행
            for(int j=0; j<answer[0].length; j++) { // arr2의 열
                for(int k=0; k<len; k++) {
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        
        
        return answer;
    }
}