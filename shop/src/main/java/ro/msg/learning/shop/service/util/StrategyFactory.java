package ro.msg.learning.shop.service.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.service.*;

@Configuration
@RequiredArgsConstructor
public class StrategyFactory {

    private final OrdersService ordersService;
    private final OrderDetailService orderDetailService;
    private final ProductService productService;
    private final StockService stockService;
    private final LocationService locationService;

    @Value("${strategy}")
    private String searchStrategy;

    @Bean
    public SearchStrategy getStrategy() {
        switch(searchStrategy){
            case "single_location": return new SingleLocationStrategy(ordersService, orderDetailService, productService, stockService, locationService);
            case "most_abundant": return new MostAbundantStrategy(ordersService, orderDetailService, productService, stockService);
            default: return new SingleLocationStrategy(ordersService, orderDetailService, productService, stockService, locationService);
        }
    }
}
