package com.halodoc.entities;

import java.util.*;

public class Notifier {
    Map<EVENT_TYPE,Map<String,IObserver>> observerCache = new HashMap<>();

    void addObserverForGivenType(EVENT_TYPE type, String observerId, IObserver observer){
        if(Objects.isNull(observerCache.get(type))){
            observerCache.put(type,new HashMap<>());
        }
        observerCache.get(type).put(observerId,observer);
    }

    void removeObserver(EVENT_TYPE type, String observerId){
        if(Objects.isNull(observerCache.get(type))){
            return;
        }
        if(observerCache.get(type).isEmpty()){
            return;
        }
        observerCache.get(type).remove(observerId);
    }

   public void notifyUpdate(EVENT_TYPE type, String message){
        Map<String,IObserver> observers = observerCache.get(type);
        observers.values().forEach(observer -> observer.update(message));
    }

}
