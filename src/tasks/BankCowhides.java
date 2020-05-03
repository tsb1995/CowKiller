package tasks;

import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.event.WebWalkEvent;
import org.osbot.rs07.event.webwalk.PathPreferenceProfile;
import org.osbot.rs07.script.MethodProvider;

public class BankCowhides extends MethodProvider {

    public final void BankCowhides() throws InterruptedException {
        // If not at bank and full inventory, got to the bank
        if (!Banks.LUMBRIDGE_UPPER.contains(myPosition()) && getInventory().isFull()) {
            WebWalkEvent webEvent = new WebWalkEvent(Banks.LUMBRIDGE_UPPER);
            webEvent.useSimplePath();
            PathPreferenceProfile ppp = new PathPreferenceProfile();
            ppp.setAllowTeleports(false);
            webEvent.setPathPreferenceProfile(ppp);
            execute(webEvent);
            sleep(300);
        } else {
            // if at bank, open bank and deposit all
            if (!getBank().isOpen()) {
                getBank().open();
                sleep(300);
            }
            getBank().depositAll();
        }
    }
}