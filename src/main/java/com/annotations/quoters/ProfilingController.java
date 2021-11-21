package com.annotations.quoters;

public class ProfilingController implements  ProfilingControllerMBean {
    private  boolean enabled = true;

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
