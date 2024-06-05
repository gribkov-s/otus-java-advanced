module api {
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.web;
    requires lombok;
    requires service;
    exports ru.otus.app;
}