package test2;

import test.Bird;

public class sparrow extends Bird{ // 상속한다고 선언
	public int numberwing = 92; // 데이터 멤버의 오버라이딩	
	public void move() { // 부모껄 그대로 안쓰고 자기가 재정의 했네 -> 오버라이딩 (메소드 멤버의)
		System.out.println("slowly fly");
	}
	
	public static void main(String[] args) {
		sparrow sp = new sparrow();

		System.out.println("wings: "+ sp.numberwing);
		sp.move();

	}

}
