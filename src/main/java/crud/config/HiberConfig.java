package crud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan("crud")
@EnableJpaRepositories(basePackages = "crud.repositories")
public class HiberConfig {
    private final Environment env;

    @Autowired
    public HiberConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter getJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager getTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(getEntityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(getJpaVendorAdapter());
        factoryBean.setDataSource(getDataSource());

        Properties props = new Properties();
        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        props.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));

        factoryBean.setJpaProperties(props);
        factoryBean.setPackagesToScan("crud.models");
        return factoryBean;
    }

    @Bean
    PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

//    @Bean
//    public HibernateTransactionManager getTransactionManager() {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(getSessionFactory().getObject());
//        return transactionManager;
//    }

//    @Bean
//    public LocalSessionFactoryBean getSessionFactory() {
//        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
//        factoryBean.setDataSource(getDataSource());
//
//        Properties props = new Properties();
//        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//        props.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
//        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//
//        factoryBean.setHibernateProperties(props);
//        factoryBean.setAnnotatedClasses(User.class);
//        return factoryBean;
//    }
}
