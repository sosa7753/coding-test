import java.util.*;
import java.io.*;
class Main {
  static class Node {
    Map<Character, Node> child = new HashMap<>();
    Node fail = null;
    List<String> outputs = new ArrayList<>();
  }
  static Node root;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    root = new Node();
    int N = Integer.parseInt(br.readLine());
    for(int i=0; i<N; i++) {
      insert(br.readLine());
    }
      
    makeFailLink();

    StringBuilder sb = new StringBuilder();
    int Q = Integer.parseInt(br.readLine());
    for(int i=0; i<Q; i++) {
      if(search(br.readLine())) {
        sb.append("YES").append("\n");
      }else {
        sb.append("NO").append("\n");
      }
    }

    System.out.print(sb.toString());
  }

  public static boolean search(String str) {
    Node cur = root;

    for(char c : str.toCharArray()) {
      while(cur != root && !cur.child.containsKey(c)) {
        cur = cur.fail; // 일치하지 않으면 실패링크로 가기
      }

      if(cur.child.containsKey(c)) {
        cur = cur.child.get(c);
      }

      if(!cur.outputs.isEmpty()) {
        return true;
      }
    }
    return false;
  }

  public static void insert(String str) {
    Node cur = root;
    for(char c : str.toCharArray()) {
      cur = cur.child.computeIfAbsent(c, key -> new Node());
    }
    cur.outputs.add(str);
  }

  public static void makeFailLink() {
    Queue<Node> q = new LinkedList<>();

    for(Node child : root.child.values()) { // root의 자식은 실패링크가 root
      child.fail = root;
      q.add(child);
    }

    while(!q.isEmpty()) {
      Node cur = q.poll();

      for(Map.Entry<Character, Node> entry : cur.child.entrySet()) {
        char c = entry.getKey();
        Node child = entry.getValue();

        Node failNode = cur.fail;

        // 루트까지 가거나 실패노드의 자식에 c가 포함하면 종료
        while(failNode != null && !failNode.child.containsKey(c)) {
          failNode = failNode.fail;
        }

        if(failNode == null) {
          child.fail = root;
        }else {
          child.fail = failNode.child.get(c); // 일치하는 노드
          child.outputs.addAll(child.fail.outputs); // 새로 일치하는 output에 모두 복사
        }

        q.add(child);
      }
    }
  }
}