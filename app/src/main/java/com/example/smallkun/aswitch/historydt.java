package com.example.smallkun.aswitch;

public class historydt {
    private String time;
    private String op;

    public historydt(String time, String op){
        this.time = time;
        this.op = op;
    }

    public String getOp() {
        return op;
    }

    public String gettime() {
        return time;
    }
}
