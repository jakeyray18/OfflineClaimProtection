package OfflineClaimProtection.Register;

import OfflineClaimProtection.Listeners.ClaimEnter;
import OfflineClaimProtection.OCP;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Events {

    OCP plugin;
    public Events(OCP instance) {
        plugin = instance;
    }

    PluginManager pm = Bukkit.getPluginManager();

    public void registerListeners() {
        pm.registerEvents(new ClaimEnter(plugin), plugin);
    }

}
