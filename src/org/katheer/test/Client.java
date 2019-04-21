package org.katheer.test;

import org.katheer.bean.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        /*
        Method Injection Application:
        -----------------------------

        Problem:
        --------
        If we inject a prototype bean to prototype bean, the injected bean
        will not be created for each call. Because it is initialized at the
        time of bean creation itself.

        Solution:
        ---------
        If we want to create a bean for each call, then we have to inject it
        indirectly (i.e. via an abstract method)

        Now we'll create an abstract method, that method will return the
        required bean object. Each time it will create a new one and return
        to us.

        In this example, I want to create two student objects, so it is
        declared as prototype. For each student one or more course objects I
        need. But using normal prototype scope, we can't get it. Each time
        we'll get the same object only.

        1. Make the dependent bean abstract (Student)
        2. Create an abstract method inside the dependent object that should
        return the injecting bean. (Course)
        3. Configure bean in the application context.
        4. To inject the prototype bean use lookup-method tag and in the name
         attribute use the abstract method name.
        5. If the injecting object is singleton, lookup-method tag will
        return same object every time.
        6. If the injecting object is prototype, the loopip-method tag will
        return different object for each call.
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("org" +
                "/katheer/resource/applicationContext.xml");

        /*
        Student student1 = (Student) context.getBean("student1");
        System.out.println("student1 : " + student1);

        System.out.println("Course Bean Normally Injected :");
        System.out.println("c1 : " + student1.getCourse());
        System.out.println("c2 : " + student1.getCourse());
        System.out.println();
        */

        Student student2 = (Student) context.getBean("student2");
        System.out.println("student2 : " + student2);

        System.out.println("Course Bean Injected via look-up method :");
        System.out.println("c1 : " + student2.createCourse());
        System.out.println("c2 : " + student2.createCourse());
        System.out.println("c3 : " + student2.createCourse());
        System.out.println("c4 : " + student2.createCourse());
        System.out.println();
    }
}
