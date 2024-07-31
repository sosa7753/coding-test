class Solution {
    public int[] solution(int[] arr) {
        if(arr.length == 1) {
            return new int[] {-1};
        }
        
        int[] answer = new int[arr.length-1];
        
        int min = Integer.MAX_VALUE;
        for(int i=0; i<arr.length; i++) {
            min = Math.min(min, arr[i]);
        }
        
        boolean check = false; // check가 true면 그대로 입력해주자.
        int idx = 0;
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == min) {
                if(!check) {
                    check = true;
                }else {
                    answer[idx++] = arr[i];
                }
            }else {
                answer[idx++] = arr[i];
            }
        }
        return answer;
    }
}