package com.imc.prs.domain.hands;

import com.imc.prs.InvalidHandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandComparingStrategyTest {

    private HandComparingStrategy handComparingStrategy;

    @BeforeEach
    public void setup() {
        this.handComparingStrategy = HandComparingStrategy.NATURAL;
    }

    @Test
    void compareHands_theSameHand_ReturnZero() {
        assertEquals(0, handComparingStrategy.compareHands(Hands.get('P'), Hands.get('P')));
    }

    @Test
    void compareHands_comparePR_PWins() {
        assertTrue(0 < handComparingStrategy.compareHands(Hands.get('P'), Hands.get('R')));
    }

    @Test
    void compareHands_compareRP_PWins() {
        assertTrue(0 > handComparingStrategy.compareHands(Hands.get('R'), Hands.get('P')));
    }

    @Test
    void compareHands_compareSR_RWins() {
        assertTrue(0 > handComparingStrategy.compareHands(Hands.get('S'), Hands.get('R')));
    }

    @Test
    void compareHands_compareRS_RWins() {
        assertTrue(0 < handComparingStrategy.compareHands(Hands.get('R'), Hands.get('S')));
    }

    @Test
    void compareHands_comparePS_SWins() {
        assertTrue(0 > handComparingStrategy.compareHands(Hands.get('P'), Hands.get('S')));
    }

    @Test
    void compareHands_compareSP_SWins() {
        assertTrue(0 < handComparingStrategy.compareHands(Hands.get('S'), Hands.get('P')));
    }

    @Test
    void compareHands_invalidFirstHand_ThrowException() {
        assertThrows(InvalidHandException.class, () -> handComparingStrategy.compareHands(Hands.get('a'), Hands.get('P')));
    }

    @Test
    void compareHands_invalidSecondHand_ThrowException() {
        assertThrows(InvalidHandException.class, () -> handComparingStrategy.compareHands(Hands.get('P'), Hands.get('a')));
    }
}
