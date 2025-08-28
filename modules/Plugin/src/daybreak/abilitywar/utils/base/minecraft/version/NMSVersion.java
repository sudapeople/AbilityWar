package daybreak.abilitywar.utils.base.minecraft.version;

/**
 * Paper 1.21.8용 NMS 버전 관리 클래스
 * @author Daybreak 새벽
 */
public class NMSVersion {
    
    public static final NMSVersion v1_13_R1 = new NMSVersion("1.13");
    public static final NMSVersion v1_13_R2 = new NMSVersion("1.13.2");
    public static final NMSVersion v1_17_R1 = new NMSVersion("1.17");
    public static final NMSVersion v1_21_R1 = new NMSVersion("1.21.8");
    
    private final String version;
    
    private NMSVersion(String version) {
        this.version = version;
    }
    
    public String getVersion() {
        return version;
    }
    
    @Override
    public String toString() {
        return version;
    }
}
