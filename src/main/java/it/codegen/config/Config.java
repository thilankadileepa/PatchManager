package it.codegen.config;

import it.codegen.DataManager;
import it.codegen.PatchManager;
import it.codegen.RepoManager;
import it.codegen.config.jdbc.MySqlJdbcDataManager;
import it.codegen.impl.PatchManagerImpl;
import it.codegen.svn.SVNKitManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Created by thilanka on 5/9/2016.
 */
@Configuration
@ComponentScan("it.codegen")
@EnableWebMvc
public class Config extends WebMvcConfigurerAdapter
{
    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public DataManager dataManager()
    {
        return new MySqlJdbcDataManager();
    }

    @Bean
    public PatchManager svnPatchManager()
    {
        return new PatchManagerImpl();
    }

    @Bean
    public RepoManager repoManager()
    {
        return new SVNKitManager();
    }
}
