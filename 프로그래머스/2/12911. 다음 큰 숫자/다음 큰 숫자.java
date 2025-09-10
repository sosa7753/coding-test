class Solution {
    public int solution(int n) {
        long x = n;
        long c = x & -x;
        long r = x + c;
        long ones = ((x ^ r) >> 2) / c;
        return (int)(r | ones);

    }
}