package hello;

import org.apache.log4j.Logger;

public class MyLogger {

    public void info(String msg) {
        Logger.getLogger(MyLogger.class).info(msg);
    }
}
