package Interface;

import subjekt.User;

public interface Observer {
	
	
	public void register(String user);
	
	public void update();
	
	public void unregister(String user);

}
