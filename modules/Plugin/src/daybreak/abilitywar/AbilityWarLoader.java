package daybreak.abilitywar;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;

/**
 * AbilityWar Paper Plugin Loader
 * @author Daybreak 새벽
 */
public class AbilityWarLoader implements PluginLoader {

    @Override
    public void classloader(PluginClasspathBuilder classpathBuilder) {
        // 필요한 외부 라이브러리들을 추가
        MavenLibraryResolver resolver = new MavenLibraryResolver();
        
        // Maven Central 대신 Paper의 기본 미러 사용
        resolver.addRepository(new RemoteRepository.Builder("central", "default", 
            MavenLibraryResolver.MAVEN_CENTRAL_DEFAULT_MIRROR).build());
        
        // 필요한 의존성들 추가
        resolver.addDependency(new Dependency(new DefaultArtifact("com.google.guava:guava:32.1.3-jre"), null));
        resolver.addDependency(new Dependency(new DefaultArtifact("com.google.code.gson:gson:2.10.1"), null));
        resolver.addDependency(new Dependency(new DefaultArtifact("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.22"), null));
        resolver.addDependency(new Dependency(new DefaultArtifact("org.bstats:bstats-bukkit:3.0.2"), null));
        
        classpathBuilder.addLibrary(resolver);
    }
}
