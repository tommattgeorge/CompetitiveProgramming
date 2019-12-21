/*
 ID: tommatt1
 LANG: JAVA
 TASK: snowcow
*/
import java.util.*;
import java.io.*;
public class snowcow{
public static ArrayList<Integer>[] adj;
public static int cdx=0;
public static int[] s;
public static int[] e;
public static int[] sub;
public static int[] rev;
public static BIT top,bot;
	public static void main(String[] args)throws IOException {
		BufferedReader bf=new BufferedReader(new FileReader("snowcow.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowcow.out")));
		StringTokenizer st=new StringTokenizer(bf.readLine());
		int n=Integer.parseInt(st.nextToken());
		int q=Integer.parseInt(st.nextToken());
		adj=new ArrayList[n+1];
		TreeSet<Integer>[] col=new TreeSet[100001];
		for(int i=1;i<=100000;i++) {
			col[i]=new TreeSet<Integer>();
		}
		s=new int[n+1];
		e=new int[n+1];
		sub=new int[n+1];
		rev=new int[n+1];
		top=new BIT(n+1);
		bot=new BIT(n+1);
		for(int i=1;i<=n;i++) {
			adj[i]=new ArrayList<Integer>();
		}
		for(int i=0;i<n-1;i++) {
			st=new StringTokenizer(bf.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		dfsord(1,-1);
		for(int i=0;i<q;i++) {
			st=new StringTokenizer(bf.readLine());
			int q1=Integer.parseInt(st.nextToken());
			if(q1==1) {
				int x=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				if(col[c].isEmpty()) {
					col[c].add(s[x]);
					upd(s[x],1);
					continue;
				}
				Integer pst=col[c].floor(s[x]);
				if(pst!=null) {
				int z=e[rev[pst]];
				if(z>=e[x]) {
					continue;
				}
				}
				Integer aft=col[c].higher(s[x]);
				if(aft!=null) {
					int z=e[rev[aft]];
					if(z<=e[x]) {
						NavigableSet<Integer> nset=col[c].tailSet(aft, true);
						while(!nset.isEmpty()) {
							Integer af=nset.pollFirst();
							if(e[x]>=e[rev[af]]) {
								nset.remove(af);
								col[c].remove(af);
								upd(af,-1);
							}
							else {
								break;
							}
						}
					}
				}
				col[c].add(s[x]);
				upd(s[x],1);
			}
			if(q1==2) {
				int x=Integer.parseInt(st.nextToken());
				out.println(sub[x]*top.query(s[x])+bot.query(e[x])-bot.query(s[x]));
			}
		}
		out.close();
	}
	public static void upd(int x,int i) {
		top.update(x,i);
		top.update(e[rev[x]]+1, -i);
		bot.update(x, i*sub[rev[x]]);
	}
	static class BIT {
		public long[] tree;
		public long count;
		public BIT(int n) {
			tree = new long[n+5];
			count=0;
		}
		public void update(int index, long val) {
			count+=val;
			while(index < tree.length) {
				tree[index] += val;
				index += index & -index;
			}
		}
		public long query(int index) {
			long ret = 0;
			while(index > 0) {
				ret += tree[index];
				index -= index & -index;
			}
			return ret;
		}
	}
	public static void dfsord(int x, int p) {
		s[x]=++cdx;
		rev[s[x]]=x;
		for(int i:adj[x]) {
			if(i!=p) {
				dfsord(i,x);
			}
		}
		e[x]=cdx;
		sub[x]=e[x]-s[x]+1;
	}
}


