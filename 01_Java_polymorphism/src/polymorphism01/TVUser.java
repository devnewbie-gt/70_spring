package polymorphism01;

public class TVUser {
	public static void main(String[] args) {
//		LgTV tv = new LgTV();
//		tv.on();
//		tv.soundUp();
//		tv.soundDown();
//		tv.off();
		
		SamsungTV tv = new SamsungTV();
		tv.powerOn();
		tv.volumnUp();
		tv.volumnDown();
		tv.powerOff();
	}
}
