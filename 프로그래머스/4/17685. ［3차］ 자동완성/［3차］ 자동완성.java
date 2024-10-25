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
    
    public int find(String str) {
        Node cur = root;
        int depth = 0;
        for(int i=0; i<str.length(); i++) {
            int idx = str.charAt(i) - 'a';
            Node next = cur.node[idx];
            if(next.cnt >=2) {
                cur = next;
                depth++;
                continue;
            }
            return depth+1;
        }
        return depth;
    }
    
    public void addTrie(String s) {
        Node cur = root;
        for(int i=0; i<s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            
            if(cur.node[idx] == null) { // 처음 보는 문자 
                cur.node[idx] = new Node(s.charAt(i), 0);
            }
            
            cur.node[idx].cnt++;     
            cur = cur.node[idx];
        }
    }
}

class Node {
    char now;
    int cnt;
    Node[] node;
    Node(char now, int cnt) {
        this.now = now;
        this.cnt = cnt;
        this.node = new Node[26]; // a~z
    }
}