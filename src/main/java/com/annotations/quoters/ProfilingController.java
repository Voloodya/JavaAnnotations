package com.annotations.quoters;

public class ProfilingController implements  ProfilingControllerMBean {
    private  boolean enabled;

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
