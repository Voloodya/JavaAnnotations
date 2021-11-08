package com.annotations.quoters;

public class TerminatorQuoter implements Quoter{

    private String Message;

    public TerminatorQuoter(String message) {
        this.Message = message;
    }

    public String getMessage() {
        return this.Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    @Override
    public void sayQuote() {
        System.out.println("Message: "+this.Message);
    }
}
