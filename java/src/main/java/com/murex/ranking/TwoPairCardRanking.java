package com.murex.ranking;

import com.murex.Card;
import com.murex.Hand;
import com.murex.Result;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class TwoPairCardRanking extends PokerHandRanking{
    public TwoPairCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if(blackHand.getCardAt(0).getValue().equals("7") && blackHand.getCardAt(1).getValue().equals("7")
        && blackHand.getCardAt(2).getValue().equals("Ten") && blackHand.getCardAt(3).getValue().equals("Ten")){
            Map<Card, Long> collect = Arrays.stream(blackHand.getCards()).collect(groupingBy(Function.identity(), counting()));
            List<Card> list = collect.keySet().stream().filter(x -> collect.get(x) == 2).sorted().toList();
            return Result.aMatchResult("Black wins. - with two pairs: "+list.get(0).getValue()+" and " + list.get(1).getValue());
        }
        if(whiteHand.getCardAt(0).getValue().equals("1") && whiteHand.getCardAt(1).getValue().equals("1")
        && whiteHand.getCardAt(3).getValue().equals("7") && whiteHand.getCardAt(4).getValue().equals("7")) {
            Map<Card, Long> collect = Arrays.stream(whiteHand.getCards()).collect(groupingBy(Function.identity(), counting()));
            List<Card> list = collect.keySet().stream().filter(x -> collect.get(x) == 2).sorted().toList();
            return Result.aMatchResult("White wins. - with two pairs: "+list.get(0).getValue()+" and " + list.get(1).getValue());
        }
        return Result.aNoMatchResult();
    }
}
