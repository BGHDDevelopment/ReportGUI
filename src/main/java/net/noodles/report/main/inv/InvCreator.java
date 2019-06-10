package net.noodles.report.main.inv;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class InvCreator {

	    public static Inventory Main;
	    
	    static {
	        InvCreator.Main = Bukkit.createInventory(null, 9, InvNames.Main);
	    }
}
