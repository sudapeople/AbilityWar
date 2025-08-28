package daybreak.abilitywar.utils.base.minecraft;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.FallingBlock;
import org.bukkit.util.Vector;

/**
 * 낙하 블록 유틸리티 클래스
 * @author Daybreak 새벽
 */
public class FallingBlocks {
    
    public enum Behavior {
        NORMAL,
        NO_GRAVITY,
        NO_DROP,
        FALSE
    }
    
    /**
     * 낙하 블록을 생성합니다.
     */
    public static FallingBlock spawnFallingBlock(Location location, Material material) {
        return location.getWorld().spawnFallingBlock(location, material.createBlockData());
    }
    
    /**
     * 낙하 블록을 생성합니다.
     */
    public static FallingBlock spawnFallingBlock(Location location, Material material, byte data) {
        return location.getWorld().spawnFallingBlock(location, material.createBlockData());
    }
    
    /**
     * 낙하 블록을 생성하고 속도를 설정합니다.
     */
    public static FallingBlock spawnFallingBlock(Location location, Material material, Vector velocity) {
        FallingBlock fallingBlock = spawnFallingBlock(location, material);
        fallingBlock.setVelocity(velocity);
        return fallingBlock;
    }
    
                 /**
              * 낙하 블록을 생성하고 행동을 설정합니다.
              */
             public static FallingBlock spawnFallingBlock(Location location, Material material, Behavior behavior) {
                 FallingBlock fallingBlock = spawnFallingBlock(location, material);

                 switch (behavior) {
                     case NO_GRAVITY:
                         fallingBlock.setGravity(false);
                         break;
                     case NO_DROP:
                         fallingBlock.setDropItem(false);
                         break;
                     default:
                         break;
                 }

                 return fallingBlock;
             }
             
             /**
              * 낙하 블록을 생성합니다 (복합 매개변수).
              */
             public static FallingBlock spawnFallingBlock(Location location, Material material, boolean dropItem, Vector velocity, Behavior behavior) {
                 FallingBlock fallingBlock = spawnFallingBlock(location, material, velocity);
                 fallingBlock.setDropItem(dropItem);
                 
                 switch (behavior) {
                     case NO_GRAVITY:
                         fallingBlock.setGravity(false);
                         break;
                     case NO_DROP:
                         fallingBlock.setDropItem(false);
                         break;
                     default:
                         break;
                 }
                 
                 return fallingBlock;
             }
             
             /**
              * 낙하 블록을 생성합니다 (복합 매개변수).
              */
             public static FallingBlock spawnFallingBlock(Location location, Material material, byte data, boolean dropItem, Vector velocity, Behavior behavior) {
                 FallingBlock fallingBlock = spawnFallingBlock(location, material, data);
                 fallingBlock.setDropItem(dropItem);
                 fallingBlock.setVelocity(velocity);
                 
                 switch (behavior) {
                     case NO_GRAVITY:
                         fallingBlock.setGravity(false);
                         break;
                     case NO_DROP:
                         fallingBlock.setDropItem(false);
                         break;
                     default:
                         break;
                 }
                 
                 return fallingBlock;
             }
         }
