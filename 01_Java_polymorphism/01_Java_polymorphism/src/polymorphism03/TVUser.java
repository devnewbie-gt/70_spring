package polymorphism03;

public class TVUser {

	public static void main(String[] args) {
		BeanFactory factory = new BeanFactory();
		
		//삼성TV 사용
		//TV tv = (TV)factory.getBean("samsung");
		
		for (int i = 0; i < args.length; i++) {
			TV tv = (TV)factory.getBean(args[i]);
			tv.powerOn();
			tv.volumnUp();
			tv.volumnDown();
			tv.powerOff();
		}

	}

}
