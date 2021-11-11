package com.annotations.quoters;

import javax.annotation.PostConstruct;

public class TerminatorQuoter implements Quoter{

    @InjectRandomInt(min = 2, max = 10)
    private int repeat;

    private String Message;

    public TerminatorQuoter(){
        System.out.println("Phase 1 - Constructor: this.repeat =  "+this.repeat);
    }

    @PostConstruct
    public void init(){
        System.out.println("Phase  - init method: this.repeat =  "+this.repeat);
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
    public void sayQuote() {
        for (int i = 0; i < this.repeat; i++) {
            System.out.println("Message: " + this.Message);
        }
    }
}
