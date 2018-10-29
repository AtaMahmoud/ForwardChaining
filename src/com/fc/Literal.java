package com.fc;

public class Literal {
    private String name;
    private boolean isInferred;

    public Literal(String name){
        this.name=name;
        isInferred=false;
    }

    public void print(){
        System.out.println(name);
    }
    public boolean isInferred() {
        return isInferred;
    }
    public void setInferred(boolean inferred) {
        isInferred = inferred;
    }
    public String getName() {
        return name;
    }
}
