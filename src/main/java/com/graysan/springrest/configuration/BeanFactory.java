package com.graysan.springrest.configuration;

import com.graysan.springrest.Constants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
// @Component de olabilir ama configuration tercih edilir
public class BeanFactory {
    //xml de oluşturmak yerine java tarafında bean oluşturmak için

    @Bean(name = "myds")
    public DataSource getDataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource(Constants.URL,Constants.USER,Constants.PASSWORD);
        ds.setDriverClassName("org.postgresql.Driver");
        return ds;
    }

    @Bean(name = "bizimjdbctemplate") // xml'de <bean> in alternatifi
    @DependsOn(value = "myds") // myds bean ine bağımlı bir bean olduğu için öncelik myds bean inin çalışması
    public JdbcTemplate createJdbcTemplate(@Qualifier(value = "myds") DataSource ds){
        return new JdbcTemplate(ds);
    }

    @Bean // xml'de <bean> in alternatifi
    @DependsOn(value = "myds") // myds bean ine bağımlı bir bean olduğu için öncelik myds bean inin çalışması
    public NamedParameterJdbcTemplate myNamedParameterJdbcTemplate(@Qualifier(value = "myds") DataSource ds){
        return new NamedParameterJdbcTemplate(ds);
    }
}