package polymorphism02;

public class SamsungTV implements TV {
	
	@Override
	public void powerOn() {
		System.out.println("SamsungTV - 전원 ON");
	}
	@Override
	public void powerOff() {
		System.out.println("SamsungTV - 전원 OFF");
	}
	@Override
	public void volumnUp() {
		System.out.println("SamsungTV - 소리 크게");
	}
	@Override
	public void volumnDown() {
		System.out.println("SamsungTV - 소리 작게");
	}
}
