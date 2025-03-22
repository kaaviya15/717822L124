package Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestTemplate {
    

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public Integer[] getForObject(String apiUrl, Class<Integer[]> class1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getForObject'");
    }
}
