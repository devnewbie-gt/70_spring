package polymorphism02;

public class LgTV implements TV {
	public LgTV() {
		System.out.println(">> LgTV 객체 생성");
	}
	@Override
	public void powerOn() {
		System.out.println("LgTV - 전원 ON");
	}
	@Override
	public void powerOff() {
		System.out.println("LgTV - 전원 OFF");
	}
	@Override
	public void volumnUp() {
		System.out.println("LgTV - 소리크게~~");
	}
	@Override
	public void volumnDown() {
		System.out.println("LgTV - 소리작게~~");
	}
	
	//-------------------------------------------
		public void initMethod() {	// 이름은 임의로 설정해도 무관
			System.out.println("LgTV - initMethod() 실행~~");
		}
		
		public void destroyMethod() {	// 이름은 임의로 설정해도 무관
			System.out.println("LgTV - destroyMethod() 실행~~");
		}
}
