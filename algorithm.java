import java.util.Scanner;
public class algorithm {

	static int[] arr() {
		int temp[] = new int[4];
		for (int i =0; i<temp.length; i++)
			temp[i] = i;
		return temp;
	}
	
	public static void main(String[] args) {
	
	    // 3�� ��� �����ٰ�
		int sum=0;
		for(int n=1 ; n<=100 ; n++) {
			if (n%3==0) sum=sum+n;
		}
		System.out.println(sum);	
		
		// �Է°� �� ���� ū �� �̾Ƴ���
		Scanner scanner = new Scanner(System.in);
//		int ary[]=new int[5];
//		int max = 0;
//		for (int n=0 ; n<5 ; n++) {
//			System.out.print(n+1+"��° ���ڸ� �Է��ּ���:");
//			ary[n]=scanner.nextInt();
//			if (ary[n] > max)
//				max = ary[n];
//		}
//		System.out.println("���� ū ���� "+max+" �Դϴ�");
//		scanner.close();
			
		// ����������
		System.out.println("���������� �����Դϴ�. ����,����,�� �߿��� �Է��ϼ���");
		System.out.print("ö��>>");
		String cco = scanner.next();
		System.out.print("����>>");
		String yco = scanner.next();
		
		if (cco.equals("����"))
			switch (yco){
			case "����": System.out.println("�����ϴ�."); break;
			case "��": System.out.println("���� �̰���ϴ�."); break;
			case "����": System.out.println("ö���� �̰���ϴ�."); break;
			}
		
		if (cco.equals("����"))
			switch (yco){
			case "����": System.out.println("�����ϴ�."); break;
			case "����": System.out.println("���� �̰���ϴ�."); break;
			case "��": System.out.println("ö���� �̰���ϴ�."); break;
			}
			
		if (cco.equals("��"))
			switch (yco){
			case "��": System.out.println("�����ϴ�."); break;
			case "����": System.out.println("���� �̰���ϴ�."); break;
			case "����": System.out.println("ö���� �̰���ϴ�."); break;
		}
		
		// ��ܽ� �� ��� 1
		for (int i=0; i<5; i++) {
			for (int j=5-i; j>0; j--) {
			System.out.print("*");
			}
			System.out.println();
		}
		
		// ��ܽ� �� ��� 2
		int i = 0;
		while (i<5) {
			for (int j=5-i; j>0; j--) {
			System.out.print("*");
			}
		System.out.println();
		i++;
	
	}

}
}

