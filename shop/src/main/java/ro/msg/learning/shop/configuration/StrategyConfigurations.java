package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.service.util.MostAbundantStrategy;
import ro.msg.learning.shop.service.util.SearchStrategy;
import ro.msg.learning.shop.service.util.SingleLocationStrategy;

@Configuration
public class StrategyConfigurations {

    @Bean
    public SearchStrategy getStrategy(@Value("${strategy}")String searchStrategy, SingleLocationStrategy singleLocationStrategy, MostAbundantStrategy mostAbundantStrategy) {
        switch(searchStrategy){
            case "most_abundant": return mostAbundantStrategy;
            default: return singleLocationStrategy;
        }
    }
}
