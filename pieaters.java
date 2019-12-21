/*
 ID: tommatt1
 LANG: JAVA
 TASK: pieaters
*/
import java.util.*;
import java.io.*;
public class pieaters{
public static long[][][] mx;
	public static void main(String[] args)throws IOException {
		BufferedReader bf=new BufferedReader(new FileReader("pieaters.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pieaters.out")));
		StringTokenizer st=new StringTokenizer(bf.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		mx=new long[n][n][n];
		long[][] dp=new long[n][n];
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(bf.readLine());
			int w=Integer.parseInt(st.nextToken());
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken())-1;
			for(int j=a;j<=b;j++) {
				mx[j][a][b]=w;
			}
		}
		for(int i=0;i<n;i++) {
			for(int j=i;j>=0;j--) {
				for(int k=i;k<n;k++) {
					if(j>0) mx[i][j-1][k]=Math.max(mx[i][j-1][k], mx[i][j][k]);
					if(k<n-1) mx[i][j][k+1]=Math.max(mx[i][j][k+1], mx[i][j][k]);
				}
			}
		}
		for(int i=0;i<n;i++) {
			dp[i][i]=mx[i][i][i];
		}
		for(int s=2;s<=n;s++) {
			for(int i=0;i<=n-s;i++) {
				for(int j=i;j<i+s;j++) {
					if(j<n-1) dp[i][i+s-1]=Math.max(dp[i][i+s-1], dp[i][j]+dp[j+1][i+s-1]);
					if(j==n-1) dp[i][i+s-1]=Math.max(dp[i][i+s-1], dp[i][j-1]+mx[j][i][i+s-1]);
					if(j==0) dp[i][i+s-1]=Math.max(dp[i][i+s-1], dp[j+1][i+s-1]+mx[j][i][i+s-1]);
					if(j>0&&j<n-1) dp[i][i+s-1]=Math.max(dp[i][i+s-1], mx[j][i][i+s-1]+dp[i][j-1]+dp[j+1][i+s-1]);
				}
			}
		}
		out.println(dp[0][n-1]);
		out.close();
	}
	
}


