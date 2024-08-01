class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        int n = sequence.length;
        int p1 = 0;
        int p2 = 0;
        
        int gap = Integer.MAX_VALUE;
        
        int sum = sequence[0];
        while(p1 <= p2 && p2 < n) {
            if(sum < k) {
                p2++;
                if(p2 >= n) {
                    break;
                }
                sum += sequence[p2];
            }else if(sum == k) {
                if(gap > p2 - p1) {
                    gap = p2 - p1;
                    answer[0] = p1;
                    answer[1] = p2;
                }
                sum -= sequence[p1];
                p1++;
            }else {
                sum -= sequence[p1];
                p1++;
            }
        }
        
        return answer;
    }
}