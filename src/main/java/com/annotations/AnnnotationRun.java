package com.annotations;

import com.annotations.quoters.Quoter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.annotation.Annotation;
// Рефлексия - языковое средство для получения сведений о классе во время тсполнения программы
import java.lang.reflect.*;

public class AnnnotationRun {

    public static void getAnnotationsClass(Object obj){

        Class<?> object = obj.getClass();
        Annotation[] annotations = object.getAnnotations();

        //Вывести все анноации для класса Meta
        System.out.println("Все анноации класса Meta: ");
        for(Annotation annotation : annotations){
            System.out.println(annotation);
        }
    }

    public static void getAnnotationsMethodClass(Object obj){

        try {
            Annotation [] annotations = obj.getClass().getAnnotations();

            //Получение метода
            Class<?> object = obj.getClass();
            // Method getMethod(String name_method, Class<?> ... type_parametrs);
            Method method = object.getMethod("myMeth");
            //Получение аннотаций метода
            annotations = method.getAnnotations();

            System.out.println("Все аннотации метода myMeth():");
            for (Annotation annotation : annotations){
                System.out.println(annotation);
            }
        }catch (NoSuchMethodException exc){

            System.out.println("Method not found!");
        }
    }

    public static void getMarkerAnnotationsObject(Object object){
         Class<?> obj = object.getClass();

         if(obj.isAnnotationPresent(MyMarker.class)){
             System.out.println("Маркерная аннотация MyMarker у класса присутствует");
         }
    }

    public static void getMarkerAnnotationsMethod(Object object){

         Class<?> obj = object.getClass();

        try {
            Method method = obj.getMethod("myMeth");

            if(method.isAnnotationPresent(MyMarker.class)){
                System.out.println("Маркерная аннотация MyMarker у метода присутствует");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void getAnnotationsSingleValueObject(Object object){
        Class<?> obj = object.getClass();

        if(obj.isAnnotationPresent(MyAnnotationSingleValue.class)){
            MyAnnotationSingleValue myAnnotationSingleValue = obj.getAnnotation(MyAnnotationSingleValue.class);
            System.out.println("Одночленная аннотация. Значение: " + myAnnotationSingleValue.value());
        }
    }

    public static void getAnnotationsSingleValueMethod(Object object){

        Class<?> obj = object.getClass();

        try {
            Method method = obj.getMethod("myMeth");

            if(method.isAnnotationPresent(MyAnnotationSingleValue.class)){
                MyAnnotationSingleValue myAnnotationSingleValue = method.getAnnotation(MyAnnotationSingleValue.class);
                System.out.println("Одночленная аннотация. Значение: " + myAnnotationSingleValue.value());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public  static  void main(String[] args) throws Exception{

        // Чтение аннотаций в объектах классов
//        Meta meta = new Meta();
//        getAnnotationsClass(meta);
//        getAnnotationsMethodClass(meta);
//        getMarkerAnnotationsObject(meta);
//        getMarkerAnnotationsMethod(meta);
//
//        getAnnotationsSingleValueObject(meta);
//        getAnnotationsSingleValueMethod(meta);

        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");
        while (true) {
            Thread.sleep(1000);
            context.getBean(Quoter.class).sayQuote();
        }

    }

}
