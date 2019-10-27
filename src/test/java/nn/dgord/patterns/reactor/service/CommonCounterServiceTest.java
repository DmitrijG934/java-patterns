package nn.dgord.patterns.reactor.service;

import org.junit.Before;
import org.junit.Test;
import reactor.test.StepVerifier;

import static nn.dgord.patterns.reactor.service.CalcOperations.ADDITION;
import static nn.dgord.patterns.reactor.service.CalcOperations.DIVISION;
import static nn.dgord.patterns.reactor.service.CalcOperations.MULTIPLICATION;
import static nn.dgord.patterns.reactor.service.CalcOperations.SUBTRACTION;
import static org.junit.Assert.assertNotNull;

public class CommonCounterServiceTest {
    private CommonCounterService commonCounterService;

    @Before
    public void setUp() {
        commonCounterService = new CommonCounterService();
    }

    @Test
    public void when_constructorIsCalling_thenInstanceNotNull() {
        assertNotNull(commonCounterService);
    }

    @Test
    public void when_counterServiceReceiveAddOperation_thenResultIsAdditionAndResultMatchesExpected() {
        final int expectedValue = 25;
        StepVerifier.create(commonCounterService.count(10, 15, ADDITION))
                .expectNextMatches(num -> num.intValue() == expectedValue)
                .expectComplete().verify();
    }

    @Test
    public void when_counterServiceReceiveSubOperation_thenResultIsAdditionAndResultMatchesExpected() {
        final int expectedValue = 25;
        StepVerifier.create(commonCounterService.count(35, 10, SUBTRACTION))
                .expectNextMatches(num -> num.intValue() == expectedValue)
                .expectComplete().verify();
    }

    @Test
    public void when_counterServiceReceiveMulOperation_thenResultIsAdditionAndResultMatchesExpected() {
        final int expectedValue = 25;
        StepVerifier.create(commonCounterService.count(5, 5, MULTIPLICATION))
                .expectNextMatches(num -> num.intValue() == expectedValue)
                .expectComplete().verify();
    }

    @Test
    public void when_counterServiceReceiveDivOperation_thenResultIsAdditionAndResultMatchesExpected() {
        final int expectedValue = 25;
        StepVerifier.create(commonCounterService.count(50, 2, DIVISION))
                .expectNextMatches(num -> num.intValue() == expectedValue)
                .expectComplete().verify();
    }

    @Test
    public void when_counterServiceReceiveDivOperationAndDivisionByZero_thenResultIsAdditionAndResultMatchesExpected() {
        final int expectedValue = 35;
        StepVerifier.create(commonCounterService.count(35, 0, DIVISION))
                .expectNextMatches(num -> num.intValue() == expectedValue)
                .expectComplete().verify();
    }
}