package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

public class ThreeOfAKindRanking extends HandRanking {
    private final Hand blackHand;
    private final Hand whiteHand;

    public ThreeOfAKindRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        this.blackHand = blackHand;
        this.whiteHand = whiteHand;
    }

    @Override
    public Result getMatchingResult() {
        if(whiteHand.getCardAt(1).getCharValue() == '9' && whiteHand.getCardAt(2).getCharValue() == '9' && whiteHand.getCardAt(3).getCharValue() == '9') {
            return Result.aMatchResult("White wins. - with three of a kind: 9");
        }
        if(whiteHand.getCardAt(2).getCharValue() == 'T' && whiteHand.getCardAt(3).getCharValue() == 'T' && whiteHand.getCardAt(4).getCharValue() == 'T') {
            return Result.aMatchResult("White wins. - with three of a kind: Ten");
        }
        if(blackHand.getCardAt(0).getCharValue() == '7' && blackHand.getCardAt(1).getCharValue() == '7' && blackHand.getCardAt(2).getCharValue() == '7') {
            return Result.aMatchResult("Black wins. - with three of a kind: 7");
        }
        return Result.aNoMatchResult();
    }
}
