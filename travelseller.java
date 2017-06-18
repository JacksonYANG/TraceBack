package TravelSeller;
import java.awt.*;
import java.util.*;
import java.math.*;

public class travelseller {
	static int n;//图的顶点数
	static int []x;//当前解
	static int []bestx;//当前最优解
	static int bestc;//当前最优值(cc<bestc进入子树)
	static int cc;//当前费用
	static int [][]a;//图的邻接矩阵
	
	
	public static int tsp(int [][]aa,int []v){
		//初始化
		n=v.length-1;
		x=new int[n+1];
		for(int i=1;i<=n;i++){
			x[i]=i;
		}//当前解为自身
		bestc=Integer.MAX_VALUE;//先设置成最大
		a=aa;
		bestx=v;
		cc=0;
		
		//回溯
		backtrack(2);
		return bestc;
	}
	
	private static void backtrack(int i){
		int flag;
		if(i==n){//到达叶结点的时候进行判断
			if((a[x[n-1]][x[n]]<Integer.MAX_VALUE)&&(a[x[n]][1]<Integer.MAX_VALUE)&&(bestc==Integer.MAX_VALUE||cc+a[x[n-1]][x[n]]+a[x[n]][1]<bestc)){
				//首尾连接，若不连接，剪枝,同时旅费比bestc小，否则同样剪去叶结点
				for(int j=1;j<=n;j++){
					bestx[j]=x[j];//更新路径
				}
				bestc=cc+a[x[n-1]][x[n]]+a[x[n]][1];//更新最优值
			}
		}
		else{
			for(int j=i;j<=n;j++){
				//判断是否可以进入x[j]的左子树
				if(a[x[i-1]][x[j]]<Integer.MAX_VALUE&&(bestc==Integer.MAX_VALUE||cc+a[x[i-1]][x[j]]<bestc)){
					flag=x[i];
					x[i]=x[j];
					x[j]=flag;
					cc+=a[x[i-1]][x[i]];
					backtrack(i+1);
					cc-=a[x[i-1]][x[i]];
					flag=x[i];
					x[i]=x[j];
					x[j]=flag;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i,j,num,bestpay;
		String choose;
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入顶点的数目");
		num=sc.nextInt();
		int []bestway=new int[num+1];
		for(i=1;i<=num;i++){
			 bestway[i]=i;
		}
		int [][]Matrix=new int[num+1][num+1];
		for(i=1;i<=num;i++) Matrix[i][i]=0;
		for(i=1;i<num;i++){
			for(j=i+1;j<=num;j++){
				System.out.print("第"+i+"个结点到第"+j+"个结点是否可达,可达输入y,不可达输入n:");
				choose=sc.next();
				if(choose.equals("y")){
					System.out.print("请输入第"+i+"个结点到第"+j+"个结点的旅费:");
					Matrix[i][j]=sc.nextInt();
				}
				else{
					Matrix[i][j]=Integer.MAX_VALUE;
				}
				Matrix[j][i]=Matrix[i][j];
			}
			
		}
		bestpay=tsp(Matrix,bestway);
		System.out.println("最便宜旅费为:"+bestpay);
		System.out.print("所对应的最优路径为:");
		for(i=1;i<=num;i++){
			System.out.print(bestway[i]+" ");
		}
	}

}
