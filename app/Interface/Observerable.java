package Interface;

import java.util.Observer;

import subjekt.User;

public interface Observerable {

	public void addObserver(Observer o);

	public void removeObserver(Observer o);

	public void notifyObservers();

}
