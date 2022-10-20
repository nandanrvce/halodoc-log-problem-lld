package usecase;

import com.halodoc.entities.Config;
import com.halodoc.entities.EVENT_TYPE;
import com.halodoc.entities.LogRecord;

import java.util.Map;
import java.util.stream.Stream;

public interface ILogProcessor {

    void init(Map<EVENT_TYPE, Config> configMap);

    void processLog(Stream<LogRecord> stream);

    void notifySubscribers(EVENT_TYPE event_type);
}
