package com.wuhaowen.phonograph

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.wuhaowen.phonograph"])
class PhonographApplication

fun main(args: Array<String>) {
    runApplication<PhonographApplication>(*args)
}


@Bean fun jpaVendorAdapter():JpaVendorAdapter = HibernateJpaVendorAdapter().apply {
    setDatabase(Database.MYSQL)
    setGenerateDdl(true)
    setShowSql(true)
}

@Bean fun  entityManagerFactory(dataSource: DataSource, jpaVendorAdapter: JpaVendorAdapter)
        : LocalContainerEntityManagerFactoryBean = LocalContainerEntityManagerFactoryBean().apply {
    setDataSource(dataSource)
    setJpaVendorAdapter(jpaVendorAdapter)
    setPackagesToScan("com.wuhaowen.phonograph")
    afterPropertiesSet()
}

@Bean fun transactionManager(emf: EntityManagerFactory): JpaTransactionManager = JpaTransactionManager(emf)


