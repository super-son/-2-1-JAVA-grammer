import java.util.Scanner;
public class algorithm {

	static int[] arr() {
		int temp[] = new int[4];
		for (int i =0; i<temp.length; i++)
			temp[i] = i;
		return temp;
	}
	
	public static void main(String[] args) {
	
	    // 3의 배수 더해줄게
		int sum=0;
		for(int n=1 ; n<=100 ; n++) {
			if (n%3==0) sum=sum+n;
		}
		System.out.println(sum);	
		
		// 입력값 중 가장 큰 수 뽑아내기
		Scanner scanner = new Scanner(System.in);
//		int ary[]=new int[5];
//		int max = 0;
//		for (int n=0 ; n<5 ; n++) {
//			System.out.print(n+1+"번째 숫자를 입력주세요:");
//			ary[n]=scanner.nextInt();
//			if (ary[n] > max)
//				max = ary[n];
//		}
//		System.out.println("가장 큰 수는 "+max+" 입니다");
//		scanner.close();
			
		// 가위바위보
		System.out.println("가위바위보 게임입니다. 가위,바위,보 중에서 입력하세요");
		System.out.print("철수>>");
		String cco = scanner.next();
		System.out.print("영희>>");
		String yco = scanner.next();
		
		if (cco.equals("바위"))
			switch (yco){
			case "바위": System.out.println("비겼습니다."); break;
			case "보": System.out.println("영희가 이겼습니다."); break;
			case "가위": System.out.println("철수가 이겼습니다."); break;
			}
		
		if (cco.equals("가위"))
			switch (yco){
			case "가위": System.out.println("비겼습니다."); break;
			case "바위": System.out.println("영희가 이겼습니다."); break;
			case "보": System.out.println("철수가 이겼습니다."); break;
			}
			
		if (cco.equals("보"))
			switch (yco){
			case "보": System.out.println("비겼습니다."); break;
			case "가위": System.out.println("영희가 이겼습니다."); break;
			case "바위": System.out.println("철수가 이겼습니다."); break;
		}
		
		// 계단식 별 방법 1
		for (int i=0; i<5; i++) {
			for (int j=5-i; j>0; j--) {
			System.out.print("*");
			}
			System.out.println();
		}
		
		// 계단식 별 방법 2
		int i = 0;
		while (i<5) {
			for (int j=5-i; j>0; j--) {
			System.out.print("*");
			}
		System.out.println();
		i++;
	
	}

}
}

