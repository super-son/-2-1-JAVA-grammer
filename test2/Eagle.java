package test2; // ��Ű�� ����

import test.Bird; // �θ� ��Ű�� import

public class Eagle extends Bird{ //�ٸ� ��Ű�� ������ �׷��� ��ã���ϱ� �ڵ����� import ���ֳ�

	// Eagle is a Bird. (Is_A relationship). �ٸ� ���鵵 ��������
	
	// ���� Bird.java�� int�� �����ڸ� �־������ 
//	public Eagle(){
//		super(2,4); // �̷��� �� �޾��ִ� �ڵ带 ���� �ۼ��ؾ���!
//	}
	public static void main(String[] args) {
		Eagle black = new Eagle();
		System.out.println("Wings: " + black.numberwing);
		
		black.move();
		

	}

}
