package petCom.db.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import petCom.db.FriendRepository;
import petCom.db.PetRepository;
import petCom.db.UserRepository;

import javax.sql.DataSource;

@Configuration
public class dbConfig {

///*
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName("org.h2.Driver");
//        ds.setUrl("jdbc:h2:tcp://localhost/~/te");
//        ds.setUsername("saa");
//        ds.setPassword("qwe123");
//        return ds;
//    }
//*/



//    @Bean
 //   public JedisConnectionFactory redisCF() {
 //       RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("server ", 6379);
 //       return new JedisConnectionFactory(config);
  //  }
    /**
     * jedis连接池配置,直接new 一个对象返回，使用默认值，如有需要可以为其各个属性配置值
     * @return
     */
   // @Bean
   // public JedisPoolConfig jedisPoolConfig(){
    //    return new JedisPoolConfig();
   // }

    @Bean
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScripts("classpath:jdbc/schema.sql", "classpath:jdbc/test-data.sql")
                .build();
  }
//    @Bean
//    public JndiObjectFactoryBean dataSource(){
//        JndiObjectFactoryBean jndiObjectFB=new JndiObjectFactoryBean();
//        jndiObjectFB.setJndiName("jdbc/mysql");
//        jndiObjectFB.setResourceRef(true);
//        jndiObjectFB.setProxyInterface(javax.sql.DataSource.class);
//        return jndiObjectFB;
//}
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return  new JdbcTemplate(dataSource);
    }
    @Bean
    @Autowired
    public UserRepository userRepository(JdbcTemplate jdbcTemplate){
        return new JdbcUserRepository(jdbcTemplate);
    }
    @Bean
    @Autowired
    public PetRepository petRepository(JdbcTemplate jdbcTemplate){return new JdbcPetRepository(jdbcTemplate);}
    @Bean
    @Autowired
    public FriendRepository friendRepository (JdbcTemplate jdbcTemplate){return new JdbcFriendRepository(jdbcTemplate);}
}
