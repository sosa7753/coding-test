class Solution {
    public int solution(int[] cookie) {
        int n = cookie.length;
        int answer = 0;

        // mid: 왼쪽 덩어리의 끝, 오른쪽 덩어리의 시작은 mid+1
        for (int mid = 0; mid < n - 1; mid++) {
            int l = mid;
            int r = mid + 1;
            int left = cookie[l];
            int right = cookie[r];

            while (true) {
                if (left == right) {
                    answer = Math.max(answer, left);
                }
                // 더 작은 쪽을 확장
                if (left <= right) {
                    if (l == 0) break;
                    l--;
                    left += cookie[l];
                } else { // right < left
                    if (r == n - 1) break;
                    r++;
                    right += cookie[r];
                }
            }
        }
        return answer;
    }
}
