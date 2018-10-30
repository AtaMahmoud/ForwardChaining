package com.fc;

import java.security.PublicKey;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class ForwardChainigAlgorithm {
    Literal provetion;
    DefiniteClauseKB kb;
    Scanner input=new Scanner(System.in);

    public ForwardChainigAlgorithm(String query){
        kb=parseToDefiniteClauseKB(query);
        kb.print();
        System.out.println("Plase Enter the literal to prove: ");
        provetion=new Literal(input.nextLine());
    }
    public void execute(){
        boolean entails=FcEntails(kb,provetion);

        if (entails){
            System.out.print("Literal ");
            provetion.print();
            System.out.println(" has been proven to be true");
        }else {
            System.out.print("Couldn't infer ");
            provetion.print();
            System.out.print(" from the given knowledge base.");
        }
    }

    public boolean FcEntails(DefiniteClauseKB kb, Literal provetion) {
        Stack<Literal> agenda=initAgenda(kb);

        if (agenda.contains(provetion)){
            System.out.println("The Given Provetion Literal is a fact at KB");
            return true;
        }
        while (!agenda.isEmpty()){
            Literal literal=agenda.pop();
            if (!literal.isInferred()){
                provetion.setInferred(true);

                Iterator<DefiniteClause>iterator=kb.getIterator();
                while (iterator.hasNext()){
                    DefiniteClause definiteClause=iterator.next();

                    if (definiteClause.getPremises().contains(literal)){
                        definiteClause.decrementCount();

                        if (definiteClause.getCount()==0){
                            System.out.println("Rule triggered: ");
                            definiteClause.print();

                            //print
                            System.out.print("\nNew fact: ");
                            definiteClause.getHead().print();
                            System.out.println();


                            if(definiteClause.getHead().equals(literal))
                                return true;

                            agenda.push(definiteClause.getHead());
                        }
                    }
                }
            }
        }
        return false;
    }

    private DefiniteClauseKB parseToDefiniteClauseKB(String query) {
        DefiniteClauseKB kb=new DefiniteClauseKB();
        DefiniteClause definiteClause;

        String subClasuses[]=query.split(",+(?![^\\(]*\\))");

        for (String clauses:subClasuses){
            clauses=clauses.replaceAll("[()]","");
            String tokens[]=clauses.split("=>");

            if (tokens.length==1){
                definiteClause=new DefiniteClause(new Literal(tokens[0]));
            }else {
                definiteClause=new DefiniteClause(new Literal(tokens[1]));
                String literals[]=tokens[0].split(",");

                for (String l:literals){
                    definiteClause.getPremises().add(new Literal(l));
                }
            }
            kb.getDefiniteClauses().add(definiteClause);
        }
        return kb;
    }
    public Stack<Literal> initAgenda(DefiniteClauseKB KB){
        Stack<Literal> agenda=new Stack<Literal>();

        Iterator<DefiniteClause>iterator=KB.getIterator();

        while (iterator.hasNext()){
            DefiniteClause definiteClause=iterator.next();
            definiteClause.initCount();
            if (definiteClause.isFact()){
                agenda.push(definiteClause.getHead());
            }
        }

        return agenda;
    }
}
