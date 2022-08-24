package io.github.axst;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LuxRecode {

    @Getter
    private static final LuxRecode instance = new LuxRecode();
    @Getter
    private static final Logger luxLog = LogManager.getLogger(LuxRecode.class.getName());

    public void init() {
    }

    public void stop() {

    }
}
