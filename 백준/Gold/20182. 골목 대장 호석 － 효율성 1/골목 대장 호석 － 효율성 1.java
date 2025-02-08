import java.util.*;
import java.io.*;
class Main {
	public static class Node {
		int to;
		long money;
		Node(int to, long money) {
				this.to = to;
				this.money = money;
	 }
   }
   static int N;
   static int M;
   static int start;
   static int end;
   static long cache;
   static List<List<Node>> list = new ArrayList<>();
   public static void main(String[] args) throws IOException {
	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 StringTokenizer st = new StringTokenizer(br.readLine());

	 N = Integer.parseInt(st.nextToken());
	 M = Integer.parseInt(st.nextToken());
	 start = Integer.parseInt(st.nextToken());
	 end = Integer.parseInt(st.nextToken());
	 cache = Long.parseLong(st.nextToken());

	 for(int i=0; i<=N; i++) {
	   list.add(new ArrayList<>());
	 }

	 for(int i=0; i<M; i++) {
	   st = new StringTokenizer(br.readLine());
	   int s = Integer.parseInt(st.nextToken());
	   int e = Integer.parseInt(st.nextToken());
	   long c = Long.parseLong(st.nextToken());
	   list.get(s).add(new Node(e, c));
	   list.get(e).add(new Node(s, c));
	 }

	 long L = 1;
	 long R = 1000000000L;
	 while(L <= R) {
	   long mid = (L + R)/2;
	   if(dijkstra(mid)) {
	     R = mid-1;
	   }else {
	     L = mid+1;
	   }
	}

	if(L == 1000000001L) {
	  System.out.print(-1);
	  return;
	}

	System.out.print(L);
  }

  public static boolean dijkstra(long bad) {
	long[] dist = new long[N+1];
	boolean[] visited = new boolean[N+1];
	
	Arrays.fill(dist, Long.MAX_VALUE);
	dist[start] = 0;

	PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> {
	  if(x.money < y.money) {
	    return -1;
	  }else {
	    return 1;
	  }
	});
	pq.offer(new Node(start, 0L));

	while(!pq.isEmpty()) {
	  Node now = pq.poll();

	  if(visited[now.to]) {
		   continue;
	  }

	  visited[now.to] = true;

	  for(int i=0; i<list.get(now.to).size(); i++) {
	    Node next = list.get(now.to).get(i);

	    if(next.money > bad) {
		     continue;
	    }

	    if(dist[next.to] > now.money + next.money) {
	      dist[next.to] = now.money + next.money;
	      pq.offer(new Node(next.to, dist[next.to]));
	    }
	  }
   }

   if(dist[end] > cache) {
	  return false;
   }
   return true;
   }
}