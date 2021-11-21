package com.annotations.quoters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;

// На данном этапе будут бины созданные из класса Proxy

public class PosProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConfigurableListableBeanFactory factory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        String[] names = context.getBeanDefinitionNames(); //Имена proxy классов бинов

        for (String name : names) {
            BeanDefinition beanDefinition = factory.getBeanDefinition(name);
            // Извлечение оригинального имени класса (не имени прокси класса)
            String originalClassName = beanDefinition.getBeanClassName();

            try {
                Class<?> originalClass = Class.forName(originalClassName);
                Method[] methods = originalClass.getMethods();

                for (Method method : methods) {
                    if (method.isAnnotationPresent(PostProxyActionThirdPhase.class)) {
                        Object bean = context.getBean(name);
                        // Извлечение метода объекта Proxy класса
                        Method currentMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
                        // Start method
                        currentMethod.invoke(bean);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
