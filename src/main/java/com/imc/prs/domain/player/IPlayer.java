package com.imc.prs.domain.player;

import com.imc.prs.domain.hands.Hands;

public interface IPlayer {

    Hands readHand();

    void sendMessage(String message);

    String getName();


}
