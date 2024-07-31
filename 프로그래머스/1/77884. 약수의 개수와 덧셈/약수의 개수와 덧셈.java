class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        
        int[] arr = new int[right+1];
        
        for(int i=1; i<=right; i++) {
            for(int j=i; j<=right; j = j + i) {
                arr[j]++;
            }
        }
        
        for(int i=left; i<=right; i++) {
            if(arr[i]%2 == 0) {
                answer += i;
            }else {
                answer -= i;
            }
        }
        
        return answer;
    }
}