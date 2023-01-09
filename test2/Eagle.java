package test2; // 패키지 선언

import test.Bird; // 부모 패키지 import

public class Eagle extends Bird{ //다른 패키지 폴더라서 그런지 못찾으니까 자동으로 import 해주네

	// Eagle is a Bird. (Is_A relationship). 다른 새들도 마찬가지
	
	// 만약 Bird.java에 int형 생성자만 있었더라면 
//	public Eagle(){
//		super(2,4); // 이렇게 찝어서 받아주는 코드를 따로 작성해야해!
//	}
	public static void main(String[] args) {
		Eagle black = new Eagle();
		System.out.println("Wings: " + black.numberwing);
		
		black.move();
		

	}

}
