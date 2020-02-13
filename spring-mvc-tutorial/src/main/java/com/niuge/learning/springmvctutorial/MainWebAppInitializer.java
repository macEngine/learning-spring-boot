package com.niuge.learning.springmvctutorial;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MainWebAppInitializer implements WebApplicationInitializer {
  // 200213 WebApplicationInitializer目前只对war启动的项目有效，对jar启动的项目无效。
  // WebApplicationInitializer初始化WEB应用，不需要WEB.XML
  public void onStartup(ServletContext container) throws ServletException {
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

    context.scan("com.niuge.learning.springmvctutorial");

    container.addListener(new ContextLoaderListener(context));

    ServletRegistration.Dynamic dispatcher = container.addServlet("mvc", new DispatcherServlet(context));
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/");
  }

}
