package com.fc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefiniteClause {
    private List<Literal>premises;
    private Literal head;
    private int count;

    public DefiniteClause(Literal head){
        this.count=0;
        premises=new ArrayList<Literal>();
        this.head=head;
    }
    public List<Literal> getPremises() {
        return premises;
    }
    public Iterator<Literal>getPermisesList(){return premises.iterator();}
    public int getCount(){return this.count;}
    public void initCount(){
        this.count=this.premises.size();
    }
    public void decrementCount(){
        this.count--;
    }
    public boolean isFact(){
        return this.premises.size()==0&&this.head!=null;
    }
    public Literal getHead() {
        return head;
    }
    public void print(){
        Iterator<Literal>iterator=this.getPermisesList();
        while (iterator.hasNext()){
            Literal literal=iterator.next();
            literal.print();
            if (iterator.hasNext())
                System.out.println(" /\\ ");
            else
                System.out.println(" => ");
        }
        this.head.print();
    }

}
