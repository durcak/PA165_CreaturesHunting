package com.monsterhunters.pa165.mvc.config;

import com.monsterhunters.pa165.sampledata.HuntersSampleDataConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.validation.Validator;

/**
 * Created by babcang
 *
 * @author Babcan G
 */

@EnableWebMvc
@Configuration
@Import({HuntersSampleDataConfiguration.class})
@ComponentScan(basePackages = "com.monsterhunters.pa165.mvc.controllers")
public class MySpringMvcConfig extends WebMvcConfigurerAdapter {

    final static Logger log = LoggerFactory.getLogger(MySpringMvcConfig.class);

    public static final String TEXTS = "Texts";

    /**
     * Maps the main page to a specific view.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        log.debug("mapping URL / to home view");
        registry.addViewController("/").setViewName("home");
    }

    /**
     * Enables default Tomcat servlet that serves static files.
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        log.debug("enabling default servlet for static files");
        configurer.enable();
    }

    /**
     * Provides mapping from view names to JSP pages in WEB-INF/jsp directory.
     */
    @Bean
    public ViewResolver viewResolver() {
        log.debug("registering JSP in /WEB-INF/jsp/ as views");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * Provides localized messages.
     */
    @Bean
    public MessageSource messageSource() {
        log.debug("registering ResourceBundle 'Texts' for messages");
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(TEXTS);
        return messageSource;
    }

    /**
     * Provides JSR-303 Validator.
     */
    @Bean
    public Validator validator() {
        log.debug("registering JSR-303 validator");
        return new LocalValidatorFactoryBean();
    }
}
