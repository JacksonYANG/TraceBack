package ShipLoad;
import java.awt.*;
import java.util.*;

public class shipload {
	static int n;//��װ������
	static int []w;//ÿ����װ�������
	static int c;//��һ���ִ���������
	static int cw;//��ǰ��������
	static int bestw;//���������
	static int r;//ʣ��������
	
	public static int maxLoading(int []ww,int cc){
		//��ʼ��
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
		//������i��Ľ��
		if(i>n){
			//����Ҷ�ӽ��
			if(cw>bestw){
				//��֦������
				bestw=cw;
			}
			return;
		}
		r-=w[i];
		if(cw+w[i]<=c){
			//����������ͬʱ��֦
			cw+=w[i];
			backtrack(i+1);
			cw-=w[i];
		}
		if(cw+r>bestw){
			//����������ͬʱ��֦
			backtrack(i+1);
		}
		r+=w[i];
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num,first_ship,first_best,total_load=0,second_ship;
		Scanner sc=new Scanner(System.in);
		System.out.println("�������ִ����������Լ���һ���ִ��͵ڶ��Ҵ����������ÿո�ֿ�");
		num=sc.nextInt();
		first_ship=sc.nextInt();
		second_ship=sc.nextInt();
		int []shipload=new int[num+1];
		for(int i=1;i<=num;i++){
			System.out.println("������"+i+"�����������");
			shipload[i]=sc.nextInt();
			total_load+=shipload[i];
		}
		first_best=maxLoading(shipload,first_ship);
		if(total_load-first_best<=second_ship){
			System.out.println("��һ�Ҵ���װ���"+first_best+"�����Ļ���");
			System.out.println("�ڶ��Ҵ���װʣ���"+(total_load-first_best)+"�����Ļ���");
		}
		else{
			System.out.println("���Ҵ����޷�װ�����л���");
		}
	}
}
