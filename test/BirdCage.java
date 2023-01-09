package test;

import test2.Eagle;
import test2.penguin;
import test2.sparrow;

public class BirdCage {

	public static void main(String[] args) {
		Bird[] flocks = new Bird[] { new Bird(), new Eagle(), new sparrow(), new penguin() }; //구식적인 방법으로 객체 초기화. 숨어있는 기본생성자
		// 자식들을 Bird의 배열형에 할당하고 있는거지.
		/////////////////////////////////////////
		// Heap 공간에 flocks의 주소 [1,2,3,4,5] 5: length정보 이런식으로 만들어지겟지.  그리고 이 1, 2, 3, 4는 각각 또 Heap에 만들어진 bird 공간 eagle공간, sparrow 공간, penguin공간을
		// 가르키는 역할을 하고있어.
		// 요것들로 해석할수 있겠다.
		// flocks[0]: Bird, flocks[1]: Eagle..
		
		// 이렇게 썻으면 같은 뜻이겠네
		// Bird x = new Eagle(); up-casting
		// Bird y = new sparrow();
		// Bird z = new penguin();
		// .....
		
		// 업 캐스팅과 다운캐스팅 문법
		
//		Eagle e = new Bird(); //down casting. 명시적으로 (Eagle) 씌우면 강제 변환하겠지만.. 얘는 실제로는 동작을 잘 못하겠지.
//		int x1 = 300L; // down-casting (int)를 씌우면 강제 형변환
//		long x2 = 300; // 큰 곳으로 들어가는 데는 에러없이 들어기지 up-casting

		// for each 문. enmuerate 방식~)
		for (Bird b:flocks) { 
			System.out.println("wings: "+ b.numberwing);
			b.move();
		// 메소드 멤버 : move 메소드는 자식버전의 메소드가 왔는데, 
		// 데이터 멤버 : 데이터는 참조중인 핸들 래퍼런스 즉 부모의 데이터가 동작하고 있네.
		}
		
		Bird[] bs = new Bird[10]; // 주소값만 10개 만든거야 새 10마리를 만든게 아니고
		for (Bird i:bs) {
			System.out.println("Bird i:" + i); // 당근 찍어보면 null의 값이 나오지
			i = new Bird(2,22);
			System.out.println("Bird i의 다리 수 " + i.numberleg); // 새 100마리가 만들어진거야
		}
	
	
	}

}
