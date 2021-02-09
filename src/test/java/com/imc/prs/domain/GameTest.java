package com.imc.prs.domain;

import com.imc.prs.domain.hands.HandComparingStrategy;
import com.imc.prs.domain.hands.Hands;
import com.imc.prs.domain.player.IPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameTest {

    @Mock
    private IPlayer player1;
    @Mock
    private IPlayer player2;

    private Game game;

    @BeforeEach
    public void setup() {
        this.game = Game.newInstance(HandComparingStrategy.NATURAL, List.of(player1, player2));
    }

    @Test
    void runLoop_inputPPAndQuit_callNotifyResult() {
        this.validInput_callNotifyResult('P', 'P', "you draw with player");
    }

    @Test
    void runLoop_inputRSAndQuit_callNotifyResult() {
        this.validInput_callNotifyResult('R', 'S', "you beat player");
    }

    @Test
    void runLoop_inputPSAndQuit_callNotifyResult() {
        this.validInput_callNotifyResult('P', 'S', "you lost against player");
    }



    void validInput_callNotifyResult(char character1, char character2, String message) {
        //setting up mock
        when(player1.readHand()).thenReturn(Hands.get(character1)).thenReturn(Hands.QUIT);
        when(player2.readHand()).thenReturn(Hands.get(character2)).thenReturn(Hands.QUIT);
        //call
        game.startLoop();
        //verify
        verify(player1).sendMessage(Mockito.startsWith(message));
    }

}
