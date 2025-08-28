package daybreak.abilitywar.utils.base.minecraft.block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

/**
 * 블록 스냅샷 인터페이스
 * @author Daybreak 새벽
 */
public interface IBlockSnapshot {
    
    /**
     * 블록의 위치를 가져옵니다.
     */
    Location getLocation();
    
    /**
     * 블록의 타입을 가져옵니다.
     */
    Material getType();
    
    /**
     * 블록의 데이터를 가져옵니다.
     */
    BlockData getBlockData();
    
               /**
            * 블록을 복원합니다.
            */
           void restore();
           
           /**
            * 블록을 적용합니다.
            */
           void apply();
    
    /**
     * 블록 스냅샷을 생성합니다.
     */
    static IBlockSnapshot of(Block block) {
        return new BlockSnapshot(block);
    }
    
    /**
     * 블록 스냅샷 구현체
     */
    class BlockSnapshot implements IBlockSnapshot {
        private final Location location;
        private final Material type;
        private final BlockData blockData;
        
        public BlockSnapshot(Block block) {
            this.location = block.getLocation();
            this.type = block.getType();
            this.blockData = block.getBlockData();
        }
        
        @Override
        public Location getLocation() {
            return location;
        }
        
        @Override
        public Material getType() {
            return type;
        }
        
        @Override
        public BlockData getBlockData() {
            return blockData;
        }
        
                       @Override
               public void restore() {
                   Block block = location.getBlock();
                   block.setType(type);
                   block.setBlockData(blockData);
               }
               
               @Override
               public void apply() {
                   restore();
               }
    }
}
