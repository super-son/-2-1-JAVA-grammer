package test;

import test2.Eagle;
import test2.penguin;
import test2.sparrow;

public class BirdCage {

	public static void main(String[] args) {
		Bird[] flocks = new Bird[] { new Bird(), new Eagle(), new sparrow(), new penguin() }; //�������� ������� ��ü �ʱ�ȭ. �����ִ� �⺻������
		// �ڽĵ��� Bird�� �迭���� �Ҵ��ϰ� �ִ°���.
		/////////////////////////////////////////
		// Heap ������ flocks�� �ּ� [1,2,3,4,5] 5: length���� �̷������� �����������.  �׸��� �� 1, 2, 3, 4�� ���� �� Heap�� ������� bird ���� eagle����, sparrow ����, penguin������
		// ����Ű�� ������ �ϰ��־�.
		// ��͵�� �ؼ��Ҽ� �ְڴ�.
		// flocks[0]: Bird, flocks[1]: Eagle..
		
		// �̷��� ������ ���� ���̰ڳ�
		// Bird x = new Eagle(); up-casting
		// Bird y = new sparrow();
		// Bird z = new penguin();
		// .....
		
		// �� ĳ���ð� �ٿ�ĳ���� ����
		
//		Eagle e = new Bird(); //down casting. ��������� (Eagle) ����� ���� ��ȯ�ϰ�����.. ��� �����δ� ������ �� ���ϰ���.
//		int x1 = 300L; // down-casting (int)�� ����� ���� ����ȯ
//		long x2 = 300; // ū ������ ���� ���� �������� ������ up-casting

		// for each ��. enmuerate ���~)
		for (Bird b:flocks) { 
			System.out.println("wings: "+ b.numberwing);
			b.move();
		// �޼ҵ� ��� : move �޼ҵ�� �ڽĹ����� �޼ҵ尡 �Դµ�, 
		// ������ ��� : �����ʹ� �������� �ڵ� ���۷��� �� �θ��� �����Ͱ� �����ϰ� �ֳ�.
		}
		
		Bird[] bs = new Bird[10]; // �ּҰ��� 10�� ����ž� �� 10������ ����� �ƴϰ�
		for (Bird i:bs) {
			System.out.println("Bird i:" + i); // ��� ���� null�� ���� ������
			i = new Bird(2,22);
			System.out.println("Bird i�� �ٸ� �� " + i.numberleg); // �� 100������ ��������ž�
		}
	
	
	}

}
