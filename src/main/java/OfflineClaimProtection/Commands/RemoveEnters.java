package OfflineClaimProtection.Commands;

import OfflineClaimProtection.OCP;
import net.kaikk.mc.gpp.Claim;
import net.kaikk.mc.gpp.GriefPreventionPlus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;

public class RemoveEnters implements CommandExecutor {

    OCP plugin;
    public RemoveEnters(OCP instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("clearentrytrust")) {
            plugin.getLogger().log(Level.INFO, "Is my command");
            if (commandSender.isOp() || (commandSender instanceof Player && commandSender.getName().equalsIgnoreCase("GungnirIncarnate"))){
                plugin.getLogger().log(Level.INFO, "Sender is OP");
                commandSender.sendMessage(ChatColor.RED + "Processing...");

                GriefPreventionPlus gpp = GriefPreventionPlus.getInstance();
                Map<Integer, Claim> claims = gpp.getDataStore().getClaims();

                plugin.getLogger().log(Level.INFO, "Starting iteration");
                for (Map.Entry<Integer, Claim> claimM : claims.entrySet()) {
                    Claim claim = gpp.getDataStore().getClaim(claimM.getKey());

                    ArrayList<String> builders = new ArrayList<String>();
                    ArrayList<String> containers = new ArrayList<String>();
                    ArrayList<String> accessors = new ArrayList<String>();
                    ArrayList<String> enters = new ArrayList<String>();
                    ArrayList<String> managers = new ArrayList<String>();

                    claim.getPermissions(builders, containers, accessors, enters, managers);

                    for (String a : enters) {
                        plugin.getLogger().log(Level.INFO, "player " + a + " had entry.");
                        if (claim.getPermissionMapPlayers().containsKey(Bukkit.getPlayer(a))) {
                            plugin.getLogger().log(Level.INFO, "Dropping " + a + ".");
                            claim.dropPermission(Bukkit.getPlayer(a).getUniqueId());
                            gpp.getDataStore().savePlayerData(Bukkit.getPlayer(a).getUniqueId(), );
                        }
                    }
                }
                commandSender.sendMessage(ChatColor.RED + "All claim entry lists have been cleared");
                return true;
            }
        }
        plugin.getLogger().log(Level.INFO, "Not my command?");
        return false;
    }
}
