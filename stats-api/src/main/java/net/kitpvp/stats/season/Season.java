package net.kitpvp.stats.season;

import com.google.common.base.Preconditions;
import com.google.gson.JsonElement;
import lombok.Getter;
import net.kitpvp.json.JsonConfig;
import net.kitpvp.json.JsonReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Getter
public final class Season {

    static {
        try{
            JsonElement element = JsonReader.readToJson(Season.class.getResourceAsStream("/seasons.json"));
            List<Season> seasons = JsonConfig.readList(element, JsonElement::isJsonObject, Season::new, new String[]{"seasons"});
            SEASONS = seasons.toArray(new Season[0]);
        }catch(IOException | NullPointerException e){
            throw new ExceptionInInitializerError(e);
        }
    }

    public static final int ALLTIME = 0;
    private static final Season[] SEASONS;
    private static AtomicReference<Season> SEASON = new AtomicReference<>();
    private static AtomicReference<Stage> STAGE = new AtomicReference<>();

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

    public static int getStage() {
        Stage stage = STAGE.get();
        if(stage == null || !stage.isLive()) {
            stage = loadStage();
            STAGE.set(stage);
        }

        if(stage == null)
            throw new IllegalStateException("No stage live currently");
        return stage.getNumber();
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

    private static Stage loadStage() {
        for(Stage stage : getSeason(getSeason()).getStages()) {
            if(stage.isLive())
                return stage;
        }
        return null;
    }

    private final int number;
    private final String title;
    private final List<Stage> stages;
    private final long start, end;

    private Season(JsonElement element) {
        this.number = JsonConfig.readInteger(element, -1, "number");
        this.title = JsonConfig.readString(element, null, "title");
        this.stages = JsonConfig.readList(element, JsonElement::isJsonObject, Stage::new, new String[]{"stages"});
        Preconditions.checkArgument(this.stages.size() >= 1, "Each season must have at least one stage");

        this.start = this.stages.get(0).getStart();
        this.end = this.stages.get(this.stages.size() - 1).getEnd();
    }

    public boolean isLive() {
        long time = System.currentTimeMillis();
        return time >= start && time <= end;
    }

    public Stage getStage(int stage) {
        Preconditions.checkElementIndex(stage, this.stages.size());
        return this.stages.get(stage);
    }

    @Override
    public String toString() {
        return String.format("Season [%d, \"%s\" from %tc until %tc]", this.number, this.title, this.start, this.end);
    }
}
