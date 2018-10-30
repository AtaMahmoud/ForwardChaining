package com.fc;

public class Main {

    public static void main(String[] args) {
        ForwardChainigAlgorithm forwardChainigAlgorithm=new ForwardChainigAlgorithm("(P=>Q),(L,M=>P),(B,L=>M),(P,A=>L),(A,B=>L),(A),(B)");
        forwardChainigAlgorithm.execute();
    }
}
