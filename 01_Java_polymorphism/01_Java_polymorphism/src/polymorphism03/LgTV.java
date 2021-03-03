package polymorphism03;

public class LgTV implements TV {
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
}
