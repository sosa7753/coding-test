class Solution {
    int answer = Integer.MAX_VALUE;
    String[] w;
    boolean[] visited;
    public int solution(String begin, String target, String[] words) {   
        w = words;
        visited = new boolean[words.length];
        DFS(begin, target, 0);
        if(answer == Integer.MAX_VALUE) {
            return 0;
        }
        return answer;
    }
    
    // Words를 순회해서 문자가 1개만 다른애로 변환하기. 근데 이전 친구는 pass
    public void DFS(String now, String target, int depth) {
        if(target.equals(now)) {
            answer = Math.min(answer, depth);
            return;
        }
        
        for(int i=0; i<w.length; i++) {
            if(visited[i]) {
                continue;
            }
            
            if(check(w[i], now)) {
                visited[i] = true;
                DFS(w[i], target, depth+1);
                visited[i] = false;
            }                        
        }
    }
    
    public boolean check(String word, String now) {
        int cnt = 0;
        for(int i=0; i<word.length(); i++) {
            if(word.charAt(i) != now.charAt(i)) {
                cnt++;
            }
            if(cnt > 1) {
                return false;
            }
        }
        return true;
    }
}