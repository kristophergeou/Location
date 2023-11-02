package kristopher.location;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Location extends JavaPlugin {

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new DirectionPlaceholderExpansion().register();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
