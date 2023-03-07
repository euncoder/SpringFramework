import mul.cam.a.dto.BbsDto;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int b = sum(1,2);
		String c = concat("i ","love you");
		
//		System.out.println(b);
//		System.out.println(c);
		
		BbsDto a = new BbsDto();
		
		//내가 생성한 인스턴스 내부의 변수에 값을 넣는 행위라고 볼수있다.
		a.setSeq(0);
		a.setId("euna");
		a.setRef(0);
		a.setStep(0);
		a.setDepth(0);
		a.setTitle("aaaaa");
		a.setWdate("20220501");
		a.setDel(0);
		a.setReadcount(0);
		
		System.out.println(test(a));
		
		
	}
	
	static int sum(int a, int b) {
		return a+b;
	}
	
	static String concat(String str1,String str2) {
		return str1 + str2;
	}
	
	static BbsDto test(BbsDto dto) {
		
		System.out.println("함수 내부"+dto.toString());
		return dto;
	}

}
