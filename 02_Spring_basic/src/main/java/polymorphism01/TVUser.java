package polymorphism01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
		
		System.out.println("--- 스프링 컨테이너 구동 전 ---");
		// 1. 스프링 컨테이너 구동(스프링 설정 파일을 읽어서 구동)
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		System.out.println("--- 스프링 컨테이너 구동 후 ---");
		// 2. 스프링 컨테이너 사용 : 생성된 객체 요청(lookup, 탐색하여 전달)
		System.out.println("--- tv 요청 사용 ---");
		TV tv = (TV)factory.getBean("tv");	// "tv"는 applicationContext.xml에 설정한 id에 해당
		tv.powerOn();
		tv.volumnUp();
		tv.volumnDown();
		tv.powerOff();
		System.out.println("tv : " + tv);
		
		System.out.println("--- tv 요청 한 번 더 ---");
		tv = (TV)factory.getBean("tv");	// "tv"는 applicationContext.xml에 설정한 id에 해당
		tv.powerOn();
		tv.volumnUp();
		tv.volumnDown();
		tv.powerOff();
		System.out.println("tv : " + tv);	// 같은 객체가 호출되어 같은 주소 값을 출력
		// ==> Spring은 기본설정으로 singleton처럼 하나의 객체를 이용 
		
		System.out.println("--- 스프링 컨테이너 구동 종료 처리 ---");
		// 3. 스프링 컨테이너 종료(close)
		factory.close();
		
	}
}
