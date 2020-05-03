package tasks;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.event.WebWalkEvent;
import org.osbot.rs07.event.webwalk.PathPreferenceProfile;
import org.osbot.rs07.script.MethodProvider;

public class NavCows extends MethodProvider {
    // Nav to cow position

    final Position COW_POSITION = new Position(3200, 3290, 0);

    public final void NavCows() throws InterruptedException {
        WebWalkEvent webEvent = new WebWalkEvent(COW_POSITION);
        webEvent.useSimplePath();
        PathPreferenceProfile ppp = new PathPreferenceProfile();
        webEvent.setPathPreferenceProfile(ppp);
        execute(webEvent);
    }
}
