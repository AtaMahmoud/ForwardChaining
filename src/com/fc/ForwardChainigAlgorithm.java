package com.fc;


import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class ForwardChainigAlgorithm {
    Literal provetion;
    DefiniteClauseKB kb;
    Scanner input = new Scanner(System.in);

    public ForwardChainigAlgorithm(String query) {
        kb = parseToDefiniteClauseKB(query);
        kb.print();
        System.out.print("Plase Enter the literal to prove: ");
        provetion = new Literal(input.nextLine());
    }

    public void execute() {
        boolean entails = FcEntails(kb, provetion);

        if (entails) {
            System.out.print("Literal ");
            provetion.print();
            System.out.println(" has been proven to be true");
        } else {
            System.out.print("Couldn't infer ");
            provetion.print();
            System.out.print(" from the given knowledge base.");
        }
    }


    private DefiniteClauseKB parseToDefiniteClauseKB(String query) {
        DefiniteClauseKB kb = new DefiniteClauseKB();
        DefiniteClause definiteClause;

        String subClasuses[] = query.split(",+(?![^\\(]*\\))");

        for (String clauses : subClasuses) {
            clauses = clauses.replaceAll("[()]", "");
            String tokens[] = clauses.split("=>");

            if (tokens.length == 1) {
                definiteClause = new DefiniteClause(new Literal(tokens[0]));
            } else {
                definiteClause = new DefiniteClause(new Literal(tokens[1]));
                String literals[] = tokens[0].split(",");

                for (String l : literals) {
                    definiteClause.getPremises().add(new Literal(l));
                }
            }
            kb.getDefiniteClauses().add(definiteClause);
        }
        return kb;
    }

    public boolean FcEntails(DefiniteClauseKB KB, Literal q) {
        Stack<Literal> agenda = initAgenda(KB);


        if (agenda.contains(q)) {
            System.out.println("The given statement is a fact of the knowledge base.");
            return true;
        }

        while (!agenda.isEmpty()) {

            Literal literal = agenda.pop();

            if (!literal.isInferred()) {
                literal.setInferred();

                Iterator<DefiniteClause> iterator = KB.getIterator();
                while (iterator.hasNext()) {
                    DefiniteClause definiteClause = iterator.next();
                    if (definiteClause.getPremises().contains(literal)) {
                        definiteClause.decrementCount();
                        if (definiteClause.getCount() == 0) {
                            System.out.println("Rule triggered: ");
                            definiteClause.print();

                            System.out.print("\nNew fact: ");
                            definiteClause.getHead().print();
                            System.out.println();


                            if (definiteClause.getHead().equals(q))
                                return true;

                            agenda.push(definiteClause.getHead());

                        }
                    }
                }
            }

        }

        return false;
    }

    public Stack<Literal> initAgenda(DefiniteClauseKB KB) {
        Stack<Literal> agenda = new Stack<Literal>();

        Iterator<DefiniteClause> iterator = KB.getIterator();

        while (iterator.hasNext()) {
            DefiniteClause definiteClause = iterator.next();
            definiteClause.initCount();
            if (definiteClause.isFact()) {
                agenda.push(definiteClause.getHead());
            }
        }

        return agenda;
    }
}
