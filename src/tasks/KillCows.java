package tasks;

import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.MethodProvider;
import util.Sleep;

public class KillCows extends MethodProvider {
    public final void KillCows() throws InterruptedException {
        // Toggle run only if above 40 energy
        if (getSettings().getRunEnergy() >40) {
            getSettings().setRunning(true);
        }

        // Grab cows not in combat and not in dying state
        NPC cow = getNpcs().closest(new Filter<NPC>() {
            public boolean match(NPC npc) {
                return npc != null && npc.getName().equals("Cow") && !npc.isUnderAttack() && npc.getHealthPercent() > 0;
            }
        });

        // Check for cowhides and grab with proper conditional sleep
        GroundItem cowhide = getGroundItems().closest("Cowhide");
        if (myPlayer().isUnderAttack()) {
            Sleep.sleepUntil(() -> myPlayer().isAnimating() || !myPlayer().isUnderAttack(), 5000);
        }else if (cowhide != null && cowhide.interact("take")) {
            Sleep.sleepUntil(() -> myPlayer().isAnimating() || !cowhide.exists(), 5000);
        } else if (cow != null && cow.interact("Attack")) {
            Sleep.sleepUntil(() -> myPlayer().isAnimating() || !cow.exists(), 5000);
        } else {
            sleep(500);
        }
    }
}