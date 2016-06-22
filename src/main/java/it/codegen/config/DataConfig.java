package it.codegen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by thilanka on 5/24/2016.
 */
@Configuration @ComponentScan({"it.codegen"}) @PropertySource(value = {"classpath:application.properties"})
public class DataConfig
{
    @Resource private Environment environment;

    @Bean public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName( environment.getRequiredProperty( "jdbc.driverClassName" ) );
        dataSource.setUsername( environment.getRequiredProperty( "jdbc.username" ) );
        dataSource.setUrl( environment.getRequiredProperty( "jdbc.url" ) );
        dataSource.setPassword( environment.getRequiredProperty( "jdbc.password" ) );

        return dataSource;
    }

    @Bean
    public String getSvnBase()
    {
        return  environment.getRequiredProperty( "svn.base" );
    }

    @Bean
    public String getUserName()
    {
        return  environment.getRequiredProperty( "svn.username" );
    }

    @Bean
    public String getPassword()
    {
        return  environment.getRequiredProperty( "svn.password" );
    }

    @Bean
    public String getPatchFileBase()
    {
        return  environment.getRequiredProperty( "patch.path" );
    }
}
