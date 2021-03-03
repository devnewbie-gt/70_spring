package di_collection;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionBeanClient {
	public static void main(String[] args) {
		System.out.println("---- 컨테이너 구동 전 ----");
		// 1. 컨테이너 구동
		GenericXmlApplicationContext factory 
			= new GenericXmlApplicationContext("applicationContext.xml");	// 별도의 디렉토리를 구성한 경우, 추가적인 경로 작성 필요
		
		System.out.println("---- 컨테이너 구동 후 ----");
		// 2. 스프링 컨테이너 사용
		CollectionBean bean = (CollectionBean)factory.getBean("collectionBean");
		System.out.println("---- List 타입 ----");
		System.out.println("bean : " + bean);
		List<String> addressList = bean.getAddressList();
		for (String address : addressList) {
			System.out.println("address : " + address);
		}
		
		System.out.println("---- Set 타입 ----");
		Set<String> addressSet = bean.getAddressSet();
		System.out.println("addressSet : " + addressSet);
		
		System.out.println("---- Map 타입 ----");
		Map<String, String> addressMap = bean.getAddressMap();
		System.out.println("addressMap : " + addressMap);
		
		System.out.println("---- Properties 타입 ----");
		Properties addressProp = bean.getAddressProp();
		System.out.println("addressProp : " + addressProp);
		
		System.out.println("---- 컨테이너 종료 ----");
		factory.close();
		
	}
}
