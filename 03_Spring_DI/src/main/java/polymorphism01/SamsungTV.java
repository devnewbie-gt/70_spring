package polymorphism01;

public class SamsungTV implements TV {
	private SonySpeaker speaker;
	
	public SamsungTV() {
		System.out.println(">> SamsungTV 객체 생성");
		// 필드 SonySpeaker 객체 주입
		speaker = new SonySpeaker();
	}
	public void powerOn() {
		System.out.println("SamsungTV - 전원 ON");
	}
	public void powerOff() {
		System.out.println("SamsungTV - 전원 OFF");
	}
	public void volumnUp() {
		System.out.println("SamsungTV - 소리크게");
		speaker.volumeUp();
	}
	public void volumnDown() {
		System.out.println("SamsungTV - 소리작게");
		speaker.volumeDown();
	}
	
	//-------------------------------------------
	public void initMethod() {	// 이름은 임의로 설정해도 무관
		System.out.println("SamsungTV - initMethod() 실행~~");
	}
	
	public void destroyMethod() {	// 이름은 임의로 설정해도 무관
		System.out.println("SamsungTV - destroyMethod() 실행~~");
	}
	
}
