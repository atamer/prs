package com.imc.prs.domain.player;


import com.imc.prs.domain.hands.Hands;
import com.imc.prs.driver.io.IOChannel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PlayerTest {

    private final String name = "playername";
    @Mock
    private IOChannel ioChannel;
    private IPlayer player;

    @BeforeEach
    public void setup() {
        this.player = PlayerFactory.newPlayer(ioChannel, name);
    }

    @Test
    void readHand_InputEntered_CorrectHandReturned() {
        Mockito.when(ioChannel.promptAndReadCharacter(Mockito.anyString())).thenReturn('P');
        Assertions.assertSame(Hands.PAPER, player.readHand());
    }

    @Test
    void sendMessage_messageSent_messageRelayed() {
        String message = "message";
        this.player.sendMessage(message);
        Mockito.verify(ioChannel).println(Mockito.eq(name + ": " + message));
    }


}
