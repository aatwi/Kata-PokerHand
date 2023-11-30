package com.murex.ranking;

import com.murex.Hand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HighCardRankingTest {

    @Test
    public void
    HIGH_CARD_white_wins_with_Ace(){
        String black = "2H 3D 5S 9C KD";
        String white = "2C 3H 4S 8C AH"; 

        String expected = "White wins. - with high card: Ace";
        HighCardRanking highCardRanking = new HighCardRanking(Hand.buildFrom("Black", black), Hand.buildFrom("White", white));

        assertEquals(expected, highCardRanking.getMatchingResult().message());
    }

    @Test
    public void
    HIGH_CARD_black_wins_with_Queen(){
        String black = "2H 3D 5S 9C QD";
        String white = "2C 3H 4S 8C JH";

        String expected = "Black wins. - with high card: Queen";

        HighCardRanking highCardRanking = new HighCardRanking(Hand.buildFrom("Black", black), Hand.buildFrom("White", white));

        assertEquals(expected, highCardRanking.getMatchingResult().message());
    }

    @Test
    public void
    HIGH_CARD_white_wins_Jack(){
        String black = "2H 3D 5S 7C 9D";
        String white = "2C 3H 4S 8C JH";

        String expected = "White wins. - with high card: Jack";
        HighCardRanking highCardRanking = new HighCardRanking(Hand.buildFrom("Black", black), Hand.buildFrom("White", white));

        assertEquals(expected, highCardRanking.getMatchingResult().message());
    }

    @Test
    public void
    HIGH_CARD_black_wins_with_9(){
        String black = "2H 3D 5S 8C 9D";
        String white = "2C 3H 4S 7C 8H";

        String expected = "Black wins. - with high card: 9";
        HighCardRanking highCardRanking = new HighCardRanking(Hand.buildFrom("Black", black), Hand.buildFrom("White", white));

        assertEquals(expected, highCardRanking.getMatchingResult().message());
    }


}