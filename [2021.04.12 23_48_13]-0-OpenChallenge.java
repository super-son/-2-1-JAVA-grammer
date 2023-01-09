import java.util.Scanner;

// 최대한 주석을 이용하여 작성한 코드에 대한 설명을 첨부하였습니다.
// p.397의 주어진 코드를 참고하였습니다.
public class OpenChallenge {
	String readString() {
	StringBuffer sb = new StringBuffer(); // 내부 버퍼 크기를 자동조절 하는 스트링버퍼를 생성합니다.
	Scanner scanner = new Scanner(System.in);
	while(true) {
		String line = scanner.nextLine();
		if(line.length() == 1 && line.charAt(0) == ';')  // 사용자가 ';'만 존재하는 라인을 입력한다면 입력을 종료시킵니다.
			break;
		sb.append(line); // 입력을 통해 읽은 line을 스트링버퍼(sb)에 추가합니다.
	}
	scanner.close(); // scanner 객체를 닫아줍니다!
	return sb.toString(); // 스트링버퍼(sb)의 문자열을 스트링으로 리턴합니다.
}
		
	// histogram 메소드를 정의합니다.
	public void histogram() {
		int text[] = new int[26]; // A부터 Z까지 총 26개의 알파벳이 존재하므로 크기가 26인 배열 text를 만듭니다. text[0]에는 'A'의 개수, text[1]에는 'B'의 개수 .. 가 들어갈 예정입니다.
		String uppertext = readString().toUpperCase(); // 문자열을 모두 대문자로 변환시킵니다.
		char j; 
		
		// text 배열에 정보를 저장합니다.
		// 이중 for문을 이용하여 j가 'A'일때, uppertext의 내용을 확인하며 'A'가 나올때마다 text[0]이 1씩 증가합니다.
		// 마찬가지로 j가 'B'일때, uppertext의 내용을 확인하며 'B'가 나올때마다 text[1]이 1씩 증가합니다. 이 작업을 j가 'Z'일때까지 반복한 후 종료됩니다.
		for(j ='A'; j <= 'Z'; j++) { 
			for(int i = 0; i<uppertext.length(); i++) { 
				if(uppertext.charAt(i)==j) { 
					int text_index = (int)j - 65; // j='A'일때 (int)j = 65이므로 65를 빼주면 text_index의 값이 0이 됩니다. 즉 text[0]에는 'A'의 개수의 정보를 담기위함입니다.
					text[text_index] += 1; 
				}
			}
		}
		
		// 위에서 저장된 정보를 이용하여 histogram을 그립니다.
		for(j = 'A'; j <= 'Z'; j++) {
			int text_index = (int)j - 65;
			System.out.print(j);
			for(int i = 0; i<text[text_index] ; i++) 
				if ((i+1) % 5 == 0) // i가 0부터 시작하기때문에 i%5가 아닌 (i+1)%5가 올바른 코드입니다.
					System.out.print("+"); // i가 4가 되는 순간 "+"를 출력하게되고 i가 9, 14, 19.. 5n-1이 된다면 그 순간도 "+"를 출력합니다. 
				else
					System.out.print("-"); // 만약 A에 관한 정보인 text[0]에 6이 저장되어 있다고 가정하고, 이를 출력시킨다면 "----+-"가 나오게 될것입니다.
			System.out.println(); 
		}
	}
	
	public static void main(String[] args) {
		System.out.println("영문 텍스트를 입력하고 세미콜론을 입력하세요.");
		OpenChallenge alphabet = new OpenChallenge (); 
		alphabet.histogram(); // alphabet이라는 객체를 생성한 후 histogram 메소드를 실행시킵니다.
	}
}
