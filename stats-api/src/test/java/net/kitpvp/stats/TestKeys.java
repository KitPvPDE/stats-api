package net.kitpvp.stats;

import net.kitpvp.stats.keys.KeyFunction;
import net.kitpvp.stats.keys.KeyFunctions;
import net.kitpvp.stats.keys.LongStageKey;
import net.kitpvp.stats.keys.LongStatsKey;
import org.junit.Test;

import java.util.function.UnaryOperator;

public class TestKeys {

    @Test
    public void testRemap() {
        UnaryOperator<KeyFunction<String>> remap = (function) ->
                KeyFunctions.prefixed(function, "seasonpass");

        LongStageKey<String> stageKey = LongStatsKey.<String>builder()
                .keyBuilder(builder -> builder.prefix("prefix").function(Key.identity()).suffix("suffix"))
                .offset(1000)
                .stage(remap);

        System.out.println(stageKey.stage().key("test"));
    }
}
