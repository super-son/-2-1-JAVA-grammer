package test;
public class goose extends Bird {

	// Bird.java�� Ŭ�����κ��� public�� �Ⱥ��̸�(���� �� ����Ʈ) �ٸ� ��Ű������ ������ ������ ������ ������Ű���̱⶧���� ������ �ȳ�!
	// class�� �׷����� �ƴϰ� public int = numberwing �κп����� public�� ���� �ٸ� ��Ű���鿡���� �� ������ ����~ 
	
	public static void main(String[] args) {
		goose black = new goose();
		System.out.println("Wings: " + black.numberwing);
		black.move();
	}
}
 