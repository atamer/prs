package com.imc.prs.domain.player;

import com.imc.prs.domain.hands.Hands;

import java.util.Random;

public class MockPlayer implements IPlayer {

    private final Random random = new Random();

    @Override
    public Hands readHand() {
        return Hands.values()[random.nextInt(Hands.values().length - 1)];
    }


    @Override
    public void sendMessage(String message) {
        // not implemented
    }

    @Override
    public String getName() {
        return "Computer";
    }
}
