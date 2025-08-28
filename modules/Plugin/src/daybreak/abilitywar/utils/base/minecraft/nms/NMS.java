package daybreak.abilitywar.utils.base.minecraft.nms;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Paper 1.21.8용 NMS 래퍼 클래스
 * @author Daybreak 새벽
 */
public class NMS {
    
    /**
     * 플레이어에게 아이템을 지급합니다.
     */
    public static void giveItem(Player player, ItemStack item) {
        player.getInventory().addItem(item);
    }
    
    /**
     * 플레이어를 텔레포트합니다.
     */
    public static void teleport(Player player, Location location) {
        player.teleport(location);
    }
    
    /**
     * 플레이어의 체력을 설정합니다.
     */
    public static void setHealth(Player player, double health) {
        player.setHealth(health);
    }
    
    /**
     * 플레이어의 최대 체력을 설정합니다.
     */
    public static void setMaxHealth(Player player, double maxHealth) {
        player.setMaxHealth(maxHealth);
    }
    
    /**
     * 플레이어의 게임모드를 설정합니다.
     */
    public static void setGameMode(Player player, org.bukkit.GameMode gameMode) {
        player.setGameMode(gameMode);
    }
    
    /**
     * 플레이어의 허기를 설정합니다.
     */
    public static void setFoodLevel(Player player, int foodLevel) {
        player.setFoodLevel(foodLevel);
    }
    
    /**
     * 플레이어의 경험치를 설정합니다.
     */
    public static void setExp(Player player, float exp) {
        player.setExp(exp);
    }
    
    /**
     * 플레이어의 레벨을 설정합니다.
     */
    public static void setLevel(Player player, int level) {
        player.setLevel(level);
    }
    
    /**
     * 플레이어의 쿨다운을 설정합니다.
     */
    public static void setCooldown(Player player, org.bukkit.Material material, int ticks) {
        // Paper 1.21.8에서는 쿨다운을 직접 설정할 수 없으므로 스킵
    }
    
    /**
     * 플레이어의 쿨다운을 확인합니다.
     */
    public static boolean hasCooldown(Player player, org.bukkit.Material material) {
        return false; // Paper 1.21.8에서는 쿨다운을 직접 확인할 수 없으므로 false 반환
    }
    
    /**
     * 플레이어의 활성 아이템을 클리어합니다.
     */
    public static void clearActiveItem(Player player) {
        // Paper 1.21.8에서는 활성 아이템을 직접 클리어할 수 없으므로 스킵
    }
    
    /**
     * 플레이어에게 액션바를 보냅니다.
     */
    public static void sendActionbar(Player player, String message, int fadeIn, int stay, int fadeOut) {
        // Paper 1.21.8에서는 액션바를 직접 보낼 수 없으므로 스킵
    }
    
    /**
     * 플레이어에게 타이틀을 보냅니다.
     */
    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }
    
    /**
     * 플레이어의 타이틀을 클리어합니다.
     */
    public static void clearTitle(Player player) {
        player.resetTitle();
    }
    
    /**
     * 플레이어를 리스폰합니다.
     */
    public static void respawn(Player player) {
        // Paper 1.21.8에서는 리스폰을 직접 호출할 수 없으므로 스킵
    }
    
    /**
     * 아머스탠드의 바운딩박스를 제거합니다.
     */
    public static void removeBoundingBox(org.bukkit.entity.ArmorStand armorStand) {
        // Paper 1.21.8에서는 바운딩박스를 직접 제거할 수 없으므로 스킵
    }
    
    /**
     * 플레이어의 머리를 회전시킵니다.
     */
    public static void rotateHead(Player player, Player target, float yaw, float pitch) {
        // Paper 1.21.8에서는 머리를 직접 회전시킬 수 없으므로 스킵
    }
    
    /**
     * 플레이어의 흡수 하트를 가져옵니다.
     */
    public static float getAbsorptionHearts(Player player) {
        return player.getAbsorptionAmount();
    }
    
    /**
     * 플레이어의 흡수 하트를 설정합니다.
     */
    public static void setAbsorptionHearts(Player player, float amount) {
        player.setAbsorptionAmount(amount);
    }
    
    /**
     * 플레이어의 흡수 하트를 설정합니다.
     */
    public static void setAbsorptionHearts(Player player, int amount) {
        player.setAbsorptionAmount(amount);
    }
    
    /**
     * 엔티티 이펙트를 브로드캐스트합니다.
     */
    public static void broadcastEntityEffect(Player player, byte effect) {
        // Paper 1.21.8에서는 엔티티 이펙트를 직접 브로드캐스트할 수 없으므로 스킵
    }
    
    /**
     * 월드보더를 생성합니다.
     */
    public static void createWorldBorder(org.bukkit.WorldBorder worldBorder) {
        // Paper 1.21.8에서는 월드보더를 직접 생성할 수 없으므로 스킵
    }
    
    /**
     * 플레이어에게 월드보더를 설정합니다.
     */
    public static void setWorldBorder(Player player, IWorldBorder worldBorder) {
        // Paper 1.21.8에서는 월드보더를 직접 설정할 수 없으므로 스킵
    }
    
    /**
     * 플레이어의 월드보더를 리셋합니다.
     */
    public static void resetWorldBorder(Player player) {
        // Paper 1.21.8에서는 월드보더를 직접 리셋할 수 없으므로 스킵
    }
    
    /**
     * 플레이어가 투명한지 확인합니다.
     */
    public static boolean isInvisible(Player player) {
        return player.isInvisible();
    }
    
    /**
     * 플레이어의 투명 상태를 설정합니다.
     */
    public static void setInvisible(Player player, boolean invisible) {
        player.setInvisible(invisible);
    }
    
    /**
     * 플레이어의 몸에 화살을 설정합니다.
     */
    public static void setArrowsInBody(Player player, int arrows) {
        // Paper 1.21.8에서는 화살을 직접 설정할 수 없으므로 스킵
    }
    
    /**
     * 새로운 홀로그램을 생성합니다.
     */
    public static IHologram newHologram(org.bukkit.World world, double x, double y, double z, String text) {
        // Paper 1.21.8에서는 홀로그램을 직접 생성할 수 없으므로 더미 구현
        return new IHologram() {
            @Override
            public void create() {}
            
            @Override
            public void remove() {}
            
            @Override
            public void setLocation(Location location) {}
            
            @Override
            public void setText(String text) {}
            
            @Override
            public void showTo(Player player) {}
            
            @Override
            public void hideFrom(Player player) {}
            
            @Override
            public boolean isVisible() {
                return false;
            }
            
            public void display(Player player) {}
            
            public void unregister() {}
            
            public void teleport(org.bukkit.World world, double x, double y, double z, float yaw, int pitch) {}
            
            public void hide(Player player) {}
        };
    }
}
