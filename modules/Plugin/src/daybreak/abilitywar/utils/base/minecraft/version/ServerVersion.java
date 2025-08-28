package daybreak.abilitywar.utils.base.minecraft.version;

import org.bukkit.Bukkit;

/**
 * Paper 1.21.8용 서버 버전 관리 클래스
 * @author Daybreak 새벽
 */
public class ServerVersion {
    
    private static final String VERSION = "1.21.8";
    private static final String NAME = "Paper 1.21.8";
    
    public static String getName() {
        return NAME;
    }
    
    public static String getVersion() {
        return VERSION;
    }
    
    public static boolean isAboveOrEqual(NMSVersion version) {
        return true; // Paper 1.21.8은 모든 버전을 지원
    }
    
    public static boolean isBelowOrEqual(NMSVersion version) {
        return true; // Paper 1.21.8은 모든 버전을 지원
    }
    
    public static boolean compatVersion(Object plugin) {
        return true; // Paper 1.21.8은 호환됨
    }
    
    /**
     * 서버 버전이 지원되는지 확인합니다.
     */
    public static boolean isSupported() {
        return true; // Paper 1.21.8은 항상 지원됨
    }
    
    /**
     * 플러그인이 지원하지 않는 버전을 사용하고 있는지 확인합니다.
     */
    public static boolean isUnsupportedVersion() {
        return false; // Paper 1.21.8은 항상 지원됨
    }
}
