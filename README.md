# Pessoas API

A RESTful API for managing people (pessoas) built with Spring Boot, featuring asynchronous email notifications via RabbitMQ.


### Features

- **RESTful API** for person management (CRUD operations)
- **MongoDB** for data persistence
- **RabbitMQ** for asynchronous message queuing
- **Email notifications** sent automatically when a person is registered
- **Swagger/OpenAPI** documentation
- **Docker** containerization for easy deployment
- **MailHog** for email testing in development

### Tech Stack

- **Java 21**
- **Spring Boot 4.0.1**
- **Spring Data MongoDB**
- **Spring AMQP (RabbitMQ)**
- **Spring Mail**
- **Lombok**
- **Maven**
- **Docker & Docker Compose**

### Prerequisites

- Java 21 or higher
- Maven 3.6+
- Docker and Docker Compose
- (Optional) MongoDB, RabbitMQ, and MailHog if running without Docker

### Getting Started

#### Using Docker (Recommended)

1. Clone the repository:
```bash
git clone <repository-url>
cd pessoas-api
```

2. Start all services with Docker Compose:
```bash
docker-compose up -d --build
```

This will start:
- **pessoas-api** on port `8084`
- **MongoDB** on port `27017`
- **RabbitMQ** on port `5672` (Management UI on `15672`)
- **MailHog** on ports `1025` (SMTP) and `8025` (Web UI)

3. Access the application:
   - API: http://localhost:8084
   - Swagger UI: http://localhost:8084/swagger-ui.html
   - RabbitMQ Management: http://localhost:15672 (guest/guest)
   - MailHog Web UI: http://localhost:8025

#### Running Locally (Without Docker)

1. Ensure MongoDB and RabbitMQ are running locally

2. Update `application.yaml` with your local configuration

3. Build and run:
```bash
./mvnw clean package
java -jar target/pessoas-api-0.0.1-SNAPSHOT.jar
```

### API Documentation

#### Endpoints

##### Create Person
```http
POST /api/v1/pessoas
Content-Type: application/json

{
  "nome": "John Doe",
  "email": "john.doe@example.com"
}
```

**Response:**
```json
{
  "id": "507f1f77bcf86cd799439011",
  "nome": "John Doe",
  "email": "john.doe@example.com"
}
```

#### Swagger Documentation

Interactive API documentation is available at:
- **Swagger UI**: http://localhost:8084/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8084/v3/api-docs

### Architecture

The application follows an event-driven architecture:

1. **API receives request** → Creates person in MongoDB
2. **Message sent to RabbitMQ** → Person data serialized as JSON
3. **MessageConsumer processes** → Deserializes and sends welcome email
4. **Email sent via MailHog** → HTML formatted welcome email

### Email Notifications

When a person is registered, the system automatically:
1. Sends a message to RabbitMQ queue `pessoas-api`
2. The `MessageConsumer` processes the message
3. Generates an HTML welcome email
4. Sends the email via MailHog (development) or SMTP server (production)

### Docker Services

| Service | Port | Description |
|---------|------|-------------|
| pessoas-api | 8084 | Main API application |
| MongoDB | 27017 | Database |
| RabbitMQ | 5672 | Message broker |
| RabbitMQ Management | 15672 | Management UI |
| MailHog SMTP | 1025 | Email server |
| MailHog Web UI | 8025 | Email viewer |

### Configuration

#### Environment Variables

The following environment variables can be configured in `docker-compose.yml`:

- `SPRING_DATA_MONGODB_URI`: MongoDB connection string
- `SPRING_RABBITMQ_HOST`: RabbitMQ host
- `SPRING_RABBITMQ_PORT`: RabbitMQ port
- `SPRING_RABBITMQ_USERNAME`: RabbitMQ username
- `SPRING_RABBITMQ_PASSWORD`: RabbitMQ password
- `SPRING_MAIL_HOST`: Mail server host
- `SPRING_MAIL_PORT`: Mail server port

