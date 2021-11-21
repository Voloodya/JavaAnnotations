package com.annotations.quoters;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

// Конструктор (construct) 1-ой фазы
//Класс имплиментирующий интерфейс сразу после отработки конструктора. Отвечает за обработку всех бинов имеющих данную анноацию (@InjectRandomInt) в каком-то поле
public class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor {

    // Вызывается до init-метода, сразу после создания bean-а
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field [] fields = bean.getClass().getDeclaredFields();
       for(Field field : fields){
           InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
           if(annotation != null){
               int min = annotation.min();
               int max = annotation.max();
               Random random = new Random();
               int i = min + random.nextInt(max-min);
               field.setAccessible(true);
               ReflectionUtils.setField(field,bean,i);
           }
       }
        return bean;
    }
    // Вызывается после init-метода
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
