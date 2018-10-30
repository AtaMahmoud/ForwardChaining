package com.fc;

public class Literal {
    private String name;
    private boolean isInferred;

    public Literal(String name){
        this.name=name;
        isInferred=false;
    }

    public void print(){
        System.out.print(name);
    }
    public boolean isInferred() {
        return isInferred;
    }
    public void setInferred() {
        isInferred = true;
    }
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        Literal literal=(Literal)obj;

        if (literal.getName().compareTo(this.name)==0)
            return true;
        else
            return false;
    }
}
