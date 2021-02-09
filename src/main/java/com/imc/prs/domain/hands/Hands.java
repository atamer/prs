package com.imc.prs.domain.hands;

import com.imc.prs.InvalidHandException;

public enum Hands {

    PAPER(3, 'P'),
    ROCK(2, 'R'),
    SCISSORS(1, 'S'),
    QUIT(0, 'Q');

    public final int weight;
    public final char synonym;

    Hands(int weight, char synonym) {
        this.weight = weight;
        this.synonym = synonym;
    }

    public static Hands get(char synonym) {
        for (Hands hand : values()) {
            if (hand.synonym == synonym) {
                return hand;
            }
        }
        throw new InvalidHandException("Invalid Hand " + synonym);
    }


    }
