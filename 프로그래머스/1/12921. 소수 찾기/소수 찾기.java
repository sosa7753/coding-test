class Solution {
    public int solution(int n) {
        int answer = 0;
        
        boolean[] arr = new boolean[n+1];
        for(int i=2; i<arr.length; i++) {
            if(arr[i]) {
                continue;
            }
            
            for(int j=i+i; j<arr.length; j = j+i) {
                arr[j] = true;
            }
        }
        
        for(int i=2; i<arr.length; i++) {
            if(!arr[i]) {
                answer++;
            }
        }
        return answer;
    }
}