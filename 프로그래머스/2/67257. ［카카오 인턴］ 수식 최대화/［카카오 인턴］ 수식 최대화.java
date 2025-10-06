import java.util.*;
class Solution {
    char[] c = {'+', '-', '*'};
    long answer = 0;
    public long solution(String expression) {
        List<Long> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        tokenize(expression, nums, ops);

        boolean[] used = new boolean[3];
        int[] order = new int[3];
        dfs(nums, ops, order, used, 0);

        return answer;
    }

    public void tokenize(String expr, List<Long> nums, List<Character> ops) {
        int n = expr.length();
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && Character.isDigit(expr.charAt(j))) j++;
            nums.add(Long.parseLong(expr.substring(i, j)));
            if (j < n) ops.add(expr.charAt(j));
            i = j + 1;
        }
    }

    // 연산자 우선순위 순열
    public void dfs(List<Long> nums, List<Character> ops, int[] order, boolean[] used, int depth) {
        if (depth == 3) {
            long val = evaluate(nums, ops, order);
            answer = Math.max(answer, Math.abs(val));
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (used[i]) continue;
            used[i] = true;
            order[depth] = i;
            dfs(nums, ops, order, used, depth + 1);
            used[i] = false;
        }
    }

    // 주어진 우선순위로 평가 (리스트를 제자리 축소)
    private long evaluate(List<Long> baseNums, List<Character> baseOps, int[] order) {
        List<Long> nums = new ArrayList<>(baseNums);
        List<Character> ops = new ArrayList<>(baseOps);

        for (int k = 0; k < 3; k++) {
            char op = c[order[k]];
            for (int i = 0; i < ops.size();) {
                if (ops.get(i) == op) {
                    long a = nums.remove(i);
                    long b = nums.remove(i);
                    long r = calc(a, b, op);
                    nums.add(i, r);
                    ops.remove(i);
                } else {
                    i++;
                }
            }
        }
        return nums.get(0);
    }

    private long calc(long a, long b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            default:  return a * b;
        }
    }
}
