package daybreak.abilitywar.utils.base.minecraft.block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import java.util.ArrayList;
import java.util.List;

/**
 * 블록 관련 유틸리티 클래스
 * @author Daybreak 새벽
 */
public class Blocks {
    
    /**
     * 블록 스냅샷을 생성합니다.
     */
    public static IBlockSnapshot createSnapshot(Block block) {
        return IBlockSnapshot.of(block);
    }
    
    /**
     * 여러 블록의 스냅샷을 생성합니다.
     */
    public static List<IBlockSnapshot> createSnapshots(List<Block> blocks) {
        List<IBlockSnapshot> snapshots = new ArrayList<>();
        for (Block block : blocks) {
            snapshots.add(createSnapshot(block));
        }
        return snapshots;
    }
    
    /**
     * 블록을 설정합니다.
     */
    public static void setBlock(Location location, Material material) {
        location.getBlock().setType(material);
    }
    
    /**
     * 블록을 설정합니다.
     */
    public static void setBlock(Location location, Material material, BlockData blockData) {
        Block block = location.getBlock();
        block.setType(material);
        block.setBlockData(blockData);
    }
    
    /**
     * 블록이 공기인지 확인합니다.
     */
    public static boolean isAir(Block block) {
        return block.getType() == Material.AIR;
    }
    
    /**
     * 블록이 공기인지 확인합니다.
     */
    public static boolean isAir(Location location) {
        return isAir(location.getBlock());
    }
    
    /**
     * 블록이 고체인지 확인합니다.
     */
    public static boolean isSolid(Block block) {
        return block.getType().isSolid();
    }
    
    /**
     * 블록이 고체인지 확인합니다.
     */
    public static boolean isSolid(Location location) {
        return isSolid(location.getBlock());
    }
}
