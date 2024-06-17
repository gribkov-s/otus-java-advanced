package ru.otus.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import ru.otus.model.User;
import ru.otus.service.encryptor.UserEncryptor;
import ru.otus.service.encryptor.UserEncryptorImpl;

import java.util.concurrent.TimeUnit;


@BenchmarkMode({Mode.All})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class UserEncryptorBenchmark {

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
    public void encrypt(ExecutionPlan plan, Blackhole bh) {
        UserEncryptor userEncryptor = new UserEncryptorImpl(
                 plan.charset,
                plan.algorithm,
                plan.isSalted);

        bh.consume(userEncryptor.encrypt(plan.TEST_USER));
    }
}
