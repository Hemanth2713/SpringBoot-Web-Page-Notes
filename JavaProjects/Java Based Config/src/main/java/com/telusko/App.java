package com.telusko;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.telusko.config.AppConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class App {
	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		// Using AnnotationConfigApplicationContext for Java-based configuration.
		Desktop dt1 = context.getBean(Desktop.class); // Getting the Desktop bean using general getBean method.
		dt1.compile();

		// Desktop dt2 = context.getBean("comdesk", Desktop.class); // Calling a bean by specifying the bean name.

		Desktop dt2 = context.getBean(Desktop.class);
        /*
        We are calling getBean() twice. In the case of singleton scope (default in Spring),
        even though we request the bean twice(creating 2 objects), Spring will return the same object for both calls.
        For example, if we pass a value (e.g., 101) into the object, the object will be created once,
        but the method will be called twice, printing the same value two times.

        To avoid this issue or create multiple instances, we need to specify the bean's scope
        (e.g., using @Scope annotation) when defining the bean.
        if we mention the scope as prototype it will create 2 objects
        EX:If we pass a 2 different values here it will print 2 values.
        */

		dt2.compile();
		
		
		
		
		
		
		
		

		
		
// 	Alien obj1 = context.getBean(Alien.class);
// 	obj1.setAge(21);
//	System.out.println(obj1.getAge());
//   	obj1.code();
////    	
////    	Alien obj2 = (Alien) context.getBean("alien1");
////    	System.out.println(obj2.age);
////    	//obj2.code();
//   
//    	
// 
////        Computer com=	context.getBean( Computer.class);
//    	
//    	
// 
////    	Desktop obj=(Desktop)context.getBean("com2",Desktop.class);
////	 Desktop obj= context.getBean( Desktop.class);
	}

	@Component
	public static class rand2 {
		private int value;

		public rand2() {
			this.value = 100; // Initial value
		}

		// Method to print the current value
		@Bean
		public void printValue() {
			System.out.println("Desktop Value: " + value);
		}

		// Setter method for value
		public void setValue(int value) {
			this.value = value;
		}
	}
}
