package me.bcfh.myboard.config;

import java.nio.file.Path;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import me.bcfh.myboard.SQLiteDialect;
import me.bcfh.myboard.model.Item;

@Configuration
public class DataSourceConfig {

	@Autowired
	private MyBoardConfig config;

	@Bean
	public DataSource dataSource() {
		Path toDb = config.getDatabaseFilePath();
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.sqlite.JDBC");
		dataSourceBuilder.url("jdbc:sqlite:" + toDb.toFile().getAbsolutePath());
		return dataSourceBuilder.build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
			JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(dataSource);
		lef.setJpaVendorAdapter(jpaVendorAdapter);
		lef.setPackagesToScan(Item.class.getPackage().getName());
		lef.getJpaPropertyMap().put("hibernate.search.default.directory_provider", "filesystem");
		lef.getJpaPropertyMap().put("hibernate.search.default.indexBase",
				config.getFulltextIndexDirectory().toFile().getAbsolutePath());
		return lef;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabasePlatform(SQLiteDialect.class.getName());
		return hibernateJpaVendorAdapter;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
}
