package com.example;

import java.util.Random;

public class JavaJester {
    protected static final String[] jokes = {
        "What is invisible and smells like carrots? Rabbit farts.",
        "Did you hear that David lost his ID in prague? Now we just have to call him Dav.",
        "What do you do when you see a space man? You park your car, man!",
        "How come a man driving a train got struck by lightning? He was a good conductor.",
        "Why do chicken coops only ever have two doors? If they had four doors, they would be a chicken sedan!",
        "What do you call Santa's little helpers? Subordinate Clauses."
    };
    protected static Random mRandom = new Random();

    public static String jokeMe() {
        return jokes[mRandom.nextInt(jokes.length)];
    }
}
