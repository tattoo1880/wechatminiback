```mysql
CREATE TABLE mp4 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    urls TEXT,
    title TEXT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time BIGINT
);

```


```shell
brew update
brew install rabbitmq
brew services start rabbitmq
rabbitmqctl status

```