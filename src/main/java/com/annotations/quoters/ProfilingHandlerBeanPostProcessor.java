package com.annotations.quoters;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

// Конструктор (construct) 2-ой фазы. Генерация бинов proxy классов
// Класс добавляющий логику по профилированию метододов объектов после конструктора и инициализации.
// Отвечает за обработку всех бинов имеющих данную анноацию (@Profiling) в каком-то поле
public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class> map = new HashMap<String, Class>();
    private final ProfilingController controller = new ProfilingController();

    public ProfilingHandlerBeanPostProcessor() throws Exception {
        //Instance MBeanServer, в который можно регистрировать бины
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        // Регистрация объекта controller в MBeanServer-е для задания значения через JVM config - VisualVM
        platformMBeanServer.registerMBean(controller, new ObjectName("profiling", "name", "controller"));
    }

    // Вызывается до init-метода, сразу после создания bean-а
    // Запоминаем объекты оригинальных классов у которых есть аннотация @Profiling
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)) {
            map.put(beanName, beanClass);
        }
        return bean;
    }

    // Вызывается после init-метода
    // Генерация бинов Proxy классов с добавлением нужного функционала, для объектов оригинального класса, которые были запомнены в postProcessBeforeInitialization
    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        Class<?> beanClass = map.get(beanName);

        if (beanClass != null) {
            // Создание proxy
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                // логика, которая будет добавляться в методы объектов классов на этапе AfterInitialization
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                    if (controller.isEnabled()) {
                        System.out.println("Профилирую метод класса объекта Proxy класса, полученного из оригинального класса ...");
                        long before = System.nanoTime();
                        Object retVal = method.invoke(bean, args);
                        long after = System.nanoTime();
                        System.out.println("Время рабоы nanoTime: " + (after - before));
                        System.out.println("Завершение профилирования");
                        return retVal;
                    } else {
                        return method.invoke(bean, args);
                    }
                }
            });
        }
        return null;
    }
}
