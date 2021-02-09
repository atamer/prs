package com.imc.prs.domain;

import com.imc.prs.domain.hands.HandComparingStrategy;
import com.imc.prs.domain.hands.Hands;
import com.imc.prs.domain.player.IPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


/* Game object created once and players quited are being removed with sequentially , no concurrency exist  */
public class Game {

    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());

    private final HandComparingStrategy comparingStrategy;
    private final List<IPlayer> playerList;

    private Game(HandComparingStrategy comparingStrategy, List<IPlayer> playerList) {
        this.comparingStrategy = comparingStrategy;
        this.playerList = new ArrayList<>(playerList);
        LOGGER.info("New game object created with strategy");
    }

    public static Game newInstance(HandComparingStrategy comparingStrategy, List<IPlayer> playerList) {
        return new Game(comparingStrategy, playerList);
    }

    public void startLoop() {

        while (playerList.size() > 1) {
            // reading hands
            Map<IPlayer, Hands> handsMap = new HashMap<>();
            List<IPlayer> removePlayers = new ArrayList<>();
            for (IPlayer player : playerList) {
                Hands hand = player.readHand();
                if (Hands.QUIT == hand) {
                    removePlayers.add(player);
                    player.sendMessage("bye");
                } else {
                    handsMap.put(player, hand);
                }
            }
            playerList.removeAll(removePlayers);

            // compare for each player
            for (IPlayer playerFirst : playerList) {
                for (IPlayer playerNext : playerList) {
                    if (playerFirst != playerNext) {
                        int handComparison = this.comparingStrategy.compareHands(handsMap.get(playerFirst), handsMap.get(playerNext));
                        if (handComparison == 0) {
                            playerFirst.sendMessage("you draw with player " + playerNext.getName() + "(" + handsMap.get(playerNext) + ")");
                        } else if (handComparison > 0) {
                            playerFirst.sendMessage("you beat player " + playerNext.getName() + "(" + handsMap.get(playerNext) + ")");
                        } else {
                            playerFirst.sendMessage("you lost against player " + playerNext.getName() + "(" + handsMap.get(playerNext) + ")");
                        }
                    }
                }
            }
        }
    }


}

