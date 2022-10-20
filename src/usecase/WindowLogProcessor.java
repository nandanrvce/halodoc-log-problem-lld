package usecase;

import com.halodoc.entities.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class WindowLogProcessor implements ILogProcessor {
    Map<EVENT_TYPE, Config> configMap = new HashMap<>();
    Map<EVENT_TYPE, ProcessingInfo> processingInfoMap = new HashMap<>();
    Notifier notifier = new Notifier();

    @Override
    public void init(Map<EVENT_TYPE, Config> configMap) {
        this.configMap = configMap;
    }

    @Override
    public void processLog(Stream<LogRecord> stream) {
        stream.map(logRecord -> {
            if(Objects.isNull(processingInfoMap.get(logRecord.getType()))){
                ProcessingInfo p = new ProcessingInfo(logRecord.getType(),logRecord.getTimestamp(),
                        logRecord.getTimestamp().plusSeconds(configMap.get(logRecord.getType()).getDurationOfWindow()),
                        0);
                processingInfoMap.put(logRecord.getType(),p);
            }
            ProcessingInfo p = processingInfoMap.get(logRecord.getType());
            if(p.isWait() && logRecord.getTimestamp().isBefore(p.getWaitEnd())){
                // do nothing
                return null;
            }else if(p.isWait() && !logRecord.getTimestamp().isBefore(p.getWaitEnd())){
                p.setWait(false);
                applyChecks(p,logRecord);
            }else {
                applyChecks(p, logRecord);
            }
            return null;
        });
    }

    @Override
    public void notifySubscribers(EVENT_TYPE event_type) {
        notifier.notifyUpdate(event_type,"Alert!!");
    }

    void applyChecks(ProcessingInfo p, LogRecord logRecord){
        if((logRecord.getTimestamp().isBefore(p.getWindowEnd()) || logRecord.getTimestamp().equals(p.getWindowEnd()))
                &&
                (p.getNumberOfEvents()<=configMap.get(logRecord.getType()).getFrequency())){
            p.setNumberOfEvents(p.getNumberOfEvents()+1);
            processingInfoMap.put(logRecord.getType(),p);
        }else if(logRecord.getTimestamp().isAfter(p.getWindowEnd()) || logRecord.getTimestamp().equals(p.getWindowEnd())){
            p.setWindowStart(logRecord.getTimestamp());
            p.setWindowEnd(logRecord.getTimestamp().plusSeconds(configMap.get(logRecord.getType()).getDurationOfWindow()));
            p.setNumberOfEvents(0);
            applyChecks(p,logRecord);
        }else if(p.getNumberOfEvents()>configMap.get(logRecord.getType()).getFrequency()){
            notifySubscribers(logRecord.getType());
            p.setWait(true);
            p.setWaitEnd(logRecord.getTimestamp().plusSeconds(configMap.get(logRecord.getType()).getWaiTime()));
        }
    }

}
