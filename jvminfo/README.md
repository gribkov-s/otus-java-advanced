# Приложение для получения информации о процессах JVM
#### подписанное сертификатом и запускаемое с помощью отдельной JRE

Приложение позволяет получить информацию о процессах системы, занятых JVM.
После запуска в консоль выводится информация о каждом таком процессе:
* pid
* parent pid
* isAlive
* supportsNormalTermination
* startTime
* command

## Подпись

## JRE

### Компиляция приложения

*javac -d ./jlink/target ./src/main/java/module-info.java*

*javac -d ./jlink/target --module-path ./jlink/target ./src/main/java/ru/otus/JvmInfoApp.java*

### Создание JRE

*jlink --launcher jvminfo-launcher=jvminfo/ru.otus.JvmInfoApp --module-path "C:\Users\0\IdeaProjects\otus-java-advanced\jvminfo\jlink\target;C:\Users\0\.jdks\liberica-17.0.7\jmods" --add-modules jvminfo --output jlink/jre*

### Запуск

*.\jlink\jre\bin\jvminfo-launcher.bat*

