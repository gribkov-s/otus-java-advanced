# Модульный сервис для симуляции игры "Орёл или решка"

Сервис симулирует игру "Орёл или решка". 
Игра заключается в предсказании значения, генерируемого сервисом в случайном порядке - 0 или 1.
Если предсказание верно, пользователь выигрывает игру, если ошибочно - проигрывает.

## Модули

### Core
Включает репозитории и модель данных.

Модель данных включает класс Game. Каждый экземпляр класса представляет собой сыгранную игру:
* datetime - дата и время игры
* prediction - предсказание пользователя
* fact - сгенерированное в случайном порядке значение
* succeed - выиграл ли пользователь (true или false)

Репозиторий позволяет записать данные очередной игры и получить статистику всех игр.
Реализован репозиторий, хранящий данные в памяти.

### Provider

Генерирует значения в случайном порядке. 
Реализован генератор целых чисел по диапазону.

### Service

Запускает очередную игру, возвращает результат и сохраняет его в репозиторий.
Также получает статистику всех игр из репозитория.
Использует модули Core и Provider.

### API

Web приложение на spring boot для работы с пользователями-игроками.
Использует модуль Service.
Реализованы методы REST API:

#### Запуск очередной игры * 
Передается предсказание - 0 или 1. Возвращается результат игры.

*curl -H "Content-Type: application/json" -d "{\"prediction\": 0}" -X POST http://localhost:8080/heads-and-tails/play*

#### Получение статистики игр *
Возвращается список результатов проведенных игр.

*curl -X GET http://localhost:8080/heads-and-tails/stat*

## Запуск
*java -jar heads-and-tails-app-1.0.jar* 


*примеры HTTP запросов приведены для утилиты curl

