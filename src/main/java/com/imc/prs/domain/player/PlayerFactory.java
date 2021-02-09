package com.imc.prs.domain.player;

import com.imc.prs.driver.io.IOChannel;

public class PlayerFactory {

    public static IPlayer computerPlayer() {
        return new ComputerPlayer();
    }

    public static IPlayer newPlayer(IOChannel ioChannel, String playerName) {
        return new Player(ioChannel, playerName);
    }
}
