# S3Test
SpringとS3を連携して画面で登録と表示ができるアプリ

## 使用技術

![Java](https://img.shields.io/badge/Java-000000.svg?logo=openjdk)
![Spring](https://img.shields.io/badge/Spring-000000.svg?logo=Spring)
![SpringBoot](https://img.shields.io/badge/SpringBoot-000000.svg?logo=SpringBoot)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-000000.svg?logo=Thymeleaf)
![Docker](https://img.shields.io/badge/Docker-000000.svg?logo=Docker)
![Postgresql](https://img.shields.io/badge/Postgresql-000000.svg?logo=Postgresql)
![Gradle](https://img.shields.io/badge/Gradle-000000.svg?logo=Gradle)

## プロジェクト概要

SpringBootとDockerを利用した画像アップロードサイト

DockerでLocalStackのコンテナを作成し、疑似S3環境を作成している。

画面で登録した画像はS3（LocalStack）に保存される。

## 環境構築手順

1. PCにDockerをインストール
 d
## LocalStack上のデータ確認方法

`http://localhost:4566/{バケット名}` にアクセスすることで確認可能
