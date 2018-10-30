package com.fc;

public class Main {

    public static void main(String[] args) {
        String query="(P=>Q),(L,M=>P),(B,C=>M),(A,B=>L),(Q,C),(X,C),(Y,X),(L,A=>D),(A),(B)";
        ForwardChainigAlgorithm forwardChainigAlgorithm=new ForwardChainigAlgorithm(query);
        forwardChainigAlgorithm.execute();
    }
}
