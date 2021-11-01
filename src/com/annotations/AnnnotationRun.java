package com.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnnotationRun {

    public static void getAnnotationsClass(Object obj){

        Annotation[] annotations = obj.getClass().getAnnotations();

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
            Method method = obj.getClass().getMethod("myMeth");
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

    public  static  void main(String[] args){

        Meta meta = new Meta();
        getAnnotationsClass(meta);
        getAnnotationsMethodClass(meta);
    }

}
