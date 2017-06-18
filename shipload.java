package ShipLoad;
import java.awt.*;
import java.util.*;

public class shipload {
	static int n;//集装箱总数
	static int []w;//每个集装箱的重量
	static int c;//第一艘轮船的载重量
	static int cw;//当前的载重量
	static int bestw;//最佳载重量
	static int r;//剩余载重量
	
	public static int maxLoading(int []ww,int cc){
		//初始化
		n=ww.length-1;
		c=cc;
		w=ww;
		cw=0;
		bestw=0;
		r=0;
		for(int i=1;i<=n;i++){
			r+=w[i];
		}
		
		backtrack(1);
		return bestw;
	}
	
	public static void backtrack(int i){
		//搜索第i层的结点
		if(i>n){
			//到达叶子结点
			if(cw>bestw){
				//剪枝右子树
				bestw=cw;
			}
			return;
		}
		r-=w[i];
		if(cw+w[i]<=c){
			//搜索左子树同时剪枝
			cw+=w[i];
			backtrack(i+1);
			cw-=w[i];
		}
		if(cw+r>bestw){
			//搜索右子树同时剪枝
			backtrack(i+1);
		}
		r+=w[i];
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num,first_ship,first_best,total_load=0,second_ship;
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入轮船的总重量以及第一艘轮船和第二艘船的重量，用空格分开");
		num=sc.nextInt();
		first_ship=sc.nextInt();
		second_ship=sc.nextInt();
		int []shipload=new int[num+1];
		for(int i=1;i<=num;i++){
			System.out.println("请输入"+i+"个货物的重量");
			shipload[i]=sc.nextInt();
			total_load+=shipload[i];
		}
		first_best=maxLoading(shipload,first_ship);
		if(total_load-first_best<=second_ship){
			System.out.println("第一艘船能装最多"+first_best+"重量的货物");
			System.out.println("第二艘船能装剩余的"+(total_load-first_best)+"重量的货物");
		}
		else{
			System.out.println("两艘船均无法装满所有货物");
		}
	}
}
