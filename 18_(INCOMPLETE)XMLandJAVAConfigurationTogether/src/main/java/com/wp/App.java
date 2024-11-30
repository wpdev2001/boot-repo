package com.wp;
import com.wp.beans.Address;
import com.wp.beans.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;


public class App
{
    public static void main( String[] args )
    {
        //SHORTCUT Key to generate variable with return type: CTRL + alt + v
        System.out.println( "Application Started" );

        //NOTE: Here we don't need to mention the resources folder
        ApplicationContext context = new ClassPathXmlApplicationContext("Configuration/config.xml");
        System.out.println(context);

        Student student = context.getBean("Student", Student.class);
        student.createStudent();

        System.out.println(student);
        Address address = student.getAddress();
        System.out.println(address);

        Map<String, String> map = student.getMap();

        map.forEach((key, value) ->{
            System.out.println(key + "-->" + value);
        } );


    }
}
