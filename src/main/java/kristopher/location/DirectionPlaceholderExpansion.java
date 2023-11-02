package kristopher.location;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class DirectionPlaceholderExpansion extends PlaceholderExpansion {
    @Override
    public String onPlaceholderRequest(Player player, String params) {
        if (params.equalsIgnoreCase( "test")) {
            return "Test!";
        }
        if  (params.startsWith("direction_")) {
            String[] parts = params.split( "_");
            String directionStr = parts[0];
            String xStr = parts[1];
            String yStr = parts[2];
            String zStr = parts[3];

            try {
                int x = Integer.parseInt(xStr);
                int y = Integer.parseInt(yStr);
                int z = Integer.parseInt(zStr);

                Location playerLocation = player.getLocation();
                Location targetLocation = new Location(player.getWorld(), x, y, z);

                return this.getDirectionBetweenLocations(playerLocation, targetLocation);

            } catch (NumberFormatException e) {
                return super.onPlaceholderRequest(player, params);

            }
        } else if (params.startsWith("height_")) {
            String[] parts = params.split("_");
            String yStr = parts[1];

            try {
                int y = Integer.parseInt(yStr);

                int playerY = player.getLocation().getBlockY();

                if (playerY > y) {
                    return "Lower";
                } else if (playerY < y) {
                    return "Higher";
                } else {
                    return "";
                }
            } catch (NumberFormatException e) {
                return super.onPlaceholderRequest(player, params);
            }
        }




        return super.onPlaceholderRequest(player, params);
    }
    @Override
    public @NotNull String getIdentifier() {
        return "location";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Kristopher";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }
    public String getDirectionBetweenLocations(Location loc1, Location loc2) {
        Vector vector = loc1.toVector().subtract(loc2.toVector());
        double angle = Math.atan2(vector.getZ(), vector.getX());

        double degrees = Math.toDegrees(angle);

        if (degrees < 0) {
            degrees += 360;
        }

        String direction;
        if (degrees >= 337.5 || degrees < 22.5) {
            direction = "W"; //West
        } else if (degrees >= 22.5 && degrees < 67.5) {
            direction = "NW"; //North West
        } else if (degrees >= 67.5 && degrees < 112.5) {
            direction = "N"; //North
        } else if (degrees >= 112.5 && degrees < 157.5) {
            direction = "NE"; //North East
        } else if (degrees >= 157.5 && degrees < 202.5) {
            direction = "E"; //East
        } else if (degrees >= 202.5 && degrees < 247.5) {
            direction = "SE"; //South East
        } else if (degrees >= 247.5 && degrees < 292.5) {
            direction = "S"; //South
        } else {
            direction = "SW"; // South West
        }

        return direction;
    }
}