#### Application Properties

Key properties in `application.yaml`:
- `mail.enabled`: Enable/disable email functionality (default: `true`)

### Testing

Run tests with:
```bash
./mvnw test
```

### Building

Build the application:
```bash
./mvnw clean package
```

The JAR file will be created in `target/pessoas-api-0.0.1-SNAPSHOT.jar`

### Stopping Services

Stop all Docker containers:
```bash
docker-compose down
```

To remove volumes as well:
```bash
docker-compose down -v
```

### Project Structure

```
pessoas-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/com/cotiinformatica/pessoas_api/
│   │   │       ├── components/      # MessageProducer, MessageConsumer, MailComponent
│   │   │       ├── configurations/  # RabbitMQ, Swagger configs
│   │   │       ├── controllers/     # REST controllers
│   │   │       ├── dtos/            # Data Transfer Objects
│   │   │       ├── entities/        # MongoDB entities
│   │   │       ├── repositories/    # MongoDB repositories
│   │   │       └── services/         # Business logic
│   │   └── resources/
│   │       └── application.yaml     # Configuration
│   └── test/                        # Test files
├── docker-compose.yml               # Docker services configuration
├── Dockerfile                       # Application container definition
└── pom.xml                          # Maven dependencies
```

### Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

### License

This project is licensed under the Apache License 2.0.

### Authors

- **Edison Araújo**

### Acknowledgments

- Spring Boot team
- MongoDB
- RabbitMQ
- MailHog

---


### 機能

- 人物管理のための**RESTful API**（CRUD操作）
- データ永続化のための**MongoDB**
- 非同期メッセージキューイングのための**RabbitMQ**
- 人物登録時に自動送信される**メール通知**
- **Swagger/OpenAPI**ドキュメント
- 簡単なデプロイのための**Docker**コンテナ化
- 開発環境でのメールテスト用の**MailHog**

### 技術スタック

- **Java 21**
- **Spring Boot 4.0.1**
- **Spring Data MongoDB**
- **Spring AMQP (RabbitMQ)**
- **Spring Mail**
- **Lombok**
- **Maven**
- **Docker & Docker Compose**

### 前提条件

- Java 21以上
- Maven 3.6以上
- Docker と Docker Compose
- （オプション）Dockerを使用しない場合、MongoDB、RabbitMQ、MailHog

### はじめに

#### Dockerを使用する場合（推奨）

1. リポジトリをクローン:
```bash
git clone <repository-url>
cd pessoas-api
```

2. Docker Composeで全サービスを起動:
```bash
docker-compose up -d --build
```

これにより以下が起動します:
- **pessoas-api** ポート `8084`
- **MongoDB** ポート `27017`
- **RabbitMQ** ポート `5672`（管理UIは `15672`）
- **MailHog** ポート `1025`（SMTP）と `8025`（Web UI）

3. アプリケーションにアクセス:
   - API: http://localhost:8084
   - Swagger UI: http://localhost:8084/swagger-ui.html
   - RabbitMQ管理画面: http://localhost:15672 (guest/guest)
   - MailHog Web UI: http://localhost:8025

#### ローカルで実行する場合（Dockerなし）

1. MongoDBとRabbitMQがローカルで実行されていることを確認

2. `application.yaml`をローカル設定で更新

3. ビルドして実行:
```bash
./mvnw clean package
java -jar target/pessoas-api-0.0.1-SNAPSHOT.jar
```

### APIドキュメント

#### エンドポイント

##### 人物を作成
```http
POST /api/v1/pessoas
Content-Type: application/json

{
  "nome": "山田太郎",
  "email": "yamada@example.com"
}
```

**レスポンス:**
```json
{
  "id": "507f1f77bcf86cd799439011",
  "nome": "山田太郎",
  "email": "yamada@example.com"
}
```

#### Swaggerドキュメント

