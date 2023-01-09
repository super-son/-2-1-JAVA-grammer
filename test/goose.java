package test;
public class goose extends Bird {

	// Bird.java의 클래스부분을 public을 안붙이면(생략 시 디폴트) 다른 패키지들의 새들은 에러가 나지만 같은패키지이기때문에 에러가 안나!
	// class만 그런것이 아니고 public int = numberwing 부분에서도 public을 때면 다른 패키지들에서는 다 에러가 난다~ 
	
	public static void main(String[] args) {
		goose black = new goose();
		System.out.println("Wings: " + black.numberwing);
		black.move();
	}
}
 