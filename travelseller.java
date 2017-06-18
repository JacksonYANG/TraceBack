package TravelSeller;
import java.awt.*;
import java.util.*;
import java.math.*;

public class travelseller {
	static int n;//ͼ�Ķ�����
	static int []x;//��ǰ��
	static int []bestx;//��ǰ���Ž�
	static int bestc;//��ǰ����ֵ(cc<bestc��������)
	static int cc;//��ǰ����
	static int [][]a;//ͼ���ڽӾ���
	
	
	public static int tsp(int [][]aa,int []v){
		//��ʼ��
		n=v.length-1;
		x=new int[n+1];
		for(int i=1;i<=n;i++){
			x[i]=i;
		}//��ǰ��Ϊ����
		bestc=Integer.MAX_VALUE;//�����ó����
		a=aa;
		bestx=v;
		cc=0;
		
		//����
		backtrack(2);
		return bestc;
	}
	
	private static void backtrack(int i){
		int flag;
		if(i==n){//����Ҷ����ʱ������ж�
			if((a[x[n-1]][x[n]]<Integer.MAX_VALUE)&&(a[x[n]][1]<Integer.MAX_VALUE)&&(bestc==Integer.MAX_VALUE||cc+a[x[n-1]][x[n]]+a[x[n]][1]<bestc)){
				//��β���ӣ��������ӣ���֦,ͬʱ�÷ѱ�bestcС������ͬ����ȥҶ���
				for(int j=1;j<=n;j++){
					bestx[j]=x[j];//����·��
				}
				bestc=cc+a[x[n-1]][x[n]]+a[x[n]][1];//��������ֵ
			}
		}
		else{
			for(int j=i;j<=n;j++){
				//�ж��Ƿ���Խ���x[j]��������
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
		System.out.println("�����붥�����Ŀ");
		num=sc.nextInt();
		int []bestway=new int[num+1];
		for(i=1;i<=num;i++){
			 bestway[i]=i;
		}
		int [][]Matrix=new int[num+1][num+1];
		for(i=1;i<=num;i++) Matrix[i][i]=0;
		for(i=1;i<num;i++){
			for(j=i+1;j<=num;j++){
				System.out.print("��"+i+"����㵽��"+j+"������Ƿ�ɴ�,�ɴ�����y,���ɴ�����n:");
				choose=sc.next();
				if(choose.equals("y")){
					System.out.print("�������"+i+"����㵽��"+j+"�������÷�:");
					Matrix[i][j]=sc.nextInt();
				}
				else{
					Matrix[i][j]=Integer.MAX_VALUE;
				}
				Matrix[j][i]=Matrix[i][j];
			}
			
		}
		bestpay=tsp(Matrix,bestway);
		System.out.println("������÷�Ϊ:"+bestpay);
		System.out.print("����Ӧ������·��Ϊ:");
		for(i=1;i<=num;i++){
			System.out.print(bestway[i]+" ");
		}
	}

}
