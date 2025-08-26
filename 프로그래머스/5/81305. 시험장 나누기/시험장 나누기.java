class Solution {
    int size = 0;
    int root = -1;
    int n,k;
    int[] num;
    int[][] links;
    public int solution(int k, int[] num, int[][] links) {
        this.k = k;
        this.num = num;
        this.links = links; 
        
        // root가 누군지 찾기 -> 누군가의 자식이면 visited = true로 찾기
        // 명수를 이진탐색으로 돌려서 k개의 그룹으로 나눠지는지 체크
        n = num.length;
        boolean[] visited = new boolean[n];
        for(int[] link : links) {
            if(link[0] != -1) {
                visited[link[0]] = true;
            }
            
            if(link[1] != -1) {
                visited[link[1]] = true;
            }
        }
        
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                root = i;
                break;
            }
        }
        
        int l = 0;
        int r = 0;
        for(int number : num) {
            l = Math.max(l, number);
            r += number;
        }
        
        int answer = 0;
        while(l<=r) {
            int mid = (l+r)/2;
            if(check(mid) <= k) {
                answer = mid;
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        
        return answer;
    }
    
    public int check(int val) {
        size = 0;
        DFS(root, val);
        return size + 1;
    }
    
    public int DFS(int node, int val) {
        int left = 0;
        int right = 0;
        if(links[node][0] != -1)  {
            left = DFS(links[node][0], val);
        }
        
        if(links[node][1] != -1) {
            right = DFS(links[node][1], val);
        }
               
        if(num[node] + left + right <= val) {
            return num[node] + left + right;
        }
        
        if(num[node] + Math.min(left, right) <= val) {
            size += 1;
            return num[node] + Math.min(left, right);
        }
        
        size +=2;
        return num[node];
    }
}