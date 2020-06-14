package net.kitpvp.pluginapi.modules.stats.mongo.write;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum WriteAction {

    SET("set"), INC("inc"), PUSH("push", "each"), PULL("pull", "in")
    ;

    @Getter
    private final String command, arrayCommand;

    WriteAction(String command) {
        this(command, null);
    }
}
