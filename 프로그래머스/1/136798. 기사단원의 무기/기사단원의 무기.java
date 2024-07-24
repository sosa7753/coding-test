class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        
        int[] result = new int[number+1];
        
        for(int i=1; i<=number; i++) {
            result[i]++;
            for(int j=i+i; j<=number; j = j+i) {
                result[j]++;
            }
        }
        
        for(int i=1; i<=number; i++) {
            if(result[i] > limit) {
                answer += power;
            }else {
                answer += result[i];
            }
        }
        
        return answer;
    }
}