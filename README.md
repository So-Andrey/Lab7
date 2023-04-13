## Лабораторная работа №7 по дисциплине «Программирование»

<p align="left">Преподаватель: <strong>Райла Мартин</strong></br>
Вариант: <strong>125611</strong></br>
Выполнили: <strong>Соколов Андрей и Соколова Анастасия</strong></br>
Группа: <strong>O3143</strong>
</p>

## Задание:

Доработать программу из лабораторной работы №5 следующим образом:

1. Организовать хранение коллекции в реляционной СУБД (PostgresQL). Убрать хранение коллекции в файле.
2. Для генерации поля id использовать средства базы данных (sequence).
3. Обновлять состояние коллекции в памяти только при успешном добавлении объекта в БД
4. Все команды получения данных должны работать с коллекцией в памяти, а не в БД
5. Организовать возможность регистрации и авторизации пользователей. У пользователя есть возможность указать пароль.
6. Пароли при хранении хэшировать алгоритмом SHA-1
7. Запретить выполнение команд не авторизованным пользователям.
8. При хранении объектов сохранять информацию о пользователе, который создал этот объект.
9. Пользователи должны иметь возможность просмотра всех объектов коллекции, но модифицировать могут только принадлежащие им.
10. Для идентификации пользователя отправлять логин и пароль с каждым запросом.
11. Операции обработки объектов коллекции должны быть реализованы с помощью Stream API с использованием лямбда-выражений.

## Порядок выполнения работы:

1. В качестве базы данных использовать PostgreSQL.
2. Для подключения к БД на кафедральном сервере использовать хост pg, имя базы данных - studs, имя пользователя/пароль совпадают с таковыми для подключения к серверу.
