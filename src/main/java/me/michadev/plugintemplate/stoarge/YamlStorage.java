package me.michadev.plugintemplate.stoarge;

import lombok.SneakyThrows;
import me.michadev.plugintemplate.utils.Common;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class YamlStorage {

    public static List<YamlStorage> savedConfigs = new ArrayList<>();

    private String name;
    private File file;
    private YamlConfiguration configuration;

    public YamlStorage(String parent, String name) {
        String dataPath = Common.getPluginDataFolder().getAbsolutePath() + File.separator;

        this.name = name.endsWith(".yml") ? name : name + ".yml";
        this.file = new File(dataPath + parent, this.name);
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
        verify();
        savedConfigs.add(this);
    }

    public YamlStorage(String name) {
        String dataPath = Common.getPluginDataFolder().getAbsolutePath() + File.separator;

        this.name = name.endsWith(".yml") ? name : name + ".yml";
        this.file = new File(dataPath, this.name);
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
        verify();
        savedConfigs.add(this);
    }

    @SneakyThrows
    public void verify() {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } else {
            reload();
        }
    }

    @SneakyThrows
    public void save() {
        this.configuration.save(file);
        reload();
    }

    public void reload() {
        this.configuration = YamlConfiguration.loadConfiguration(file);
    }

    public void set(String path, Object object) {
        configuration.set(path, object);
        save();
    }

    @SneakyThrows
    public void deleteLine(String partialLine) {
        List<String> strings = Files.readAllLines(file.toPath());

        strings.forEach(s-> {
            if (s.contains(partialLine)) {
                strings.remove(strings.indexOf(s));
            }
        });
    }

    public YamlConfiguration getYaml() {
        return configuration;
    }

    public File getFile() {
        return file;
    }

    @SneakyThrows
    public boolean isEmpty() {
        return Files.readAllLines(file.toPath()).isEmpty();
    }
}
