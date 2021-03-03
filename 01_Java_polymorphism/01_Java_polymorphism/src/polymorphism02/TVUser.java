package polymorphism02;

public class TVUser {

	public static void main(String[] args) {
		//삼성TV 사용
		//SamsungTV tv = new SamsungTV();
		//TV tv = new SamsungTV();
		
		//LG TV 사용 형태로 변경
		//LgTV tv = new LgTV();
		TV tv = new LgTV();
		tv.powerOn();
		tv.volumnUp();
		tv.volumnDown();
		tv.powerOff();
		

	}
}








