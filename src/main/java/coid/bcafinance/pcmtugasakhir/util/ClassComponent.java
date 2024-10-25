package coid.bcafinance.pcmtugasakhir.util;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ClassComponent {



    @Bean
    public Random getRandom(){
        return new Random();
    }
}
