package ro.msg.learning.shop.model;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.service.CustomerService;

@Component
@RequiredArgsConstructor
public class PopulareCiobaneasca {//} implements ApplicationRunner {

    /*private final CustomerService customerService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        customerService.insert(new Customer(2, "Andrei", "Bercean", "AndreiBercean", "Parola", "andreibercean@yahoo.com"));
        customerService.insert(new Customer(3, "Marian", "Corpodean", "MCorpo", "Parola1", "corpodeanmarian@yahoo.com"));
    }*/
}
