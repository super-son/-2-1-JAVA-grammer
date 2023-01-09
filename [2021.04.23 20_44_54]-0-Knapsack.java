// 201811958 손휘준, 중간고사과제 코드입니다.
// 최대한 주석을 이용하여 작성한 코드에 대한 설명을 첨부하였습니다.
// 주석은 모두 해당코드의 윗쪽에 적었습니다.
public class Knapsack {

	// 물품들의 이름을 나타내는 배열을 표의 순서대로 만듭니다.
	public static String[] name = {"휴대폰","라면","쌀","빵","우의","커피","생수","오이","침낭","라디오","손전등"};
	// 물품들의 무게를 나타내는 배열을 표의 순서대로 만듭니다.
	public static int[] W = {1,11,12,5,6,3,7,4,16,2,2}; 
	// 물품들의 만족도를 나타내는 배열을 표의 순서대로 만듭니다.
	public static int[] P = {16,35,36,20,21,10,12,16,46,12,28};
	
	// 물품의 목록들이 11개 뿐만아니라 더 추가되거나 삭제될 경우도 고려하여 코드를 유동적으로 조절하기 위해 만든 변수입니다. 
	public static int count_num = name.length; 
	// 배낭의 제한무게가 40이 아닐 경우도 고려하여 코드를 유동적으로 조절하기 위해 만든 변수입니다.
	public static int maximum_weight = 40;
	
	
	// 이진수 열한자리를 맞추기위해, 필요한 "0"의 개수를 반환하는 메소드를 정의하였습니다. 
	// 예를들면 101이라는 이진수를 받으면 앞에 8개의 "0"을 붙여 00000000101을 만들기 위해 "0" 8개를 반환해주는 작업입니다.
	public static String plus_zero(String x) {
		int plus = count_num - x.length(); 
		String count_zero = "0";	
		// x의 길이가 열한자리라면 "0"을 채울 필요가 없기 때문에 예외처리를 하였습니다.
			if (x.length() == count_num) count_zero = "";		
			else {
				for(int k=0; k<plus-1; k++) {
				count_zero +="0"; 
				} 
			}
		return count_zero;
	}

	public static void main(String[] args) {
		
		// 가장 큰 만족도의 합계를 구하기 위한 변수입니다.
		int max_sum_p = 0;
		// Math.pow(2, count_num)는 2048로 2048개의 모든 경우의 수를 고려하기 위함입니다. 
		for(int index1=0; index1<Math.pow(2, count_num); index1++) { 
			// index1을 이진수로 변환한 값을 S_index라는 변수로 저장하였습니다.
			String S_index = Integer.toBinaryString(index1);
			// 예를들어, 이진수로 변환시켰을때 101과 같은 형태로 수가 나오는데 원하는 형태는 00000000101의 형태이므로 위에서 정의한 메소드를 사용하였습니다. 
			// 00000000101 = 00000000 + 101
			String a = plus_zero(S_index) + S_index;
			
			// 무게의 합계를 구하기 위한 변수입니다.
			int sum_w = 0;
			// 여기서 count_num는 11이고 count_num을 사용한 이유는 11개의 모든 물품에 대한 정보를 얻기 위함입니다.
			for(int index2=0; index2 < count_num; index2++) {			
				// a.charAt(index2)-48을 사용한 이유는 아스키코드를 고려한 것으로 0과 1의 값을 도출하기 위함입니다.
				// 11자리 2진법과 무게를 나타내는 W를 배열의 순서대로 곱하여 모두 더하면 총 무게가 그대로 도출됩니다.	
				// 예를들어, a가 00000000101라면 0인 자리는 해당 물품을 가져가지 않는다는 뜻으로, 무게와 곱해도 0으로 모두 무게합에 반영되지않고 
				//                           1인 자리는 W[8]인 16과 W[10]인 2으로 각각 곱해진 후 이들을 더한값 18을 얻게됩니다.
				sum_w = sum_w + (a.charAt(index2)-48) * W[index2]; 			
			}
			
			// 배낭의 무게 제한이 40kg이기 때문에 무게의 합계가 40을 넘는다면 다음순서의 index1으로 넘어갑니다.
			if (sum_w>maximum_weight) continue;
			// 만족도의 합계를 구하기 위한 변수입니다.
			int sum_p = 0;
			// sum_w를 구하는 방법과 똑같이 sum_p를 구합니다. 
			for(int index3=0; index3 < count_num; index3++) {				
				sum_p = sum_p + (a.charAt(index3)-48) * P[index3]; 				
			}
			// sum_p의 최댓값 즉, 가장 큰 만족도의 합계를 구하기 위함입니다.
			if (sum_p > max_sum_p)
				max_sum_p = sum_p;
		}
		
		// 배낭을 최적으로 꾸리는 방법이 한 개 이상일 경우, 이 경우들을 모두 출력하기 위해 작성하였습니다.
		// 위의 for문으로 구한 가장 큰 만족도의 합계를 이용합니다.
		// 아래의 if (sum_p==max_sum_p)... 전까지의 코드는 위의 for문의 코드와 동일한 구성이므로 주석을 생략합니다.
		for(int index4=0; index4<Math.pow(2, count_num); index4++) { 
			String S_index = Integer.toBinaryString(index4);
			String a = plus_zero(S_index) + S_index; 
			int sum_w = 0;
			for(int index5=0; index5 < count_num; index5++) {				
				sum_w = sum_w + (a.charAt(index5)-48) * W[index5]; 			
			}
			if (sum_w>maximum_weight) continue;
			int sum_p = 0;
			for(int index6=0; index6 < count_num; index6++) {				
				sum_p = sum_p + (a.charAt(index6)-48) * P[index6]; 			
			}
			// "만족도의 합계가 가장 큰 만족도의 합계와 같다면"의 조건을 사용함으로써 만약 최적해가 여러가지라면 모두 확인할 수 있습니다.
			if (sum_p==max_sum_p) {
				// 배낭에 담길 물품들의 이름을 저장할 변수입니다.
				String name_get = "";
				for(int index7=0; index7 < count_num; index7++) {	
					// 가져가는 물품은 1로 표기하므로 a.charAt(index7)-48 == 1의 조건을 통해 가져가는 물품들만 확인할 수 있습니다.
					if (a.charAt(index7)-48 == 1) {
						// 가져가는 물품들의 이름을 name_get에 저장시킵니다.
						name_get += name[index7]+" ";
					}
				}
				// 배낭에 담길 물품들의 목록, 배낭의 무게에 추가적으로 물품들의 만족도 합까지 출력합니다.
				System.out.println("배낭에 담길 물품들의 목록 : " + name_get);
				System.out.println("배낭의 무게 : " + sum_w + "kg");
				System.out.println("물품들의 만족도 합 : " + sum_p);
			}
		}
	}
}




