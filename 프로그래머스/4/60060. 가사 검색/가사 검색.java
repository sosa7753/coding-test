import java.util.*;
class Solution {
    Node root = new Node();
    Node reverseRoot = new Node();
    public int[] solution(String[] words, String[] queries) {
        for(String word : words) {
            insert(word, root);
            insert(reverse(word), reverseRoot);
        }
        
        int[] answer = new int[queries.length];
        for(int i=0; i<queries.length; i++) {
            String str = queries[i];
            int n = str.length();
            
            if(str.charAt(0) == '?') {
               if(str.charAt(n-1) == '?') {
                  answer[i] = root.len.getOrDefault(n, 0);
               }else {
                  answer[i] = search(reverse(str), reverseRoot);
               }
            }else {
                  answer[i] = search(str, root);
            }        
        }
              
        return answer;
    }
    
    public int search(String str, Node root) {
        Node cur = root;
            
        char[] c = str.toCharArray();
        for(int i=0; i<c.length; i++) {
            if(c[i] != '?') {
                cur = cur.map.get(c[i]);
                if(cur == null) {
                    return 0;
                }
            }else {
                return cur.len.getOrDefault(str.length() - i, 0);
            }
        }
        return 0;
    }
    
    public void insert(String str, Node root) {
        Node cur = root;
        cur.len.put(str.length(), cur.len.getOrDefault(str.length(), 0) + 1);
        
        char[] c = str.toCharArray();
        for(int i=0; i<c.length; i++) {
            cur = cur.map.computeIfAbsent(c[i], key -> new Node());
            cur.len.put(str.length() - i - 1, cur.len.getOrDefault(str.length() - i - 1, 0) + 1);
        }
    }
    
    public String reverse(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }
}

class Node {
    Map<Character, Node> map = new HashMap<>();
    Map<Integer, Integer> len = new HashMap<>();
}