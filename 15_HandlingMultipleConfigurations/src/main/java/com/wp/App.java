package com.wp;

import com.wp.beans.CartService;
import com.wp.beans.OrderService;
import com.wp.beans.UserService;
import com.wp.config.AppConfig;
import com.wp.web.HomeController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main( String[] args )
    {
        //SHORTCUT Key to generate variable with return type: CTRL + alt + v
        System.out.println( "Application Started" );

        //We have to manually create the object of application context
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println(context);

        CartService cartService1 = context.getBean("cartService1", CartService.class);
        cartService1.createCart();

        UserService bean = context.getBean(UserService.class);
        bean.saveUser();

        OrderService bean1 = context.getBean(OrderService.class);
        bean1.createOrder();

        HomeController bean2 = context.getBean(HomeController.class);
        bean2.homePage();

    }
}
