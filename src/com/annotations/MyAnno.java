package com.annotations;
import java.lang.annotation.*;

// Правила удержания опрмомент, когда аннотация отбрасывается:
// SOURCE - хранятся только в исходном файле и отбрасываются при компиляции
// CLASS - сохраняются в файле с расширение .class во время компиляции. Но они не доступны для виртуал. машины JVM во время выполнения
// RINTIME - сохраняются в файле с расширение .class во время компиляции и остаются доступными для виртуал. машины JVM во время выполнения
// Правило удержания аннотации задается одной из встроенных аннотаций Java: @Retention(првило_удержания)

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno{
    String str();
    int val() default 0; //Свойство со значением по умолчанию
}

@Retention(RetentionPolicy.RUNTIME)
@interface What{
    String Description() default "Тестирование";
}

// Маркерная аннотация. Единственная цель - пометить объявление
@Retention(RetentionPolicy.RUNTIME)
@interface MyMarker {}

// Одночленная аннотация
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotationSingleValue {
    String value(); // эта переменная должная иметь имя value
}