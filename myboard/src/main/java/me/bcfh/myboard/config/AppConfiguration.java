package me.bcfh.myboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AppConfiguration {

    @Autowired
    private Environment env;

    private MyBoardConfig config;

    @Bean
    public MyBoardConfig appConfig() {
	String userDir = System.getProperty("user.dir");
	String baseDir = env.getProperty(MyBoardConfig.WORKING_DIRECTORY_KEY, userDir);
	config = new MyBoardConfig(baseDir);
	return config;
    }

}
