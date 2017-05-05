package events;

public class Controller {
	private EventSet es = new EventSet();

	public void addEvent(Event c) {
		es.add(c);
	}

	public void run() {
		Event e;
		while ((e = es.getNext()) != null) {
			if (e.ready()) {
				e.action();
				System.out.println(e.description());
				es.removeCurrent();
			}
		}
	}
} /// :~