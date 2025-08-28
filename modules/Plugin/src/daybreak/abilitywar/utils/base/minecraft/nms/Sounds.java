package daybreak.abilitywar.utils.base.minecraft.nms;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * 사운드 관련 NMS 클래스
 * @author Daybreak 새벽
 */
public class Sounds {
    
    /**
     * 플레이어에게 사운드를 재생합니다.
     */
    public static void playSound(Player player, Sound sound, float volume, float pitch) {
        player.playSound(player.getLocation(), sound, volume, pitch);
    }
    
    /**
     * 위치에서 사운드를 재생합니다.
     */
    public static void playSound(Location location, Sound sound, float volume, float pitch) {
        location.getWorld().playSound(location, sound, volume, pitch);
    }
    
    /**
     * 모든 플레이어에게 사운드를 재생합니다.
     */
    public static void playSoundToAll(Location location, Sound sound, float volume, float pitch) {
        location.getWorld().playSound(location, sound, volume, pitch);
    }
    
    /**
     * 사운드가 처리되는지 확인합니다.
     */
    public static boolean isHandled() {
        return true;
    }
    
    /**
     * 사운드를 재생합니다.
     */
    public static void playSound(String sound, double x, double y, double z, float volume, float pitch) {
        // 더미 구현
    }
    
    /**
     * 플레이어에게 사운드를 재생합니다.
     */
    public static void playSound(Player player, String sound, double x, double y, double z, float volume, float pitch) {
        // 더미 구현
    }
}
