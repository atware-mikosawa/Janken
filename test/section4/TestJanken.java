package section4;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestJanken {

    @Test
    void ジャンケンの手がそれぞれおおよそ3分の1の確率で選択されること() {
        var counts = IntStream.range(0, 100)
                .mapToObj(ignoreUnused -> Hand.decide())
                .collect(groupingBy(identity(), counting()));

        assertTrue(isWithinRange(counts.get(Hand.ROCK), 20, 40));
        assertTrue(isWithinRange(counts.get(Hand.SCISSORS), 20, 40));
        assertTrue(isWithinRange(counts.get(Hand.PAPER), 20, 40));
    }

    private static boolean isWithinRange(Long count, int lowerLimit, int upperLimit) {
        return lowerLimit < count && count < upperLimit;
    }
}
