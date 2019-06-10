package net.noodles.report.main.inv;

import net.noodles.report.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Items {

    public static ItemStack Glass(Player p) {
        ItemStack stone = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)7);
        ItemMeta stonem = stone.getItemMeta();
        stonem.setDisplayName(" ");
        stone.setItemMeta(stonem);
        return stone;
    }
	
    
    private static String getColor(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }


    public static ItemStack Hacking(Player p) {
        ItemStack stone = new ItemStack(Material.getMaterial(Main.plugin.getGUIItemsConfig().getString("HackingReportItem")));
        ItemMeta stonem = stone.getItemMeta();
        stonem.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.getGUIItemsConfig().getString("HackingReportItemName")));
        ArrayList<String> lore = new ArrayList<>();
        List<String> stringList = Main.plugin.getGUIItemsConfig().getStringList("HackingReportItemLore");
        IntStream.range(0, stringList.size()).forEach(i -> lore.add(getColor(stringList.get(i))));
        stonem.setLore(lore);
        stone.setItemMeta(stonem);
        return stone;
    }

    public static ItemStack ChatOffences(Player p) {
        ItemStack stone = new ItemStack(Material.getMaterial(Main.plugin.getGUIItemsConfig().getString("ChatReportItem")));
        ItemMeta stonem = stone.getItemMeta();
        stonem.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.getGUIItemsConfig().getString("ChatReportItemName")));
        ArrayList<String> lore = new ArrayList<>();
        List<String> stringList = Main.plugin.getGUIItemsConfig().getStringList("ChatReportItemLore");
        IntStream.range(0, stringList.size()).forEach(i -> lore.add(getColor(stringList.get(i))));
        stonem.setLore(lore);
        stone.setItemMeta(stonem);
        return stone;
    }


    public static ItemStack BugExploiting(Player p) {
        ItemStack stone = new ItemStack(Material.getMaterial(Main.plugin.getGUIItemsConfig().getString("BugExploitingReportItem")));
        ItemMeta stonem = stone.getItemMeta();
        stonem.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.getGUIItemsConfig().getString("BugExploitingReportItemName")));
        ArrayList<String> lore = new ArrayList<>();
        List<String> stringList = Main.plugin.getGUIItemsConfig().getStringList("BugExploitingReportItemLore");
        IntStream.range(0, stringList.size()).forEach(i -> lore.add(getColor(stringList.get(i))));
        stonem.setLore(lore);
        stone.setItemMeta(stonem);
        return stone;
    }

    public static ItemStack TeamTrolling(Player p) {
        ItemStack stone = new ItemStack(Material.getMaterial(Main.plugin.getGUIItemsConfig().getString("TeamTrollingReportItem")));
        ItemMeta stonem = stone.getItemMeta();
        stonem.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.getGUIItemsConfig().getString("TeamTrollingReportItemName")));
        ArrayList<String> lore = new ArrayList<>();
        List<String> stringList = Main.plugin.getGUIItemsConfig().getStringList("TeamTrollingReportItemLore");
        IntStream.range(0, stringList.size()).forEach(i -> lore.add(getColor(stringList.get(i))));
        stonem.setLore(lore);
        stone.setItemMeta(stonem);
        return stone;
    }

    public static ItemStack Threat(Player p) {
        ItemStack stone = new ItemStack(Material.getMaterial(Main.plugin.getGUIItemsConfig().getString("ThreatReportItem")));
        ItemMeta stonem = stone.getItemMeta();
        stonem.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.getGUIItemsConfig().getString("ThreatReportItemName")));
        ArrayList<String> lore = new ArrayList<>();
        List<String> stringList = Main.plugin.getGUIItemsConfig().getStringList("ThreatReportItemLore");
        IntStream.range(0, stringList.size()).forEach(i -> lore.add(getColor(stringList.get(i))));
        stonem.setLore(lore);
        stone.setItemMeta(stonem);
        return stone;
    }
    
}
