import data.State;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.script.Script;

import org.osbot.rs07.script.ScriptManifest;
import tasks.BankCowhides;
import tasks.KillCows;
import tasks.NavCows;

import java.awt.*;

@ScriptManifest(name = "CowKiller2", author = "Tsb", version = 1.0, info = "", logo = "")
public final class CowKiller2Main extends Script {

    private String status = "";
    private final Area COW_AREA = new Area(3193, 3281, 3211, 3302);

    // Initialize task classes
    private final NavCows navCows = new NavCows();
    private final KillCows killCows = new KillCows();
    private final BankCowhides bankCowhides = new BankCowhides();

    @Override
    public void onStart() {
        // Exchange context to allow OSBot methods in task classes
        navCows.exchangeContext(getBot());
        killCows.exchangeContext(getBot());
        bankCowhides.exchangeContext(getBot());

    }

    @Override
    public void onExit() {
        //Code here will execute after the script ends
    }

    @Override
    public int onLoop() throws InterruptedException {
        // Grab current state and convert to string
        final State state = getState();
        status = state.toString();

        // Choose task based on state
        switch (state) {
            case NAV_COWS:
                navCows.NavCows();
            case KILL_COWS:
                killCows.KillCows();
            case BANK_COWHIDES:
                bankCowhides.BankCowhides();
        }
        // end current loop and wait between 250 and 400 miliseconds before next loop
        return random(250, 400);
    }

    @Override
    public void onPaint(Graphics2D g) {
        //This is where you will put your code for paint(s)
    }

    private State getState() {

        // Return proper state based on inventory and position
        if (getInventory().isFull()) {
            return State.BANK_COWHIDES;
        } else if (!COW_AREA.contains(myPosition())) {
            return State.NAV_COWS;
        } else {
            return State.KILL_COWS;
        }
    }
}