/*
 ID: tommatt1
 LANG: JAVA
 TASK: tallbarn
*/
import java.util.*;
import java.io.*;
public class tallbarn{
public static long[] a;
public static int n;
	public static void main(String[] args)throws IOException {
		BufferedReader bf=new BufferedReader(new FileReader("tallbarn.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tallbarn.out")));
		StringTokenizer st=new StringTokenizer(bf.readLine());
		n=Integer.parseInt(st.nextToken());
		long k=Long.parseLong(st.nextToken());
		k-=n;
		a=new long[n];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(bf.readLine());
			a[i]=Long.parseLong(st.nextToken());
		}
		double ans=0;
		double lo=0;
		double hi=1e13;
		double md=0;
		for(int i=0;i<600;i++) {
			md=(lo+hi)/2;
			if(bin(md)>=k) {
				lo=md;
			}
			else {
				hi=md;
			}
		}
		long tot=0;
		for(int i=0;i<n;i++) {
			long num=(long)((Math.sqrt(1+4.0*(double)a[i]/md)-1)/2);
			ans+=1.0*a[i]/(double)(num+1);
			tot+=num;
			
		}
		out.println((long)((double)Math.round(ans-(k-tot)*md)));
		out.close();
	}
	public static long bin(double x) {
		long tot=0;
		for(int i=0;i<n;i++) {
			tot+=(long)((Math.sqrt(1+4*a[i]/x)-1)/(double)2);
		}
		return tot;
	}
	
}


