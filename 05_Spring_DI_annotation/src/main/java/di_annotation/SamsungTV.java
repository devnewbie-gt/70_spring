package di_annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// @Component : 객체 생성
// @Component // samsungTV 명칭으로 객체를 생성(첫 글자를 소문자로 구성)
@Component("tv") // tv라는 명칭으로 객체 생성
public class SamsungTV implements TV {
	@Autowired // 동일한 데이터 타입을 찾아서 주입
	@Qualifier("apple")	// qualifier는 autowired와 함께 사용
	private Speaker speaker;
	private int price;
	private int width;
	private int height;
	
	public SamsungTV() {
		System.out.println(">> SamsungTV 객체 생성");
	}
	
	public SamsungTV(Speaker speaker) {
		System.out.println(">> SamsungTV(speaker) 객체 생성");
		this.speaker = speaker;
	}
	
	public SamsungTV(Speaker speaker, int price) {
		System.out.println(">> SamsungTV(speaker) 객체 생성");
		this.speaker = speaker;
		this.price = price;
	}
	
	public SamsungTV(Speaker speaker, int width, int height) {
		System.out.println(">> SamsungTV(speaker) 객체 생성");
		this.speaker = speaker;
		this.width = width;
		this.height = height;
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

	@Override
	public String toString() {
		return "SamsungTV [speaker=" + speaker + ", price=" + price + ", width=" + width + ", height=" + height + "]";
	}

	//=============================================================
	
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
