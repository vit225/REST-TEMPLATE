package org.example.rest;

import org.example.rest.Communication;
import org.example.rest.config.MyConfig;
import org.example.rest.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);
        communication.getAllUser();
        System.out.println(communication.saveUser(new User(3L,"James","Brown",(byte)35)));
        System.out.println(communication.updateUser(new User(3L,"Thomas","Shelby",(byte)35)));
        System.out.println(communication.deleteUser(3l));
    }
}
