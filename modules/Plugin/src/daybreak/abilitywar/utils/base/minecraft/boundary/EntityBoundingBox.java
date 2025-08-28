package daybreak.abilitywar.utils.base.minecraft.boundary;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

/**
 * 엔티티 경계선 박스 클래스
 * @author Daybreak 새벽
 */
public class EntityBoundingBox extends BoundingBox {
    
    private final Entity entity;
    
    public EntityBoundingBox(Entity entity) {
        super(
            entity.getLocation().getX() - entity.getWidth() / 2,
            entity.getLocation().getY(),
            entity.getLocation().getZ() - entity.getWidth() / 2,
            entity.getLocation().getX() + entity.getWidth() / 2,
            entity.getLocation().getY() + entity.getHeight(),
            entity.getLocation().getZ() + entity.getWidth() / 2
        );
        this.entity = entity;
    }
    
    public EntityBoundingBox(Entity entity, double x, int y, double z, double width, double height, double depth) {
        super(x - width/2, y, z - depth/2, x + width/2, y + height, z + depth/2);
        this.entity = entity;
    }
    
    public Entity getEntity() {
        return entity;
    }
    
    public void update() {
        // 엔티티 위치가 변경되면 경계선을 업데이트
        Location loc = entity.getLocation();
        double width = entity.getWidth() / 2;
        double height = entity.getHeight();
        
        // 새로운 경계선으로 업데이트 (실제로는 새로운 인스턴스를 생성해야 하지만 여기서는 단순화)
    }
    
    public boolean containsEntity(Entity other) {
        Location otherLoc = other.getLocation();
        double otherWidth = other.getWidth() / 2;
        double otherHeight = other.getHeight();
        
        return otherLoc.getX() - otherWidth >= getMinX() && otherLoc.getX() + otherWidth <= getMaxX() &&
               otherLoc.getY() >= getMinY() && otherLoc.getY() + otherHeight <= getMaxY() &&
               otherLoc.getZ() - otherWidth >= getMinZ() && otherLoc.getZ() + otherWidth <= getMaxZ();
    }
}
