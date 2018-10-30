package com.fc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefiniteClauseKB {
    public List<DefiniteClause> clauses=new ArrayList<DefiniteClause>();
    public List<DefiniteClause>getDefiniteClauses(){return clauses;}
    public Iterator<DefiniteClause>getIterator(){
        return clauses.iterator();
    }

    public void print(){
        Iterator<DefiniteClause>iterator=this.getIterator();
        System.out.println("KB: ");
        while (iterator.hasNext()){
            DefiniteClause definiteClause=iterator.next();
            definiteClause.print();
            System.out.println();
        }
    }
}
