package daybreak.abilitywar.utils.base.minecraft;

import org.bukkit.entity.Player;

/**
 * 스킨 정보 클래스
 * @author Daybreak 새벽
 */
public class SkinInfo {
    
    private final String value;
    private final String signature;
    
    public SkinInfo(String value, String signature) {
        this.value = value;
        this.signature = signature;
    }
    
    public SkinInfo(String value, String signature, String texture) {
        this.value = value;
        this.signature = signature;
    }
    
    public String getValue() {
        return value;
    }
    
    public String getSignature() {
        return signature;
    }
    
    /**
     * 플레이어의 스킨 정보를 가져옵니다.
     */
    public static SkinInfo getSkinInfo(Player player) {
        // Paper 1.21.8에서는 플레이어의 스킨 정보를 직접 가져올 수 없으므로 기본값 반환
        return new SkinInfo("", "");
    }
    
    /**
     * 기본 스킨 정보를 반환합니다.
     */
    public static SkinInfo getDefaultSkin() {
        return new SkinInfo("", "");
    }
}
