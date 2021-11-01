package com.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

@What()
@MyAnno( str="Meta")
@MyMarker
public class Meta {

    @What(Description = "Аннотация метода тестового класса")
    @MyAnno( str="Testing", val = 1)
    @MyAnnotationSingleValue("Одночленная аннотация")
    @MyMarker
    public void myMeth(){

    }
}
