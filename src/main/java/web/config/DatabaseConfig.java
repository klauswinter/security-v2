package web.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan("web")
public class DatabaseConfig {

    //Интерфейс Environment представляет окружение, в котором приложение запущено.
    //Свойства (properties) - это не только содержимое файлов с соответствующим расширением, но и свойства JVM,
    // переменные окружения операционной системы,
    // параметры сервлета (если приложение запущено в соответствующем контейнере) и многое другое.
    // Environment предоставляет к ним ко всем единообразный доступ.
    @Resource
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        // EntityManagerFactory предоставляет возможность автоматического создания наших бинов
        // и автоматического создания таблиц на основе наших бинов

        em.setDataSource(dataSource());
        em.setPackagesToScan(env.getRequiredProperty("db.entity.package"));
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(getHibernateProperties());

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    // В данном случае DataSource и Properties
    // заменяют файл persistence.xml, в котором тоже указываются properties для db и hibernate
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl(env.getRequiredProperty("db.url"));
        dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
        dataSource.setUsername(env.getRequiredProperty("db.username"));
        dataSource.setPassword(env.getRequiredProperty("db.password"));

        return dataSource;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
