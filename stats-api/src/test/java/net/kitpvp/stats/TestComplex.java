package net.kitpvp.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.kitpvp.stats.bson.BsonStatsReader;
import net.kitpvp.stats.bson.BsonStatsWriter;
import net.kitpvp.stats.converter.Converter;
import net.kitpvp.stats.converter.Context;
import net.kitpvp.stats.keys.bool.BooleanSStatsKey;
import net.kitpvp.stats.keys.numeric.DoubleSStatsKey;
import net.kitpvp.stats.keys.numeric.IntSStatsKey;
import org.bson.Document;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class TestComplex {

    private final IntSStatsKey a = IntSStatsKey.builder().keyBuilder(builder -> builder.path("a")).build();
    private final BooleanSStatsKey b = BooleanSStatsKey.builder().keyBuilder(builder -> builder.path("b")).build();
    private final DoubleSStatsKey c = DoubleSStatsKey.builder().keyBuilder(builder -> builder.path("c")).build();

    @Test
    public void testComplex() {
        Document document = new Document("a", 3214).append("b", true).append("c", 34d);
        StatsReader statsReader = new BsonStatsReader(document);
        StatsWriter statsWriter = new BsonStatsWriter(document);

        Converter<Complex> converter = new Converter<>(new ComplexContext());
        Complex decoded = statsReader.mapStatsReader(converter);

        assertEquals(new Complex(3214, true, 34d), decoded);
        assertEquals(statsWriter, converter.encode(decoded));
    }

    private class ComplexContext implements Context<Complex> {

        @Override
        public StatsWriter encode(Complex complex, StatsWriter statsWriter) {
            statsWriter.setStatKey(a, null, complex.a);
            statsWriter.setStatKey(b, null, complex.b);
            statsWriter.setStatKey(c, null, complex.c);
            return statsWriter;
        }

        @Override
        public Complex decode(StatsReader statsReader) {
            return new Complex(statsReader);
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    private class Complex {
        private int a;
        private boolean b;
        private double c;

        public Complex(StatsReader statsReader) {
            this.a = statsReader.getIntKey(TestComplex.this.a);
            this.b = statsReader.getBooleanKey(TestComplex.this.b);
            this.c = statsReader.getDoubleKey(TestComplex.this.c);
        }

        public StatsWriter encode() {
            BsonStatsWriter statsWriter = new BsonStatsWriter();
            statsWriter.setStatKey(TestComplex.this.a, null, this.a);
            statsWriter.setStatKey(TestComplex.this.b, null, this.b);
            statsWriter.setStatKey(TestComplex.this.c, null, this.c);
            return statsWriter;
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if(o == null || getClass() != o.getClass()) return false;
            Complex complex = (Complex) o;
            return a == complex.a &&
                    b == complex.b &&
                    Double.compare(complex.c, c) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }
    }
}
