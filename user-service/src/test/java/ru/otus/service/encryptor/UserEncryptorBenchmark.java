package ru.otus.service.encryptor;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.jmh.AbstractBenchmark;
import ru.otus.model.User;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.concurrent.TimeUnit;
import org.junit.runner.RunWith;

@SpringBootTest
@State(Scope.Benchmark)
@RunWith(SpringRunner.class)
@BenchmarkMode({Mode.All})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class UserEncryptorBenchmark extends AbstractBenchmark {

    @State(Scope.Benchmark)
    public static class ExecutionPlan {

        @Param({"UTF-8"})
        public String charset;

        @Param({"MD5", "SHA-256", "SHA-512"})
        public String algorithm;

        @Param({"false", "true"})
        public boolean isSalted;

        public User TEST_USER = new User("vasya", "123456");
    }

    @Benchmark
    public void encrypt(Blackhole bh, ExecutionPlan plan) {
        UserEncryptor userEncryptor = new UserEncryptorImpl(
                plan.charset,
                plan.algorithm,
                plan.isSalted);

        bh.consume(userEncryptor.encrypt(plan.TEST_USER));
    }
}
