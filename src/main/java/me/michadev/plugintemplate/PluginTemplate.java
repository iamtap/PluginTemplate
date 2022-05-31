package me.michadev.plugintemplate;

import me.michadev.plugintemplate.stoarge.YamlStorage;
import org.bukkit.plugin.java.JavaPlugin;

public final class PluginTemplate extends JavaPlugin {

    private static PluginTemplate instance = null;
    private static YamlStorage configuration = null;
    private static YamlStorage messages = null;

    public static PluginTemplate getInstance() {
        return instance;
    }

    public static YamlStorage getConfiguration() {
        return configuration;
    }

    public static YamlStorage getMessages() {
        return messages;
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {
    }
  
    /**
     * Creates basic configuration files (i.e config.yml, and messages.yml).
     */
    private void setupDefaultConfigurations() {
        instance = this;
        messages = new YamlStorage("messages.yml");
        saveDefaultConfig();
        configuration = new YamlStorage("config.yml");
    }
}
