package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import org.junit.jupiter.api.Test;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PairCardRankingTest {

    private static void assertNoMatchingResult(String blackCards, String whiteCards) {
        Hand blackHand = Hand.buildFrom("Black", blackCards);
        Hand whiteHand = Hand.buildFrom("White", whiteCards);
        PairCardRanking pairCardRanking = new PairCardRanking(blackHand, whiteHand);

        assertEquals(aNoMatchResult(), pairCardRanking.getMatchingResult());
    }

    @Test
    public void
    it_should_return_a_no_matching_result_when_no_pair_is_found() {
        assertNoMatchingResult("7H 8C TD KH AS", "2D 3H 5C 9S KH");
    }

    @Test
    public void
    it_should_return_a_no_matching_result_when_having_a_tie_with_pairs() {
        assertNoMatchingResult("7H 7C TD KH AS", "7D 7S TC KS AH");
    }

    @Test
    public void
    PAIR_black_wins_with_ace_as_pair() {
        assertMatchingResult("7H JC KD AH AS", "2D 3H 5C 9S KH", aMatchResult("Black wins. - with Pair cards: Ace"), aMatchResult("Black wins. - with Pair cards: Ace").message());
    }

    private static void assertMatchingResult(String cards, String cards1, Result expected, String message) {
        Hand blackHand = Hand.buildFrom("Black", cards);
        Hand whiteHand = Hand.buildFrom("White", cards1);
        PairCardRanking pairCardRanking = new PairCardRanking(blackHand, whiteHand);

        assertEquals(aMatchResult(message), pairCardRanking.getMatchingResult());
    }

    @Test
    public void
    PAIR_black_wins_with_jack_as_pair() {
        assertMatchingResult("7H JH JC KD AS", "2D 3H 5C 9S KH", aMatchResult("Black wins. - with Pair cards: Jack"), aMatchResult("Black wins. - with Pair cards: Jack").message());
    }

    @Test
    public void
    PAIR_white_wins_with_4_as_pair() {
        assertMatchingResult("5D 6C 7H JH AS", "2D 4H 4C 9S KH", aMatchResult("White wins. - with Pair cards: 4"), aMatchResult("White wins. - with Pair cards: 4").message());
    }

    @Test
    public void
    PAIR_white_wins_with_ace_over_a_pair_of_2() {
        assertMatchingResult("2H 2D 5S 6C AS", "4D 9S KH AH AC", aMatchResult("White wins. - with Pair cards: Ace"), aMatchResult("White wins. - with Pair cards: Ace").message());
    }

    @Test
    public void
    PAIR_white_wins_with_ace_over_a_pair_of_ace_due_to_other_rank() {
        assertMatchingResult("2H 2D 5S 6C JS", "2C 2S 6H 8H AC", aMatchResult("White wins. - with Pair cards and higher rank: 2 and Ace"), aMatchResult("White wins. - with Pair cards and higher rank: 2 and Ace").message());
    }

    @Test
    public void
    PAIR_black_wins_with_ace_over_a_pair_of_ace_due_to_other_rank() {
        assertMatchingResult("2H 2D 5S 6C KS", "2C 2S 6H 8H JC", aMatchResult("Black wins. - with Pair cards and higher rank: 2 and King"), aMatchResult("Black wins. - with Pair cards and higher rank: 2 and King").message());
    }

}