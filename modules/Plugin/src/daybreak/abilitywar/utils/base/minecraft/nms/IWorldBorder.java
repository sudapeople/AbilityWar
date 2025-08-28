package daybreak.abilitywar.utils.base.minecraft.nms;

import org.bukkit.Location;
import org.bukkit.World;

/**
 * 월드보더 인터페이스
 * @author Daybreak 새벽
 */
public interface IWorldBorder {
    
    /**
     * 월드보더를 생성합니다.
     */
    void create();
    
    /**
     * 월드보더를 제거합니다.
     */
    void remove();
    
    /**
     * 월드보더의 중심을 설정합니다.
     */
    void setCenter(Location center);
    
    /**
     * 월드보더의 크기를 설정합니다.
     */
    void setSize(double size);
    
    /**
     * 월드보더의 경고 거리를 설정합니다.
     */
    void setWarningDistance(int distance);
    
    /**
     * 월드보더의 경고 시간을 설정합니다.
     */
    void setWarningTime(int seconds);
    
    /**
     * 월드보더가 활성화되어 있는지 확인합니다.
     */
    boolean isActive();
    
    /**
     * 월드보더의 월드를 가져옵니다.
     */
    World getWorld();
}
