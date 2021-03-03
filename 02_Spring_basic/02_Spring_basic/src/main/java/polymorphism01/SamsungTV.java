package polymorphism01;

public class SamsungTV implements TV {
	public SamsungTV() {
		System.out.println(">> SamsungTV 객체 생성");
	}
	public void powerOn() {
		System.out.println("SamsungTV - 전원 ON");
	}
	public void powerOff() {
		System.out.println("SamsungTV - 전원 OFF");
	}
	public void volumnUp() {
		System.out.println("SamsungTV - 소리크게");
	}
	public void volumnDown() {
		System.out.println("SamsungTV - 소리작게");
	}
	
}
