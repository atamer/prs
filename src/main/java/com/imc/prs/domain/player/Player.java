package com.imc.prs.domain.player;

import com.imc.prs.domain.hands.Hands;
import com.imc.prs.driver.io.IOChannel;

public class Player implements IPlayer {
    private final IOChannel channel;
    private final String name;

    public Player(IOChannel ioChannel, String name) {
        this.channel = ioChannel;
        this.name = name;
    }

    @Override
    public Hands readHand() {
        String prompt = name + ": Please Enter P(aper) R(ock) or S(cissors), Enter Q(uit) for ending game";
        return Hands.get(channel.promptAndReadCharacter(prompt));
    }

    @Override
    public void sendMessage(String message) {
        channel.println(name + ": "+message);
    }

    @Override
    public String getName() {
        return name;
    }


}
