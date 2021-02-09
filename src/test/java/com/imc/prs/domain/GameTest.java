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
    void runLoop_inputPAndQuit_callNotifyResult() {
        this.validInput_callNotifyResult('P');
    }

    @Test
    void runLoop_inputRAndQuit_callNotifyResult() {
        this.validInput_callNotifyResult('R');
    }

    @Test
    void runLoop_inputSAndQuit_callNotifyResult() {
        this.validInput_callNotifyResult('S');
    }

    void validInput_callNotifyResult(char character) {
        //setting up mock
        when(player1.readHand()).thenReturn(Hands.get(character)).thenReturn(Hands.QUIT);
        when(player2.readHand()).thenReturn(Hands.get(character)).thenReturn(Hands.QUIT);
        //call
        game.startLoop();
        //verify
        verify(player1).sendMessage(Mockito.startsWith("you draw with player"));
    }

}
