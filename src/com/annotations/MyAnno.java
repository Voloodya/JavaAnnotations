package com.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno{
    String str();
    int val();
}

@Retention(RetentionPolicy.RUNTIME)
@interface What{
    String Description();
}


