// 201811958 ������, �߰������� �ڵ��Դϴ�.
// �ִ��� �ּ��� �̿��Ͽ� �ۼ��� �ڵ忡 ���� ������ ÷���Ͽ����ϴ�.
// �ּ��� ��� �ش��ڵ��� ���ʿ� �������ϴ�.
public class Knapsack {

	// ��ǰ���� �̸��� ��Ÿ���� �迭�� ǥ�� ������� ����ϴ�.
	public static String[] name = {"�޴���","���","��","��","����","Ŀ��","����","����","ħ��","����","������"};
	// ��ǰ���� ���Ը� ��Ÿ���� �迭�� ǥ�� ������� ����ϴ�.
	public static int[] W = {1,11,12,5,6,3,7,4,16,2,2}; 
	// ��ǰ���� �������� ��Ÿ���� �迭�� ǥ�� ������� ����ϴ�.
	public static int[] P = {16,35,36,20,21,10,12,16,46,12,28};
	
	// ��ǰ�� ��ϵ��� 11�� �Ӹ��ƴ϶� �� �߰��ǰų� ������ ��쵵 ����Ͽ� �ڵ带 ���������� �����ϱ� ���� ���� �����Դϴ�. 
	public static int count_num = name.length; 
	// �賶�� ���ѹ��԰� 40�� �ƴ� ��쵵 ����Ͽ� �ڵ带 ���������� �����ϱ� ���� ���� �����Դϴ�.
	public static int maximum_weight = 40;
	
	
	// ������ �����ڸ��� ���߱�����, �ʿ��� "0"�� ������ ��ȯ�ϴ� �޼ҵ带 �����Ͽ����ϴ�. 
	// ������� 101�̶�� �������� ������ �տ� 8���� "0"�� �ٿ� 00000000101�� ����� ���� "0" 8���� ��ȯ���ִ� �۾��Դϴ�.
	public static String plus_zero(String x) {
		int plus = count_num - x.length(); 
		String count_zero = "0";	
		// x�� ���̰� �����ڸ���� "0"�� ä�� �ʿ䰡 ���� ������ ����ó���� �Ͽ����ϴ�.
			if (x.length() == count_num) count_zero = "";		
			else {
				for(int k=0; k<plus-1; k++) {
				count_zero +="0"; 
				} 
			}
		return count_zero;
	}

	public static void main(String[] args) {
		
		// ���� ū �������� �հ踦 ���ϱ� ���� �����Դϴ�.
		int max_sum_p = 0;
		// Math.pow(2, count_num)�� 2048�� 2048���� ��� ����� ���� ����ϱ� �����Դϴ�. 
		for(int index1=0; index1<Math.pow(2, count_num); index1++) { 
			// index1�� �������� ��ȯ�� ���� S_index��� ������ �����Ͽ����ϴ�.
			String S_index = Integer.toBinaryString(index1);
			// �������, �������� ��ȯ�������� 101�� ���� ���·� ���� �����µ� ���ϴ� ���´� 00000000101�� �����̹Ƿ� ������ ������ �޼ҵ带 ����Ͽ����ϴ�. 
			// 00000000101 = 00000000 + 101
			String a = plus_zero(S_index) + S_index;
			
			// ������ �հ踦 ���ϱ� ���� �����Դϴ�.
			int sum_w = 0;
			// ���⼭ count_num�� 11�̰� count_num�� ����� ������ 11���� ��� ��ǰ�� ���� ������ ��� �����Դϴ�.
			for(int index2=0; index2 < count_num; index2++) {			
				// a.charAt(index2)-48�� ����� ������ �ƽ�Ű�ڵ带 ����� ������ 0�� 1�� ���� �����ϱ� �����Դϴ�.
				// 11�ڸ� 2������ ���Ը� ��Ÿ���� W�� �迭�� ������� ���Ͽ� ��� ���ϸ� �� ���԰� �״�� ����˴ϴ�.	
				// �������, a�� 00000000101��� 0�� �ڸ��� �ش� ��ǰ�� �������� �ʴ´ٴ� ������, ���Կ� ���ص� 0���� ��� �����տ� �ݿ������ʰ� 
				//                           1�� �ڸ��� W[8]�� 16�� W[10]�� 2���� ���� ������ �� �̵��� ���Ѱ� 18�� ��Ե˴ϴ�.
				sum_w = sum_w + (a.charAt(index2)-48) * W[index2]; 			
			}
			
			// �賶�� ���� ������ 40kg�̱� ������ ������ �հ谡 40�� �Ѵ´ٸ� ���������� index1���� �Ѿ�ϴ�.
			if (sum_w>maximum_weight) continue;
			// �������� �հ踦 ���ϱ� ���� �����Դϴ�.
			int sum_p = 0;
			// sum_w�� ���ϴ� ����� �Ȱ��� sum_p�� ���մϴ�. 
			for(int index3=0; index3 < count_num; index3++) {				
				sum_p = sum_p + (a.charAt(index3)-48) * P[index3]; 				
			}
			// sum_p�� �ִ� ��, ���� ū �������� �հ踦 ���ϱ� �����Դϴ�.
			if (sum_p > max_sum_p)
				max_sum_p = sum_p;
		}
		
		// �賶�� �������� �ٸ��� ����� �� �� �̻��� ���, �� ������ ��� ����ϱ� ���� �ۼ��Ͽ����ϴ�.
		// ���� for������ ���� ���� ū �������� �հ踦 �̿��մϴ�.
		// �Ʒ��� if (sum_p==max_sum_p)... �������� �ڵ�� ���� for���� �ڵ�� ������ �����̹Ƿ� �ּ��� �����մϴ�.
		for(int index4=0; index4<Math.pow(2, count_num); index4++) { 
			String S_index = Integer.toBinaryString(index4);
			String a = plus_zero(S_index) + S_index; 
			int sum_w = 0;
			for(int index5=0; index5 < count_num; index5++) {				
				sum_w = sum_w + (a.charAt(index5)-48) * W[index5]; 			
			}
			if (sum_w>maximum_weight) continue;
			int sum_p = 0;
			for(int index6=0; index6 < count_num; index6++) {				
				sum_p = sum_p + (a.charAt(index6)-48) * P[index6]; 			
			}
			// "�������� �հ谡 ���� ū �������� �հ�� ���ٸ�"�� ������ ��������ν� ���� �����ذ� ����������� ��� Ȯ���� �� �ֽ��ϴ�.
			if (sum_p==max_sum_p) {
				// �賶�� ��� ��ǰ���� �̸��� ������ �����Դϴ�.
				String name_get = "";
				for(int index7=0; index7 < count_num; index7++) {	
					// �������� ��ǰ�� 1�� ǥ���ϹǷ� a.charAt(index7)-48 == 1�� ������ ���� �������� ��ǰ�鸸 Ȯ���� �� �ֽ��ϴ�.
					if (a.charAt(index7)-48 == 1) {
						// �������� ��ǰ���� �̸��� name_get�� �����ŵ�ϴ�.
						name_get += name[index7]+" ";
					}
				}
				// �賶�� ��� ��ǰ���� ���, �賶�� ���Կ� �߰������� ��ǰ���� ������ �ձ��� ����մϴ�.
				System.out.println("�賶�� ��� ��ǰ���� ��� : " + name_get);
				System.out.println("�賶�� ���� : " + sum_w + "kg");
				System.out.println("��ǰ���� ������ �� : " + sum_p);
			}
		}
	}
}




