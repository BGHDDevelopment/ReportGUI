package net.noodles.report.main;

import net.noodles.report.main.inv.InvCreator;
import net.noodles.report.main.inv.InvNames;
import net.noodles.report.main.inv.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Report implements Listener, CommandExecutor {

	public static Player bannedPlayer;

	public Report() {
		this.bannedPlayer = null;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
		if (!(sender instanceof Player)) {
			Bukkit.getServer().getLogger().warning(Main.plugin.getConfig().getString("NoConsole"));
			return true;
		}
		final Player p = (Player) sender;
		if (!cmd.getName().equalsIgnoreCase("report")) {
			return true;
		}
		if (args.length < 1) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("InvalidUsage")));
			return true;
		}
		if (args.length == 1) {
			bannedPlayer = Bukkit.getPlayer(args[0]);
		}
		if (!sender.hasPermission("report.use")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("NoPermission")));
			return true;
		}
		if (args.length > 1) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("InvalidUsage")));
			return true;
		}
		if (bannedPlayer == null) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("OfflinePlayer")));
			return true;
		}

		InvCreator.Main.setItem(Main.plugin.getGUIItemsConfig().getInt("HackingReportItemLocation"), Items.Hacking(p));
		InvCreator.Main.setItem(Main.plugin.getGUIItemsConfig().getInt("ChatReportItemLocation"), Items.ChatOffences(p));
		InvCreator.Main.setItem(Main.plugin.getGUIItemsConfig().getInt("BugExploitingReportItemLocation"), Items.BugExploiting(p));
		InvCreator.Main.setItem(Main.plugin.getGUIItemsConfig().getInt("TeamTrollingReportItemLocation"), Items.TeamTrolling(p));
		InvCreator.Main.setItem(Main.plugin.getGUIItemsConfig().getInt("ThreatReportItemLocation"), Items.Threat(p));
		for (int i = 0; i < 9; ++i) {
			if (InvCreator.Main.getItem(i) == null) {
				InvCreator.Main.setItem(i, Items.Glass(p));
			}
		}
		p.openInventory(InvCreator.Main);
		return true;
	}


	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getView().getTitle().equals(null)) {
			return;
		}
		if (e.getCurrentItem() == null) {
			return;
		}
		if (e.getView().getTitle().equals(InvNames.Main)) {
			e.setCancelled(true);
			for(Player globalp : Bukkit.getOnlinePlayers()) {
				if (e.getCurrentItem().equals(Items.Hacking(p))) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("HackReport").replace("%player%", bannedPlayer.getName())));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("SentMessage")));
					p.closeInventory();
					if (globalp.hasPermission("report.receive")) {
						globalp.sendMessage("");
						globalp.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("HackReceive").replace("%player%", bannedPlayer.getName()).replace("%reporting%", p.getName())));
						globalp.sendMessage("");

					}
				}
				if (e.getCurrentItem().equals(Items.ChatOffences(p))) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("ChatReport").replace("%player%", bannedPlayer.getName())));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("SentMessage")));
					p.closeInventory();
					if (globalp.hasPermission("report.receive")) {
						globalp.sendMessage("");
						globalp.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("ChatReceive").replace("%player%", bannedPlayer.getName()).replace("%reporting%", p.getName())));
						globalp.sendMessage("");
					}
				}
				if (e.getCurrentItem().equals(Items.BugExploiting(p))) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("BugExploitingReport").replace("%player%", bannedPlayer.getName())));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("SentMessage")));
					p.closeInventory();
					if (globalp.hasPermission("report.receive")) {
						globalp.sendMessage("");
						globalp.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("BugExploitingReceive").replace("%player%", bannedPlayer.getName()).replace("%reporting%", p.getName())));
						globalp.sendMessage("");
					}
				}
				if (e.getCurrentItem().equals(Items.TeamTrolling(p))) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("TeamTrollingReport").replace("%player%", bannedPlayer.getName())));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("SentMessage")));
					p.closeInventory();
					if (globalp.hasPermission("report.receive")) {
						globalp.sendMessage("");
						globalp.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("TeamTrollingReceive").replace("%player%", bannedPlayer.getName()).replace("%reporting%", p.getName())));
						globalp.sendMessage("");
					}
				}
				if (e.getCurrentItem().equals(Items.Threat(p))) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("ThreatReport").replace("%player%", bannedPlayer.getName())));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("SentMessage")));
					p.closeInventory();
					if (globalp.hasPermission("report.receive")) {
						globalp.sendMessage("");
						globalp.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("ThreatReceive").replace("%player%", bannedPlayer.getName()).replace("%reporting%", p.getName())));
						globalp.sendMessage("");
					}
				}




			}
		}
	}

}

