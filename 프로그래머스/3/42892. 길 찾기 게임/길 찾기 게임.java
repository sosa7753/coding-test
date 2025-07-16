import java.util.*;
class Solution {
    Node root;
    PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> {
        if(a.y == b.y) {
            return a.x - b.x;
        }else {
            return b.y - a.y;
        }
    });
    int cnt = 0;
    int[][] answer;
    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;
               
        for(int i=0; i<nodeinfo.length; i++) {
            pq.offer(new Node(i+1, nodeinfo[i][0], nodeinfo[i][1],
                             null, null));
        }
        
        root = pq.poll();     
        
        while(!pq.isEmpty()) {
           makeTree(root, pq.poll());            
        }
        
        answer = new int[2][n];    
        preorder(root);
        cnt = 0;
        postorder(root);
        
        return answer;
    }
    
    public void makeTree(Node parent, Node child) {
        if(parent.x > child.x) {
            if(parent.left == null) {
                parent.left = child;
            }else {
                makeTree(parent.left, child);
            }
        }else {
            if(parent.right == null) {
                parent.right = child;
            }else {
                makeTree(parent.right, child);
            }
        }
    }
    
    public void preorder(Node node) {
        if(node != null) {
            answer[0][cnt++] = node.idx;
            preorder(node.left);
            preorder(node.right);
        }
    }
    
    public void postorder(Node node) {
        if(node != null) {
            postorder(node.left);
            postorder(node.right);
            answer[1][cnt++] = node.idx;
        }
    }
}

class Node {
    int idx;
    int x;
    int y;
    Node left;
    Node right;
    Node(int idx, int x, int y, Node left, Node right) {
        this.idx = idx;
        this.x = x;
        this.y = y;
        this.left = left;
        this.right = right;
    }
}