package org.tix.lab4_1.config;

import com.atomikos.spring.AtomikosDataSourceBean;
import lombok.RequiredArgsConstructor;
import org.postgresql.xa.PGXADataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = {"org.tix.lab4_1.repo.mainDB"},
        entityManagerFactoryRef = "mainEntityManager"

)
@RequiredArgsConstructor
public class MainDbConf {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean(name = "mainDataSource", initMethod = "init", destroyMethod = "close")
    @Primary
    public AtomikosDataSourceBean mainDataSource() {
        PGXADataSource pgxaDataSource = new PGXADataSource();
        pgxaDataSource.setUrl(url);
        pgxaDataSource.setUser(username);
        pgxaDataSource.setPassword(password);

        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
        dataSource.setXaDataSource(pgxaDataSource);
        dataSource.setUniqueResourceName("mainDataSource");
        dataSource.setMaxPoolSize(100);
        dataSource.setBorrowConnectionTimeout(5);
        return dataSource;
    }

    public Map<String, String> mainJpaProperties() {
        Map<String, String> mainJpaProperties = new HashMap<>();
        mainJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        mainJpaProperties.put("hibernate.show_sql", "true");
        mainJpaProperties.put("javax.persistence.transactionType", "JTA");
        mainJpaProperties.put("hibernate.hbm2ddl.auto", "update");
        mainJpaProperties.put("spring.jpa.open-in-view", "true");
        return mainJpaProperties;
    }


    @Bean(name = "mainEntityManagerFactoryBuilder")
    public EntityManagerFactoryBuilder mainEntityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(
                new HibernateJpaVendorAdapter(), mainJpaProperties(), null
        );
    }

    @Primary
    @Bean(name = "mainEntityManager")
    public LocalContainerEntityManagerFactoryBean mainEntityManagerFactory(@Qualifier("mainEntityManagerFactoryBuilder") EntityManagerFactoryBuilder builder, @Qualifier("mainDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("org.tix.lab4_1.model.mainDB")
                .persistenceUnit("main")
                .jta(true)
                .build();
    }
}
