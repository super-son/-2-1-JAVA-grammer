package test;

public class Bird {
	
	public int numberwing=2;
	public int numberleg;
	
	public void move(){
		System.out.println("fly");
	}

	public Bird() {
		// 이건 안썻다면 밑에 int를 포함한 생성자한테 잡아먹히고 int랑 애랑 둘다 없엇다면 자동생성되는 애야. 아른 java에서 참조중이지
	}
	
	public Bird(int w, int t) {
		numberwing = w;
		numberleg = t;
	}
	
	// 메소드를 하나 만들어보자. 두 변수의 값을 change해주는 기능.
	// static으로 만들어줘야 main에서 사용가능
	public static void change(int x, int y) {
		System.out.println("[Before] x:" + x + ", y:" + y);
		int temp = x;
		x = y;
		y = temp;
		System.out.println("[After] x:" + x + ", y:" + y);
	}
	
	// int말고 Bird랑 놀아볼까? .! 이렇게 데이터 멤버를 콕 찝어줘야 실제로 멤버값이 변환이 된단다.
	public static void change2(Bird x, Bird y) {
		System.out.println("[Before] b1.numberwing:" + x.numberwing + ", b2.numberwing :" + y.numberwing);
		int temp = x.numberwing;
		x.numberwing = y.numberwing;
		y.numberwing = temp; // 위 코드처럼 말고 이렇게 해줘야 찐으로 바뀐다
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
	
		// 메소드 실험
		int x =3;
		int y =4;
		change(x,y);
		System.out.println("x:" + x + " y:" + y); // change를 했는데 원론적인것은 바뀌지않았네?
		// change라는 공간에서 Heap -> main에 있던값을 복사해서 썻기때문에 다시 main으로 돌아오면 바뀌지 않았던거지
		// 그럼 진짜 바꾸고싶다면? Heap 공간을 딱 찝어주는거지.  위 주석으로 써놓은거 참고
		
		Bird b1 = new Bird(0,0);
		Bird b2 = new Bird(4,4);
		change2(b1,b2);
		System.out.println("b1.numberwing:" + b1.numberwing + ", b2.numberwing:" + b2.numberwing);
	}

}
