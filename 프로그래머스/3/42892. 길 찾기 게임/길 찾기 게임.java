import java.util.*;
class Solution {
    int[][] result;
    int idx = 0;
    public int[][] solution(int[][] nodeinfo) {      
        Node[] nodes = new Node[nodeinfo.length];
        
        for(int i=0; i<nodeinfo.length; i++) {
            nodes[i] = new Node(i+1, nodeinfo[i][0], nodeinfo[i][1], null, null);     
        }
        
        Arrays.sort(nodes, new Comparator<Node>(){
            public int compare(Node a, Node b) {
                if(a.y == b.y) { // y값이 같다면 x가 작은 순 
                    return a.x - b.x;
                }else {
                    return b.y - a.y; 
                }
            }
        });
        
        Node parent = nodes[0];
        
        for(int i=1; i<nodes.length; i++) {
            makeTree(parent, nodes[i]);
        }
        
        result = new int[2][nodeinfo.length];
        
        preOrder(parent);
        idx = 0;
        postOrder(parent);
                
        return result;
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
    
    public void preOrder(Node head) {
        if(head != null) {
            result[0][idx++] = head.value;
            preOrder(head.left);
            preOrder(head.right);
        }
    }
    
    public void postOrder(Node head) {
        if(head != null) {
            postOrder(head.left);
            postOrder(head.right);
            result[1][idx++] = head.value;
        }
    }
 }

class Node {
    int value;
    int x;
    int y;
    Node left;
    Node right;
    Node(int value, int x, int y, Node left, Node right) {
        this.value = value;
        this.x = x;
        this.y = y;
        this.left = left;
        this.right = right;
    }
}