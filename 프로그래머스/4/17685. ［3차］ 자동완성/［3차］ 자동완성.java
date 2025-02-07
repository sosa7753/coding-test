class Solution {
    Node root;
    public int solution(String[] words) {
        int answer = 0;        
        root = new Node('0', 0);
        
        for(int i=0; i<words.length; i++) {
            addTrie(words[i]);
        }
        
        for(int i=0; i<words.length; i++) {
            answer += find(words[i]);
        }        
        
        return answer;
    }
    
    public void addTrie(String s) {
        Node node = root;
        for(int i=0; i<s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            
            if(node.next[idx] == null) { // 처음 방문한 문자
                node.next[idx] = new Node(s.charAt(i), 0);
            }
            
            node.next[idx].cnt++;
            node = node.next[idx];
        }
    }
    
    public int find(String s) {
        Node node = root;
        int dep = 1;
        
        for(int i=0; i<s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            
            if(node.next[idx].cnt >=2) { // 검색이 2개이상이다.
                dep++;
                node = node.next[idx];
            }else { // 1개다.
                return dep;
            }
        }
        return dep-1;
    }
}

class Node {
    char now;
    int cnt;
    Node[] next;
    Node(char now, int cnt) {
        this.now = now;
        this.cnt = cnt;
        next = new Node[26]; // a~z
    }
}