package net.noodles.report.main;

import net.noodles.report.main.commands.Report;
import net.noodles.report.main.updatechecker.UpdateChecker;
import net.noodles.report.main.util.Logger;
import net.noodles.report.main.util.MetricsLite;
import net.noodles.report.main.util.Settings;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    public static Main plugin;
    private UpdateChecker checker;


    @Override
    public void onEnable() {
        // Plugin startup logic
        Logger.log(Logger.LogLevel.OUTLINE,  "********************");
        Logger.log(Logger.LogLevel.INFO, "Initializing ReportGUI Version: " + Settings.VERSION);
        Logger.log(Logger.LogLevel.INFO, "Created by: " + Settings.DEVELOPER_NAME);
        Logger.log(Logger.LogLevel.INFO, "Website: " + Settings.DEVELOPER_URL);
        Logger.log(Logger.LogLevel.INFO, "Spigot Link: " + Settings.PLUGIN_URL);
        Logger.log(Logger.LogLevel.INFO, "Support Link: " + Settings.SUPPORT_DISCORD_URL);
        Logger.log(Logger.LogLevel.OUTLINE,  "********************");
        Logger.log(Logger.LogLevel.INFO, "Plugin Loading...");
        Logger.log(Logger.LogLevel.INFO, "Registering Managers...");
        plugin = this;
        MetricsLite metrics = new MetricsLite(this);
        Logger.log(Logger.LogLevel.INFO, "Managers Registered!");
        Logger.log(Logger.LogLevel.INFO, "Registering Listeners...");
        registerEvents();
        Logger.log(Logger.LogLevel.INFO, "Listeners Registered!");
        Logger.log(Logger.LogLevel.INFO, "Registering Commands...");
        registerCommands();
        Logger.log(Logger.LogLevel.INFO, "Commands Registered!");
        Logger.log(Logger.LogLevel.INFO, "Loading Config's...");
        createFiles();
        Logger.log(Logger.LogLevel.INFO, "Config's Registered!");
        Logger.log(Logger.LogLevel.SUCCESS, "ReportGUI Version: " + Settings.VERSION + " Loaded.");
        this.setEnabled(true);
        Logger.log(Logger.LogLevel.OUTLINE,  "********************");
        Logger.log(Logger.LogLevel.INFO, "Checking for updates...");
        this.checker = new UpdateChecker(this);
        if (this.checker.isConnected()) {
            if (this.checker.hasUpdate()) {
                Logger.log(Logger.LogLevel.OUTLINE,  "********************");
                Logger.log(Logger.LogLevel.WARNING,("ReportGUI is outdated!"));
                Logger.log(Logger.LogLevel.WARNING,("Newest version: " + this.checker.getLatestVersion()));
                Logger.log(Logger.LogLevel.WARNING,("Your version: " + Settings.VERSION));
                Logger.log(Logger.LogLevel.WARNING,("Please Update Here: " + Settings.PLUGIN_URL));
                Logger.log(Logger.LogLevel.OUTLINE,  "********************");
            }
            else {
                Logger.log(Logger.LogLevel.SUCCESS, "ReportGUI is up to date!");
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerEvents() {
        final PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new Report(), this);
    }
    public void registerCommands() {
        this.getCommand("report").setExecutor(new Report());

    }


    private File configf, guiitemsf;
    private FileConfiguration config, guiitems;


    public FileConfiguration getGUIItemsConfig() {
        return this.guiitems;
    }

    private void createFiles() {
        configf = new File(getDataFolder(), "config.yml");
        guiitemsf = new File(getDataFolder(), "guiitems.yml");


        if (!configf.exists()) {
            configf.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
        if (!guiitemsf.exists()) {
            guiitemsf.getParentFile().mkdirs();
            saveResource("guiitems.yml", false);
        }

        config = new YamlConfiguration();
        guiitems = new YamlConfiguration();

        try {
            config.load(configf);
            guiitems.load(guiitemsf);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }


    }


}
