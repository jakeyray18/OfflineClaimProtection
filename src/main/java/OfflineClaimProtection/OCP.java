package OfflineClaimProtection;

import OfflineClaimProtection.Register.Commands;
import OfflineClaimProtection.Register.Events;
import org.bukkit.plugin.java.JavaPlugin;

public class OCP extends JavaPlugin {

    public void onEnable() {
        startUp();
    }

    public void onDisable() {

    }

    private void startUp() {
        registerEvents();
        registerCommands();
    }

    private void registerEvents() {
        Events re = new Events(this);
        re.registerListeners();
    }

    private void registerCommands() {
        Commands rc = new Commands(this);
        rc.registerCommands();
    }

}
