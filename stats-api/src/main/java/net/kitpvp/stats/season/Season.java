package net.kitpvp.stats.season;

import com.google.gson.JsonElement;
import lombok.Getter;
import net.kitpvp.json.JsonConfig;
import net.kitpvp.json.JsonReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Getter
public final class Season {

    static {
        Season[] result;
        try{
            JsonElement element = JsonReader.readToJson(Objects.requireNonNull(Season.class.getResourceAsStream("/seasons.json")));
            List<Season> seasons = JsonConfig.readList(element, JsonElement::isJsonObject, Season::new, new String[]{"seasons"});
            result = seasons.toArray(new Season[0]);
        }catch(IOException | NullPointerException e){
            result = new Season[] {new Season()};
        }

        SEASONS = result;
    }

    public static final int ALLTIME = 0;
    private static final Season[] SEASONS;
    private static final AtomicReference<Season> SEASON = new AtomicReference<>();

    public static int getSeason() {
        Season season = SEASON.get();
        if(season == null || !season.isLive()){
            season = loadSeason();
            SEASON.set(season);
        }

        if(season == null)
            throw new IllegalStateException("No season live currently");

        return season.getNumber();
    }

    public static Season getActiveSeason() {
        return getSeason(getSeason());
    }

    public static Season getSeason(int number) {
        return Arrays.stream(SEASONS).filter(season -> season.getNumber() == number).findAny().orElse(null);
    }

    public static Season[] getSeasons() {
        return SEASONS;
    }

    private static Season loadSeason() {
        for(Season season : SEASONS){
            if(season.isLive())
                return season;
        }
        return null;
    }

    private final int number;
    private final String title;
    private final long start, end;

    private Season(JsonElement element) {
        this.number = JsonConfig.readInteger(element, -1, "number");
        this.title = JsonConfig.readString(element, null, "title");
        this.start = JsonConfig.readLong(element, 0, "start");
        this.end = JsonConfig.readLong(element, Long.MAX_VALUE, "end");
    }

    private Season() {
        this.number = -1;
        this.title = "Fallback Season";
        this.start = 0;
        this.end = Long.MAX_VALUE;
    }

    public boolean isLive() {
        long time = System.currentTimeMillis();
        return time >= start && time <= end;
    }

    public boolean isEnding(long time, TimeUnit unit) {
        return (this.end - System.currentTimeMillis()) <= unit.toMillis(time);
    }

    @Override
    public String toString() {
        return String.format("Season [%d, \"%s\" from %tc until %tc]", this.number, this.title, this.start, this.end);
    }
}
