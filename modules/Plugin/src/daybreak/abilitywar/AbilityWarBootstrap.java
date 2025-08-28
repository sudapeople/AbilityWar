package daybreak.abilitywar;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * AbilityWar Paper Plugin Bootstrapper
 * @author Daybreak 새벽
 */
public class AbilityWarBootstrap implements PluginBootstrap {

    @Override
    public void bootstrap(BootstrapContext context) {
        // Bootstrap 단계에서 필요한 초기화 작업
        context.getLogger().info("AbilityWar 플러그인 부트스트랩을 시작합니다.");
    }

    @Override
    public JavaPlugin createPlugin(PluginProviderContext context) {
        return new AbilityWar();
    }
}
