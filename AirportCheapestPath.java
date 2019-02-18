import java.util.*;


import java.io.*;
public class AirportCheapestPath {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new FileReader(new File("airports.in")));
		int n = Integer.parseInt(in.readLine());
		String[] cities = new String[n+1];
		for(int i = 1;i<=n;i++)
		{
			cities[i] = in.readLine();
		}
		int m = Integer.parseInt(in.readLine());
		ArrayList<edge>[] a = new ArrayList[n+1];
		for(int i = 1;i<=n;i++)
		{
			a[i] = new ArrayList<edge>();
		}
		for(int i = 0;i<m;i++)
		{
			String[] temp = in.readLine().split(" ");
			int p = Integer.parseInt(temp[0]);
			int q = Integer.parseInt(temp[1]);
			int weight = Integer.parseInt(temp[2]);
			a[p].add(new edge(q,weight));
			a[q].add(new edge(p,weight));
		}
		System.out.println("From New York");
		for(int i =1;i<=n;i++)
		{
			System.out.print(cities[i]+", ");
		}
		System.out.println();
		dijkstra(1,a,n);
	}
	static class edge implements Comparable<edge>
	{
		int vertex, weight;
		public edge(int vertex, int weight)
		{
			this.vertex = vertex;
			this.weight = weight;
		}
		public int compareTo(edge that)
		{
			return this.weight-that.weight;
		}
	}
	public static void dijkstra(int s, ArrayList<edge>[] a, int n)
	{
		PriorityQueue<edge> pq = new PriorityQueue<edge>();
		int[] d = new int[n+1];
		for(int i =1;i<=n;i++)
		{
			d[i]= 1000000000;
		}
		pq.add(new edge(s,0));
		while(!pq.isEmpty())
		{
			edge temp = pq.poll();
			int cur = temp.vertex;
			int curd = temp.weight;
			if(curd>d[cur])
				continue;
			for(int i=0;i<a[cur].size();i++)
			{
				int next = a[cur].get(i).vertex;
				int nextD = a[cur].get(i).weight+curd;
				if(nextD<d[next])
				{
					d[next] = nextD;
					pq.add(new edge(next,nextD));
				}
			}
			
		}
		d[s] = 0;
		for(int i =1;i<d.length;i++)
		{
			System.out.print(d[i]+" ");
		}
	}

}
