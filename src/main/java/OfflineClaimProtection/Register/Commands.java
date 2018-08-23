package OfflineClaimProtection.Register;

import OfflineClaimProtection.Commands.RemoveEnters;
import OfflineClaimProtection.OCP;

public class Commands {

    OCP plugin;
    public Commands(OCP instance) {
        plugin = instance;
    }

    public void registerCommands() {
        plugin.getCommand("clearentrytrust").setExecutor(new RemoveEnters(plugin));
    }
}
