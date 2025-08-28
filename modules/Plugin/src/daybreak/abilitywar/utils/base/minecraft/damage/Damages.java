package daybreak.abilitywar.utils.base.minecraft.damage;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * 데미지 관련 유틸리티 클래스
 * @author Daybreak 새벽
 */
public class Damages {
    
    public static class INSTANCE {
        public static class Flag {
            public static final int NO_KNOCKBACK = 0x1;
            public static final int NO_ARMOR = 0x2;
            public static final int NO_ENCHANT = 0x4;
            public static final int NO_POTION = 0x8;
        }
    }
    
    /**
     * 엔티티에게 데미지를 줍니다.
     */
    public static void damage(Entity entity, double damage) {
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).damage(damage);
        }
    }
    
    /**
     * 엔티티에게 데미지를 줍니다.
     */
    public static void damage(Entity entity, double damage, Entity damager) {
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).damage(damage, damager);
        }
    }
    
    /**
     * 엔티티에게 데미지를 줍니다.
     */
    public static void damage(Entity entity, double damage, EntityDamageEvent.DamageCause cause) {
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).damage(damage);
        }
    }
    
    /**
     * 플레이어의 체력을 설정합니다.
     */
    public static void setHealth(Player player, double health) {
        player.setHealth(Math.max(0, Math.min(health, player.getMaxHealth())));
    }
    
    /**
     * 플레이어의 최대 체력을 설정합니다.
     */
    public static void setMaxHealth(Player player, double maxHealth) {
        player.setMaxHealth(maxHealth);
    }
    
    /**
     * 엔티티가 살아있는지 확인합니다.
     */
    public static boolean isAlive(Entity entity) {
        if (entity instanceof LivingEntity) {
            return ((LivingEntity) entity).getHealth() > 0;
        }
        return true;
    }
    
    /**
     * 엔티티를 죽입니다.
     */
    public static void kill(Entity entity) {
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).setHealth(0);
        }
    }
    
    /**
     * 가시 데미지를 줍니다.
     */
    public static void damageThorn(org.bukkit.entity.LivingEntity entity, org.bukkit.entity.Player damager, float damage) {
        entity.damage(damage, damager);
    }
    
    /**
     * 데미지 가능 여부를 확인합니다.
     */
    public static boolean canDamage(org.bukkit.entity.LivingEntity entity, org.bukkit.event.entity.EntityDamageEvent.DamageCause cause, double damage) {
        return entity.getHealth() > 0;
    }
    
    /**
     * 고정 데미지를 줍니다.
     */
    public static void damageFixed(org.bukkit.entity.Entity entity, org.bukkit.entity.Player damager, float damage) {
        if (entity instanceof org.bukkit.entity.LivingEntity) {
            ((org.bukkit.entity.LivingEntity) entity).damage(damage, damager);
        }
    }
    
    /**
     * 마법 데미지를 줍니다.
     */
    public static void damageMagic(org.bukkit.entity.LivingEntity entity, org.bukkit.entity.Player damager, boolean critical, float damage) {
        entity.damage(damage, damager);
    }
}
