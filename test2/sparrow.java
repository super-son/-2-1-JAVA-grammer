package test2;

import test.Bird;

public class sparrow extends Bird{ // ����Ѵٰ� ����
	public int numberwing = 92; // ������ ����� �������̵�	
	public void move() { // �θ� �״�� �Ⱦ��� �ڱⰡ ������ �߳� -> �������̵� (�޼ҵ� �����)
		System.out.println("slowly fly");
	}
	
	public static void main(String[] args) {
		sparrow sp = new sparrow();

		System.out.println("wings: "+ sp.numberwing);
		sp.move();

	}

}
