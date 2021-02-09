package com.imc.prs.domain.player;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ComputerPlayerTest {

    private IPlayer player;

    @BeforeEach
    public void setup() {
        this.player = PlayerFactory.computerPlayer();
    }

    @Test
    void readHand_InputEntered_CorrectHandReturned() {
        Assertions.assertNotNull(player.readHand());
    }


}
