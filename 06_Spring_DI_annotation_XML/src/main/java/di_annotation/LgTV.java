package di_annotation;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

// LgTV 객체를 생성하고, 스피커도 주입하여 TV 사용
@Component("tv2") // default name : lgTV(첫 글자가 소문자인 클래스명으로 설정)
public class LgTV implements TV {
	@Resource(name = "apple")
	private Speaker speaker;
	private int price;
	
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
		speaker.volumeUp();
	}
	@Override
	public void volumnDown() {
		System.out.println("LgTV - 소리작게~~");
		speaker.volumeDown();
	}
	
	//-------------------------------------------
	public void initMethod() {	// 이름은 임의로 설정해도 무관
		System.out.println("LgTV - initMethod() 실행~~");
	}
	
	public void destroyMethod() {	// 이름은 임의로 설정해도 무관
		System.out.println("LgTV - destroyMethod() 실행~~");
	}
	//===========================================
	public Speaker getSpeaker() {
		return speaker;
	}
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "LgTV [speaker=" + speaker + ", price=" + price + "]";
	}
	
}
