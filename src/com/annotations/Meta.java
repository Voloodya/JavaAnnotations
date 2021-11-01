package com.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

@What(Description = "Аннотация тестового класса")
@MyAnno( str="Meta", val = 0)
public class Meta {

    @What(Description = "Аннотация метода тестового класса")
    @MyAnno( str="Testing", val = 1)
    public static void myMeth(){

    }
}
