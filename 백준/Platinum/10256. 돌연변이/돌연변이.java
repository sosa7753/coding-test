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
    StringTokenizer st;

    int T = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<T; i++) {
      root = new Node();

      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());

      String DNA = br.readLine();
      String marker = br.readLine();

      Set<String> set = pattern(marker);
      for(String mark : set) {
        insert(mark);
      }

      makeFailLink();

      sb.append(search(DNA)).append("\n");
    }

    System.out.print(sb);
  }

  public static int search(String str) {
    int result = 0;
    Node cur = root;

    for(char c : str.toCharArray()) {
      while(cur != root && !cur.child.containsKey(c)) {
        cur = cur.fail; // 일치하지 않으면 실패링크로 가기
      }

      if(cur.child.containsKey(c)) {
        cur = cur.child.get(c);
      }

      result += cur.outputs.size();
    }
    return result;
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

    for(Node child : root.child.values()) {
      child.fail = root;
      q.offer(child);
    }

    while(!q.isEmpty()) {
      Node cur = q.poll();

      for(Map.Entry<Character, Node> entry : cur.child.entrySet()) {
        char c = entry.getKey();
        Node child = entry.getValue();

        Node failNode = cur.fail;
        while(failNode != null && !failNode.child.containsKey(c)) {
          failNode = failNode.fail;
        }

        if(failNode == null) {
          child.fail = root;
        }else {
          child.fail = failNode.child.get(c);
          child.outputs.addAll(child.fail.outputs);
        }

        q.offer(child);
      }
    }
  }

  public static Set<String> pattern(String str) {
    Set<String> set = new HashSet<>();

    int[] arr = new int[2];
    DFS(arr, 0, 0, str, set);

    return set;
  }

  public static void DFS(int[] arr, int idx, int cnt, String str, Set<String> set) {
    if(cnt == 2) {
      StringBuilder sb = new StringBuilder();
      sb.append(str.substring(0,arr[0]));
      sb.append(reverse(str.substring(arr[0], arr[1])));
      sb.append(str.substring(arr[1]));
      set.add(sb.toString());
      return;
    }

    for(int i=idx; i<=str.length(); i++) {
      arr[cnt] = i;
      DFS(arr, i+1, cnt+1, str, set);
    }
  }
  
  public static String reverse(String str) {
    StringBuilder sb = new StringBuilder(str);
    return sb.reverse().toString();
  }
}