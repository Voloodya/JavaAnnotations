package com.annotations.quoters;

public class TerminatorQuoter implements Quoter{

    @InjectRandomInt(min = 1, max = 10)
    private int repeat;

    private String Message;

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
