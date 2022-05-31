package me.michadev.plugintemplate.utils;

import lombok.SneakyThrows;
import me.michadev.plugintemplate.PluginTemplate;
import me.michadev.plugintemplate.stoarge.YamlStorage;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Level;

public class Common {

    public static PluginTemplate getPluginInstance() {
        return PluginTemplate.getInstance();
    }

    public static String getPluginName() {
        return getPluginInstance().getName();
    }

    public static File getPluginDataFolder() {
        return getPluginInstance().getDataFolder();
    }

    @SneakyThrows
    public static File createPluginFile(String name) {
        File file = new File(getPluginDataFolder().getAbsolutePath(), name);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        return file;
    }

    public static String colorize(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static void msg(CommandSender sender, String... messages) {
        Arrays.asList(messages).forEach(m -> sender.sendMessage(colorize(m)));
    }

    public static void msg(CommandSender sender, TextComponent... messages) {
        Arrays.asList(messages).forEach(textComponent -> sender.spigot().sendMessage(textComponent));
    }

    public static String getMessage(String path) {
        String message = PluginTemplate.getMessages().getYaml().getString(path);
        return colorize(message);
    }

    /**
     * @param player   The player to message.
     * @param messages The messages to send to the player. (auto-colorized)
     * @return false if the player is offline, true if the player is online.
     */
    public static boolean attemptMsg(OfflinePlayer player, String... messages) {
        if (player.getPlayer() != null)
            if (player.getPlayer().isOnline())
                Arrays.asList(messages).forEach(msg -> player.getPlayer().sendMessage(colorize(msg)));
        return ((player.getPlayer() != null) && player.getPlayer().isOnline());
    }

    public static void log(Level level, String... messages) {
        Arrays.asList(messages).forEach(m -> Bukkit.getLogger().log(level, colorize(m)));
    }

    public static YamlStorage getPlayerData(UUID uuid) {
        return new YamlStorage("player-data", uuid.toString());
    }

    public static YamlStorage getConfigByName(String name) {
        name = name.endsWith(".yml") ? name : name + ".yml";
        YamlStorage configuration = null;

        for (YamlStorage savedConfig : YamlStorage.savedConfigs) {
            if (savedConfig.getFile().getName().equalsIgnoreCase(name))
                configuration = savedConfig;
        }

        return configuration;
    }

    public static YamlStorage getConfig() {
        return PluginTemplate.getConfiguration();
    }

    public static OfflinePlayer getOfflinePlayer(String username, boolean ignoreCase) {
        OfflinePlayer offlinePlayer = null;
        OfflinePlayer[] offlinePlayers = Bukkit.getOfflinePlayers();

        for (OfflinePlayer offlinePlayer1 : offlinePlayers) {
            if (offlinePlayer1.getName() == null) continue;

            if (ignoreCase)
                if (offlinePlayer.getName().equalsIgnoreCase(username)) {
                    offlinePlayer = offlinePlayer1;
                    break;
                }
            else
                if (offlinePlayer.getName().equals(username)) {
                    offlinePlayer = offlinePlayer1;
                    break;
                }

        }

        return offlinePlayer;
    }

}

