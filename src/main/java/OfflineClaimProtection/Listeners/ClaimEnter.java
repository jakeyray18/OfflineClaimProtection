package OfflineClaimProtection.Listeners;

import OfflineClaimProtection.Logger.Logger;
import OfflineClaimProtection.OCP;
import net.kaikk.mc.gpp.Claim;
import net.kaikk.mc.gpp.GriefPreventionPlus;
import net.kaikk.mc.gpp.events.ClaimEnterEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.logging.Level;

public class ClaimEnter implements Listener {

    OCP plugin;
    public ClaimEnter(OCP instance) {
        plugin = instance;
    }

    @EventHandler
    public void onEnterClaim(ClaimEnterEvent event) {
        Claim claim = event.getClaim();
        if (claim.isAdminClaim()) {
            return;
        }
        Logger logger = new Logger(plugin);
        logger.log(event.getPlayer().getName() + " entered claim id " + claim.getID().toString() + " owned by " + claim.getOwnerName());
        Player owner = Bukkit.getPlayer(event.getClaim().getOwnerID());
        if ((plugin.getServer().getPlayer(owner.getUniqueId()).isOnline() && plugin.getServer().getPlayer(owner.getUniqueId()) != null)) {
            return;
        }
        if (claim.canAccess(event.getPlayer()) == null) {
            plugin.getLogger().log(Level.INFO , event.getPlayer() + " enter claim " + claim.getID() + " owned by " + claim.getOwnerName());
            GriefPreventionPlus.getInstance().ejectPlayer(event.getPlayer());
            logger.log(event.getPlayer().getName() + " was removed from claim with id " + claim.getID() + " owned by " + claim.getOwnerName());
        }

    }

}
