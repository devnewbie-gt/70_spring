package polymorphism02;

public class TVUser {
	public static void main(String[] args) {
		// 삼성 TV 사용
		//TV tv = new SamsungTV();
		
		// LgTV 사용
		//LgTV tv = new LgTV();
		TV tv = new LgTV();
		tv.powerOn();
		tv.volumnUp();
		tv.volumnDown();
		tv.powerOff();
	}
}
