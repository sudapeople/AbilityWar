package daybreak.abilitywar.utils.base.minecraft.ability.list.grapplinghook;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * 그랩플링 훅 유틸리티 클래스
 * @author Daybreak 새벽
 */
public class GrapplingHook {
    
    /**
     * 그랩플링 훅을 생성합니다.
     */
    public static HookEntity createHook(Player player, Location target) {
        return new HookEntity(player, target);
    }
    
    /**
     * 그랩플링 훅을 생성합니다.
     */
    public static HookEntity createHook(Player player, Vector direction, double distance) {
        Location target = player.getLocation().add(direction.multiply(distance));
        return new HookEntity(player, target);
    }
    
    /**
     * 훅 엔티티 클래스
     */
    public static class HookEntity {
        private final Player player;
        private final Location target;
        private boolean active = true;
        
        public HookEntity(Player player, Location target) {
            this.player = player;
            this.target = target;
        }
        
        public Player getPlayer() {
            return player;
        }
        
        public Location getTarget() {
            return target;
        }
        
        public boolean isActive() {
            return active;
        }
        
        public void setActive(boolean active) {
            this.active = active;
        }
        
        public void pull() {
            if (active) {
                Vector direction = target.toVector().subtract(player.getLocation().toVector()).normalize();
                player.setVelocity(direction.multiply(1.5));
            }
        }
    }
}
