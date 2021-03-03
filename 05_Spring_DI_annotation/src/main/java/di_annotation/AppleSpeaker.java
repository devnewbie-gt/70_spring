package di_annotation;

import org.springframework.stereotype.Component;

@Component("apple")
public class AppleSpeaker implements Speaker{
	
	public AppleSpeaker() {
		System.out.println("--->> AppleSpeaker 생성");
	}
	
	// Annotation을 명시해야 interface의 변화점을 알 수 있음
	@Override
	public void volumeUp() {
		System.out.println("--->> AppleSpeaker 소리 크게");
	}
	
	@Override
	public void volumeDown() {
		System.out.println("--->> AppleSpeaker 소리 작게");
	}
	
}
