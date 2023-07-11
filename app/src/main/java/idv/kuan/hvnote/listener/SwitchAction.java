package idv.kuan.hvnote.listener;

import java.util.ArrayList;

import idv.kuan.libs.observers.Observer;
import idv.kuan.libs.observers.Subject;

public class SwitchAction implements Subject<String> {
    private ArrayList<Observer<String>> observers = new ArrayList<>();
    private String toggle;

    @Override
    public void registerObserver(Observer<String> observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<String> observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer<String> observer : observers) {
            observer.update(toggle);
        }
    }

    public void setToggle(String toggle) {
        this.toggle = toggle;
        notifyObservers();
    }

}
