package com.imc.prs.driver;


import com.imc.prs.domain.Game;
import com.imc.prs.domain.hands.HandComparingStrategy;
import com.imc.prs.domain.player.IPlayer;
import com.imc.prs.domain.player.PlayerFactory;
import com.imc.prs.driver.io.IOChannelFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DriverImpl implements IDriver {

    private static final Logger LOGGER = Logger.getLogger(DriverImpl.class.getName());

    DriverImpl() {
        LOGGER.info("New Driver Created");
    }

    public static IDriver createDriver() {
        return new DriverImpl();
    }

    @Override
    public void startNewGameWithPlayer(int playerCount, char communication) {
        List<IPlayer> players = createPlayers(playerCount, communication);
        Game game = Game.newInstance(HandComparingStrategy.NATURAL, players);
        introduceGame(players);
        game.startLoop();
    }

    List<IPlayer> createPlayers(int playerCount, char comm) {
        List<IPlayer> playerList = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            playerList.add(PlayerFactory.newPlayer(IOChannelFactory.createIOChannel(comm), "Player" + i));
        }
        if (playerList.size() == 1) {
            playerList.add(PlayerFactory.computerPlayer());
        }
        return playerList;
    }

    void introduceGame(List<IPlayer> playerList) {
        for (IPlayer player : playerList) {
            player.sendMessage("For each hand you need to select one of P-R-S (Paper-Rock-Scissors)\n");
            player.sendMessage("Rules");
            player.sendMessage("1) Rock crushes Scissors");
            player.sendMessage("2) Rock crushes Scissors");
            player.sendMessage("3) Paper covers Rock");
        }

    }
}
