package net.kitpvp.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.kitpvp.stats.bson.BsonStatsReader;
import net.kitpvp.stats.bson.BsonStatsWriter;
import net.kitpvp.stats.bson.codec.BsonCodec;
import net.kitpvp.stats.bson.codec.BsonConverter;
import net.kitpvp.stats.keys.BooleanVoidStatsKey;
import net.kitpvp.stats.keys.DoubleVoidStatsKey;
import net.kitpvp.stats.keys.IntVoidStatsKey;
import org.bson.Document;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class TestComplex {

    private final IntVoidStatsKey a = IntVoidStatsKey.builder().keyBuilder(builder -> builder.path("a")).build();
    private final BooleanVoidStatsKey b = BooleanVoidStatsKey.builder().keyBuilder(builder -> builder.path("b")).build();
    private final DoubleVoidStatsKey c = DoubleVoidStatsKey.builder().keyBuilder(builder -> builder.path("c")).build();

    @Test
    public void testComplex() {
        Document document = new Document("a", 3214).append("b", true).append("c", 34d);
        StatsReader statsReader = new BsonStatsReader(document);
        StatsWriter statsWriter = new BsonStatsWriter(document);

        BsonConverter<Complex> converter = new BsonConverter<>(new ComplexCodec());
        Complex decoded = statsReader.map(converter);

        assertEquals(new Complex(3214, true, 34d), decoded);
        assertEquals(statsWriter, converter.encode(decoded));
    }

    private class ComplexCodec implements BsonCodec<Complex> {

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
