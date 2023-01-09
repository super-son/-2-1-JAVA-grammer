import java.util.Scanner;

// �ִ��� �ּ��� �̿��Ͽ� �ۼ��� �ڵ忡 ���� ������ ÷���Ͽ����ϴ�.
// p.397�� �־��� �ڵ带 �����Ͽ����ϴ�.
public class OpenChallenge {
	String readString() {
	StringBuffer sb = new StringBuffer(); // ���� ���� ũ�⸦ �ڵ����� �ϴ� ��Ʈ�����۸� �����մϴ�.
	Scanner scanner = new Scanner(System.in);
	while(true) {
		String line = scanner.nextLine();
		if(line.length() == 1 && line.charAt(0) == ';')  // ����ڰ� ';'�� �����ϴ� ������ �Է��Ѵٸ� �Է��� �����ŵ�ϴ�.
			break;
		sb.append(line); // �Է��� ���� ���� line�� ��Ʈ������(sb)�� �߰��մϴ�.
	}
	scanner.close(); // scanner ��ü�� �ݾ��ݴϴ�!
	return sb.toString(); // ��Ʈ������(sb)�� ���ڿ��� ��Ʈ������ �����մϴ�.
}
		
	// histogram �޼ҵ带 �����մϴ�.
	public void histogram() {
		int text[] = new int[26]; // A���� Z���� �� 26���� ���ĺ��� �����ϹǷ� ũ�Ⱑ 26�� �迭 text�� ����ϴ�. text[0]���� 'A'�� ����, text[1]���� 'B'�� ���� .. �� �� �����Դϴ�.
		String uppertext = readString().toUpperCase(); // ���ڿ��� ��� �빮�ڷ� ��ȯ��ŵ�ϴ�.
		char j; 
		
		// text �迭�� ������ �����մϴ�.
		// ���� for���� �̿��Ͽ� j�� 'A'�϶�, uppertext�� ������ Ȯ���ϸ� 'A'�� ���ö����� text[0]�� 1�� �����մϴ�.
		// ���������� j�� 'B'�϶�, uppertext�� ������ Ȯ���ϸ� 'B'�� ���ö����� text[1]�� 1�� �����մϴ�. �� �۾��� j�� 'Z'�϶����� �ݺ��� �� ����˴ϴ�.
		for(j ='A'; j <= 'Z'; j++) { 
			for(int i = 0; i<uppertext.length(); i++) { 
				if(uppertext.charAt(i)==j) { 
					int text_index = (int)j - 65; // j='A'�϶� (int)j = 65�̹Ƿ� 65�� ���ָ� text_index�� ���� 0�� �˴ϴ�. �� text[0]���� 'A'�� ������ ������ ��������Դϴ�.
					text[text_index] += 1; 
				}
			}
		}
		
		// ������ ����� ������ �̿��Ͽ� histogram�� �׸��ϴ�.
		for(j = 'A'; j <= 'Z'; j++) {
			int text_index = (int)j - 65;
			System.out.print(j);
			for(int i = 0; i<text[text_index] ; i++) 
				if ((i+1) % 5 == 0) // i�� 0���� �����ϱ⶧���� i%5�� �ƴ� (i+1)%5�� �ùٸ� �ڵ��Դϴ�.
					System.out.print("+"); // i�� 4�� �Ǵ� ���� "+"�� ����ϰԵǰ� i�� 9, 14, 19.. 5n-1�� �ȴٸ� �� ������ "+"�� ����մϴ�. 
				else
					System.out.print("-"); // ���� A�� ���� ������ text[0]�� 6�� ����Ǿ� �ִٰ� �����ϰ�, �̸� ��½�Ų�ٸ� "----+-"�� ������ �ɰ��Դϴ�.
			System.out.println(); 
		}
	}
	
	public static void main(String[] args) {
		System.out.println("���� �ؽ�Ʈ�� �Է��ϰ� �����ݷ��� �Է��ϼ���.");
		OpenChallenge alphabet = new OpenChallenge (); 
		alphabet.histogram(); // alphabet�̶�� ��ü�� ������ �� histogram �޼ҵ带 �����ŵ�ϴ�.
	}
}
