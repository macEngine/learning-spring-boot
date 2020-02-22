package learning.springmvctutorial;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("index");
  }

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver bean = new InternalResourceViewResolver();

    bean.setViewClass(JstlView.class);
    bean.setPrefix("WEB-INF/view/");
    // 200213, jsp文件放在src/main/webapp/WEB-INF/view/sample.jsp可以。
    // 200213, jsp文件放在src/main/webapp/view/sample.jsp也可以，效果跟上面一样。
    // 200213,  bean.setPrefix("WEB-INF/view/");可以， bean.setPrefix("/WEB-INF/view/");也可以。 bean.setPrefix
    // ("WEB-INF/view");会报错。
    // 结论：找不到资源，是module配置的问题，不是这里设置的问题。http://blogimg.v2sky.top/blogimg/1581562771539.png
    // mvn spring-boot:run可以跑起来。

    // http://blogimg.v2sky.top/blogimg/1581563741785.png，正常访问结果的截图。
    bean.setSuffix(".jsp");
    return bean;
  }
}

