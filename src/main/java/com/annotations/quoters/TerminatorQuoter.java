package com.annotations.quoters;

import javax.annotation.PostConstruct;

@Profiling //Добавление лигики по замеру времени работы каждого метода объектов данного класса. Реализуется путем создания Spring-ом proxy-класса
public class TerminatorQuoter implements Quoter{

    @InjectRandomInt(min = 1, max = 6) //Сработает после создания бина InjectRandomIntAnnotationBeanPostProcessor BeanPostProcessor-ом в контекст
    private int repeat;

    private String Message;

    public TerminatorQuoter(){
        System.out.println("Phase 1 - Constructor. Before annotation: this.repeat =  " + this.repeat);
    }

    @PostConstruct //Сработает только после добвления бина CommonAnnotationBeanPostProcessor в контекст
    public void init(){
        System.out.println("Phase 2 - init-method. After annotation: this.repeat =  " + this.repeat);
    }

    public String getMessage() {
        return this.Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    @Override
    @PostProxyActionThirdPhase
    public void sayQuote() {
        System.out.println("Phase 3. After PostConstruct: ");
        for (int i = 0; i < this.repeat; i++) {
            System.out.println("Message: " + this.Message);
        }
    }
}
