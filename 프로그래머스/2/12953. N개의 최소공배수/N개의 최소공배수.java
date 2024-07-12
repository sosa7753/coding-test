class Solution {
    public int solution(int[] arr) {       
        // 각 두 수의 최대 공배수를 구하자.
        int pre = arr[0];
        for(int i=1; i<arr.length; i++) {
            int min = gcd(pre, arr[i]);
            pre *= arr[i]/min;
        }
                
        return pre;
    }
    
    public int gcd(int a, int b) {
        if(b == 0) {
            return a;
        }
        
        return gcd(b, a%b);
    }
}