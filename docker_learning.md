# Docker learning checklist (BookTable)

## 1. Проверка Docker

- `docker --version`
- `docker compose version`
Зачем: убедиться, что Docker и Compose доступны.

## 2. Запустить только БД

- `docker compose up -d db`
Зачем: поднять PostgreSQL без запуска backend.

## 3. Проверить, что контейнер запущен

- `docker compose ps`
- `docker logs tablebook-db --tail 50`
Зачем: увидеть статус и логи старта.

## 4. Подключиться к БД (вариант psql)

- `docker exec -it tablebook-db psql -U postgres -d tabledb`
- `\dt`
Зачем: проверить, что БД жива и доступна.

## 5. Подключиться к БД с хоста (GUI)

Параметры:

- host: localhost
- port: 5432
- db: tabledb
- user: postgres
- pass: postgres
Зачем: удобно смотреть таблицы и данные.

## 6. Запустить всё приложение

- `docker compose up -d`
Зачем: поднять db + backend.

## 7. Проверить API backend

- открыть `http://localhost:8080` (или ваш endpoint)
Зачем: убедиться, что backend стартовал и видит БД.

## 8. Проверить volume (сохранность данных)

- `docker volume ls`
- `docker volume inspect postgres_data`
Зачем: убедиться, где хранится персистентное хранилище.

## 9. Остановить сервисы

- `docker compose stop`
Зачем: остановить без удаления.

## 10. Полная очистка окружения (осторожно)

- `docker compose down`
- `docker compose down -v`  # удалит и volume (данные БД)
Зачем: чистый перезапуск.