インタラクティブなAPIドキュメントは以下で利用可能:
- **Swagger UI**: http://localhost:8084/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8084/v3/api-docs

### アーキテクチャ

アプリケーションはイベント駆動型アーキテクチャに従います:

1. **APIがリクエストを受信** → MongoDBに人物を作成
2. **RabbitMQにメッセージ送信** → 人物データをJSONとしてシリアライズ
3. **MessageConsumerが処理** → デシリアライズしてウェルカムメールを送信
4. **MailHog経由でメール送信** → HTML形式のウェルカムメール

### メール通知

人物が登録されると、システムは自動的に:
1. RabbitMQキュー `pessoas-api` にメッセージを送信
2. `MessageConsumer`がメッセージを処理
3. HTMLウェルカムメールを生成
4. MailHog（開発環境）またはSMTPサーバー（本番環境）経由でメールを送信

### Dockerサービス

| サービス | ポート | 説明 |
|---------|------|------|
| pessoas-api | 8084 | メインAPIアプリケーション |
| MongoDB | 27017 | データベース |
| RabbitMQ | 5672 | メッセージブローカー |
| RabbitMQ管理画面 | 15672 | 管理UI |
| MailHog SMTP | 1025 | メールサーバー |
| MailHog Web UI | 8025 | メールビューア |

### 設定

#### 環境変数

以下の環境変数は `docker-compose.yml` で設定できます:

- `SPRING_DATA_MONGODB_URI`: MongoDB接続文字列
- `SPRING_RABBITMQ_HOST`: RabbitMQホスト
- `SPRING_RABBITMQ_PORT`: RabbitMQポート
- `SPRING_RABBITMQ_USERNAME`: RabbitMQユーザー名
- `SPRING_RABBITMQ_PASSWORD`: RabbitMQパスワード
- `SPRING_MAIL_HOST`: メールサーバーホスト
- `SPRING_MAIL_PORT`: メールサーバーポート

#### アプリケーションプロパティ

`application.yaml`の主要プロパティ:
- `mail.enabled`: メール機能の有効/無効（デフォルト: `true`）

### テスト

テストを実行:
```bash
./mvnw test
```

### ビルド

アプリケーションをビルド:
```bash
./mvnw clean package
```

JARファイルは `target/pessoas-api-0.0.1-SNAPSHOT.jar` に作成されます

### サービスの停止

すべてのDockerコンテナを停止:
```bash
docker-compose down
```

ボリュームも削除する場合:
```bash
docker-compose down -v
```

### プロジェクト構造

```
pessoas-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/com/cotiinformatica/pessoas_api/
│   │   │       ├── components/      # MessageProducer, MessageConsumer, MailComponent
│   │   │       ├── configurations/  # RabbitMQ, Swagger設定
│   │   │       ├── controllers/     # RESTコントローラー
│   │   │       ├── dtos/            # データ転送オブジェクト
│   │   │       ├── entities/        # MongoDBエンティティ
│   │   │       ├── repositories/    # MongoDBリポジトリ
│   │   │       └── services/         # ビジネスロジック
│   │   └── resources/
│   │       └── application.yaml     # 設定
│   └── test/                        # テストファイル
├── docker-compose.yml               # Dockerサービス設定
├── Dockerfile                       # アプリケーションコンテナ定義
└── pom.xml                          # Maven依存関係
```

### 貢献

貢献を歓迎します！プルリクエストを自由に提出してください。

### ライセンス

このプロジェクトはApache License 2.0の下でライセンスされています。

### 作成者

- **Edison Araújo**

### 謝辞

- Spring Bootチーム
- MongoDB
- RabbitMQ
- MailHog

---

**Note**: This is a development/demonstration project. For production use, ensure proper security configurations, error handling, and monitoring are in place.

**注意**: これは開発/デモンストレーションプロジェクトです。本番環境で使用する場合は、適切なセキュリティ設定、エラーハンドリング、監視が整っていることを確認してください。
