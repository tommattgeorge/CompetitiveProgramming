/*
 ID: tommatt1
 LANG: JAVA
 TASK: 
*/
import java.util.*;
import java.io.*;
public class boards{

	public static void main(String[] args)throws IOException {
		BufferedReader bf=new BufferedReader(new FileReader("boards.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("boards.out")));
		StringTokenizer st=new StringTokenizer(bf.readLine());
		int n=Integer.parseInt(st.nextToken());
		int p=Integer.parseInt(st.nextToken());
		int tot=0;
		pair[] ev=new pair[2*p];
		int[] bomin=new int[p];
		int[] skip=new int[p];
		for(int i=0;i<p;i++) {
			st=new StringTokenizer(bf.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			ev[2*i]=new pair(a,b,i,0);
			ev[2*i+1]=new pair(c,d,i,1);
			skip[i]=c-a+d-b;
		}
		Arrays.sort(ev);
		TreeMap<Integer,Integer> min=new TreeMap<Integer,Integer>();
		min.put(0,0);
		for(int i=0;i<2*p;i++) {
			pair e=ev[i];
			if(e.d==1) {
				int val=bomin[e.c]-skip[e.c];
				NavigableMap<Integer,Integer> nm=min.tailMap(e.b, true);
				while(!nm.isEmpty()&&val<nm.get(nm.firstKey())) {
					min.remove(nm.firstKey());
					nm.pollFirstEntry();
				}
				if(min.lowerKey(e.b+1)!=null&&val>=min.get(min.lowerKey(e.b+1))){
					continue;
				}
				min.put(e.b, val);
			}
			else {
				bomin[e.c]=min.get(min.lowerKey(e.b+1));
			}
		}
		out.println(n+n+min.get(min.lowerKey(1000000001)));
		out.close();
	}
	static class pair implements Comparable<pair>{
		int a,b,c,d;
		public pair(int x,int y,int z, int w) {
			a=x;b=y;c=z;d=w;
		}
		public int compareTo(pair p) {
			if(a==p.a) {
				return b-p.b;
			}
			return a-p.a;
		}
	}
}


