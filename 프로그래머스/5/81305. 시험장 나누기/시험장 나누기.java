class Solution {    
    int size = 0;
    int root = -1;
    public int solution(int k, int[] num, int[][] links) {
        int low = 0;
        int high = 0;
        
        for(int i=0; i<num.length; i++) {
            low = Math.max(low, num[i]);
            high += num[i];
        }
        
        root = find(links, num);
        
        while(low <= high) {
            int mid = high - (high - low)/2;
            
            if(check(num, links, mid) <= k) { // 가능하면 더 작은 값 찾기
                high = mid -1;
            }else {
                low = mid + 1;
            }
        }
        return low;
    }
    
    public int find(int[][] links, int[] num) { // 루트 찾기 
        boolean[] visited = new boolean[num.length];
        
        for(int i=0; i<links.length; i++) {
            if(links[i][0] != -1) {
                visited[links[i][0]] = true;
            }
            
            if(links[i][1] != -1) {
                visited[links[i][1]] = true;
            }
        }
        
        for(int i=0; i<visited.length; i++) {
            if(!visited[i]) {
                return i;
            }
        }
        return -1;
    }
    
    public int check(int[] num, int[][] links, int max) {
        size = 0;
        DFS(max, num, links, root);
        return size+1;      
    }
    
    public int DFS(int max, int[] num, int[][] links, int node) {
        int left = 0;
        int right = 0;
        
        // 왼쪽 자식이 있음. 
        if(links[node][0] != -1) {
            left = DFS(max, num, links, links[node][0]);
        }
        
        // 오른쪽 자식이 있음. 
        if(links[node][1] != -1) {
            right = DFS(max, num, links, links[node][1]);
        }
        
        // 자기 자신 + left + right <= max면 자르지 말자. 
        if(num[node] + left + right <= max) {
            return num[node] + left + right;
        }
        
        // 3개를 더하면 초과한다 -> 큰 자식을 잘라야한다.
        if(num[node] + Math.min(left, right) <= max) {
            size += 1;
            return num[node] + Math.min(left, right);
        }
        
        // 2개를 잘라야함. 자기 자신 반환 
        size +=2;
        return num[node];
    }
}