package test;

public class Bird {
	
	public int numberwing=2;
	public int numberleg;
	
	public void move(){
		System.out.println("fly");
	}

	public Bird() {
		// �̰� �ț��ٸ� �ؿ� int�� ������ ���������� ��Ƹ����� int�� �ֶ� �Ѵ� �����ٸ� �ڵ������Ǵ� �־�. �Ƹ� java���� ����������
	}
	
	public Bird(int w, int t) {
		numberwing = w;
		numberleg = t;
	}
	
	// �޼ҵ带 �ϳ� ������. �� ������ ���� change���ִ� ���.
	// static���� �������� main���� ��밡��
	public static void change(int x, int y) {
		System.out.println("[Before] x:" + x + ", y:" + y);
		int temp = x;
		x = y;
		y = temp;
		System.out.println("[After] x:" + x + ", y:" + y);
	}
	
	// int���� Bird�� ��ƺ���? .! �̷��� ������ ����� �� ������ ������ ������� ��ȯ�� �ȴܴ�.
	public static void change2(Bird x, Bird y) {
		System.out.println("[Before] b1.numberwing:" + x.numberwing + ", b2.numberwing :" + y.numberwing);
		int temp = x.numberwing;
		x.numberwing = y.numberwing;
		y.numberwing = temp; // �� �ڵ�ó�� ���� �̷��� ����� ������ �ٲ��
		System.out.println("[After] b1.numberwing:" + x.numberwing + ", b2.numberwing:" + y.numberwing);
	}
	
	public static void main(String[] args) {
		
		Bird sparrow = new Bird();
		sparrow.move();
		System.out.println(sparrow.numberwing);
		
		Bird sparrow2 = new Bird(2,4);
		sparrow2.move();
		System.out.println(sparrow2.numberwing);
		System.out.println(sparrow2.numberleg);
	
		// �޼ҵ� ����
		int x =3;
		int y =4;
		change(x,y);
		System.out.println("x:" + x + " y:" + y); // change�� �ߴµ� �������ΰ��� �ٲ����ʾҳ�?
		// change��� �������� Heap -> main�� �ִ����� �����ؼ� ���⶧���� �ٽ� main���� ���ƿ��� �ٲ��� �ʾҴ�����
		// �׷� ��¥ �ٲٰ�ʹٸ�? Heap ������ �� ����ִ°���.  �� �ּ����� ������� ����
		
		Bird b1 = new Bird(0,0);
		Bird b2 = new Bird(4,4);
		change2(b1,b2);
		System.out.println("b1.numberwing:" + b1.numberwing + ", b2.numberwing:" + b2.numberwing);
	}

}
