package Musaib.MybackendProject.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MaperConfig {

    @Bean
    public ModelMapper modelmap(){
        return new ModelMapper();
    }
}
