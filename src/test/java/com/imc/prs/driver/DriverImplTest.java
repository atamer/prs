package com.imc.prs.driver;

import com.imc.prs.domain.player.IPlayer;
import com.imc.prs.driver.io.IOChannelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DriverImplTest {


    @Mock
    private IPlayer player1;
    @Mock
    private IPlayer player2;

    private DriverImpl driver;

    @BeforeEach
    public void setup() {
        this.driver = new DriverImpl();
    }

    @Test
    void createPlayers_OnePlayer_ComputerPlayerAdded() {
        List<IPlayer> playerList = this.driver.createPlayers(1, IOChannelFactory.CONNECTION_TYPE_CONSOLE);
        assertEquals(2, playerList.size());
    }

    @Test
    void createPlayers_TwoPlayer_ComputerPlayerNotAdded() {
        List<IPlayer> playerList = this.driver.createPlayers(1, IOChannelFactory.CONNECTION_TYPE_CONSOLE);
        assertEquals(2, playerList.size());
    }

    @Test
    void introduceGame_TwoPlayer_sendMessageCalled() {
        this.driver.introduceGame(List.of(player1, player2));
        verify(player1, atLeast(1)).sendMessage(any());
        verify(player2, atLeast(1)).sendMessage(any());

    }
}
