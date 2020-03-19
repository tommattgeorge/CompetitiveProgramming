/*
 ID: tommatt1
 LANG: JAVA
 TASK: 
*/
import java.util.*;
import java.io.*;
public class cf575b{
static ArrayList<Integer>[] adj;
static ArrayList<Integer>[] dir;
static int[][] p;
static int[] d;
static int mod=1000000007;
static long[] pow;
static int[] up;
static boolean[] nup;
static boolean[] ndn;
static int[] dn;
	public static void main(String[] args)throws IOException {
		//BufferedReader bf=new BufferedReader(new FileReader("cf575b.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cf575b.out")));
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st=new StringTokenizer(bf.readLine());
		int n=Integer.parseInt(st.nextToken());
		adj=new ArrayList[n];
		dir=new ArrayList[n];
		up=new int[n];
		nup=new boolean[n];
		ndn=new boolean[n];
		dn=new int[n];
		p=new int[n][20];
		d=new int[n];
		for(int i=0;i<n;i++) {
			adj[i]=new ArrayList<Integer>();
			dir[i]=new ArrayList<Integer>();
		}
		for(int i=0;i<n-1;i++) {
			st=new StringTokenizer(bf.readLine());
			int a1=Integer.parseInt(st.nextToken())-1;
			int a2=Integer.parseInt(st.nextToken())-1;
			int a3=Integer.parseInt(st.nextToken());
			adj[a1].add(a2);
			adj[a2].add(a1);
			if(a3==1) dir[a1].add(a2);
		}
		st=new StringTokenizer(bf.readLine());
		int k=Integer.parseInt(st.nextToken());
		pow=new long[k+2];
		pow[0]=1;
		for(int i=1;i<k+2;i++) {
			pow[i]=(pow[i-1]*2)%mod;
		}
		dfs(0,0,1);
		for(int i=0;i<n;i++) {
			for(int j:dir[i]) {
				if(d[j]<d[i]) {
					ndn[i]=true;
				}
				else {
					nup[j]=true;
				}
			}
		}
		for(int i=1;i<20;i++) {
			for(int x=0;x<n;x++) {
				p[x][i]=p[p[x][i-1]][i];
			}
		}
		int prev=0;
		st=new StringTokenizer(bf.readLine());
		for(int i=0;i<k;i++) {
			int nxt=Integer.parseInt(st.nextToken())-1;
			int l=lca(prev,nxt);
			up[prev]++;
			dn[nxt]++;
			up[l]--;
			dn[l]--;
			prev=nxt;
		}
		
		out.println(dfs2(0,-1));
		out.close();
	}
	static void dfs(int x, int par, int dp) {
		d[x]=dp;
		p[x][0]=par;
		for(int i:adj[x]) {
			if(i!=par) {
				dfs(i,x,dp+1);
			}
		}
	}
	static int dfs2(int x, int p) {
		long ans=0;
		for(int i:adj[x]) {
			if(i!=p) {
				ans=(ans+dfs2(i,x))%mod;
				up[x]+=up[i];
				dn[x]+=dn[i];
			}
		}
		if(nup[x]) ans=(ans+pow[up[x]]-1)%mod;
		if(ndn[x]) ans=(ans+pow[dn[x]]-1)%mod;
		return (int)ans;
	}
	static int lca(int a,int b)
	{
		for(int j=19;j>=0;j--) {
			if(d[p[a][j]] >= d[b]) {
				a = p[a][j];
			}
		}
		for(int j=19;j>=0;j--) {
			if(d[p[b][j]] >= d[a]) {
				b = p[b][j];
			}
		}
		for(int j=19;j>=0;j--) {
			if(p[a][j]!=p[b][j]) {
				a = p[a][j];
				b = p[b][j];
			}
		}
		if(a==b) return a;
		return p[a][0];
	}
}


