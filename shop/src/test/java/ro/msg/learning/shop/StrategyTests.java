package ro.msg.learning.shop;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.ActiveProfiles;
import ro.msg.learning.shop.service.util.MostAbundantStrategyTest;
import ro.msg.learning.shop.service.util.SingleLocationStrategyTest;

@ActiveProfiles("test")
@RunWith(Suite.class)
@Suite.SuiteClasses({
        MostAbundantStrategyTest.class,
        SingleLocationStrategyTest.class})
public class StrategyTests {
}
