# Inboop Backend

Enterprise-level lead generation and management system that captures and analyzes Instagram DM conversations using AI-powered classification.

## 📋 Overview

Inboop helps businesses convert Instagram DMs into actionable leads by:
- Receiving real-time Instagram DM notifications via Meta webhooks
- Using AI to detect language and classify message intent (inquiry, order, support, etc.)
- Organizing conversations into trackable leads with labels and status
- Managing orders from initial inquiry to delivery
- Providing analytics and insights on customer interactions

## 🏗️ Architecture

This project uses a **Domain-Driven Design (DDD)** architecture with clear separation of concerns:

```
com.inboop.backend
├── auth/              # Authentication & User Management
│   ├── controller/    # Login, registration endpoints
│   ├── service/       # User authentication logic
│   ├── repository/    # User data access
│   ├── entity/        # User entity
│   └── dto/           # Login/Register request DTOs
│
├── business/          # Business Account Management
│   ├── entity/        # Business (Instagram account details)
│   └── repository/    # Business data access
│
├── instagram/         # Instagram Integration
│   ├── webhook/       # Webhook endpoints for Meta
│   ├── service/       # Instagram Graph API integration
│   ├── entity/        # Instagram-related entities
│   └── dto/           # Webhook payload DTOs
│
├── lead/              # Lead Management (Core Domain)
│   ├── entity/        # Lead, Conversation, Message
│   ├── enums/         # LeadStatus, LeadType, MessageSentiment
│   ├── repository/    # Lead data access
│   ├── service/       # Lead management logic
│   └── controller/    # Lead REST API endpoints
│
├── order/             # Order Tracking
│   ├── entity/        # Order entity
│   ├── enums/         # OrderStatus
│   ├── repository/    # Order data access
│   └── controller/    # Order REST API endpoints
│
├── ai/                # AI/ML Services
│   ├── service/       # Language detection, intent classification
│   └── config/        # AI provider configuration
│
├── notification/      # Real-time Notifications
│   ├── websocket/     # WebSocket handlers
│   └── service/       # Notification logic
│
├── analytics/         # Dashboard Analytics
│   ├── controller/    # Analytics endpoints
│   └── service/       # Analytics calculations
│
├── config/            # Global Configuration
│   └── SecurityConfig # Security, CORS, OAuth2
│
└── shared/            # Shared Utilities
    ├── exception/     # Global exception handling
    ├── dto/           # Common DTOs (ApiResponse, PageResponse)
    ├── constant/      # Application constants
    └── util/          # Utility classes
```

## 🚀 Getting Started

### Prerequisites

- **Java 17** or higher
- **Maven 3.6+** (or use included Maven wrapper)
- **Git**

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/nitheeshsudireddy/Inboop.git
   cd Inboop
   ```

2. **Set up environment variables**

   Create a `.env` file or export these variables:
   ```bash
   # Google OAuth2 (optional for OAuth login)
   export GOOGLE_CLIENT_ID=your-google-client-id
   export GOOGLE_CLIENT_SECRET=your-google-client-secret

   # Database (H2 is default for development)
   export DB_URL=jdbc:h2:file:./data/inboop
   export DB_DRIVER=org.h2.Driver
   export DB_USER=sa
   export DB_PASSWORD=password
   export DB_PLATFORM=org.hibernate.dialect.H2Dialect
   export DDL_AUTO=update

   # Instagram Webhook
   export INSTAGRAM_WEBHOOK_VERIFY_TOKEN=your-verify-token

   # Frontend CORS (if using separate frontend)
   export ALLOWED_ORIGINS=http://localhost:3000
   ```

3. **Build the project**
   ```bash
   ./mvnw clean install
   ```

4. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Access the application**
   - Application: http://localhost:8080
   - H2 Console: http://localhost:8080/h2-console
   - API Base URL: http://localhost:8080/api/v1

## 📡 API Endpoints

### Authentication
- `GET /` - Home page
- `GET /register` - Registration page
- `POST /register` - User registration
- `GET /dashboard` - Dashboard (authenticated)
- `GET /api/v1/auth/me` - Current user info

### Instagram Webhooks
- `GET /api/v1/webhooks/instagram` - Webhook verification (Meta requirement)
- `POST /api/v1/webhooks/instagram` - Receive Instagram DM notifications

### Leads (Coming Soon)
- `GET /api/v1/leads` - List all leads
- `GET /api/v1/leads/{id}` - Get lead details
- `POST /api/v1/leads` - Create lead
- `PUT /api/v1/leads/{id}` - Update lead
- `PATCH /api/v1/leads/{id}/status` - Update lead status

### Orders (Coming Soon)
- `GET /api/v1/orders` - List all orders
- `POST /api/v1/orders` - Create order
- `PATCH /api/v1/orders/{id}/status` - Update order status

## 🗄️ Database

### Development (H2)
The application uses **H2** in-memory database by default for development.

**Access H2 Console:**
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:file:./data/inboop`
- Username: `sa`
- Password: `password`

### Production (PostgreSQL - Recommended)

For production, switch to PostgreSQL:

1. **Add PostgreSQL dependency** (already in pom.xml):
   ```xml
   <dependency>
       <groupId>org.postgresql</groupId>
       <artifactId>postgresql</artifactId>
   </dependency>
   ```

2. **Update environment variables**:
   ```bash
   export DB_URL=jdbc:postgresql://localhost:5432/inboop
   export DB_DRIVER=org.postgresql.Driver
   export DB_USER=postgres
   export DB_PASSWORD=your-password
   export DB_PLATFORM=org.hibernate.dialect.PostgreSQLDialect
   export DDL_AUTO=validate  # Use Liquibase for migrations
   export H2_CONSOLE_ENABLED=false
   ```

## 🔑 Core Entities

### User
- User authentication (local + OAuth2)
- Role-based access control

### Business
- Represents Instagram business accounts
- Stores access tokens for Instagram Graph API
- One business can have multiple leads

### Lead
- Customer lead from Instagram DM
- Status tracking (NEW, CONTACTED, QUALIFIED, CONVERTED, etc.)
- Type classification (INQUIRY, ORDER_REQUEST, SUPPORT, etc.)
- Assignable to team members
- Labels for organization

### Conversation & Message
- Thread of messages between business and customer
- Language detection
- AI-powered sentiment analysis
- Translation support

### Order
- Order tracking from lead to delivery
- Status management (PENDING, CONFIRMED, SHIPPED, DELIVERED)
- Customer details and tracking information

## 🤖 AI Integration (Planned)

The system will integrate with AI services for:
- **Language Detection** - Detect customer message language
- **Intent Classification** - Categorize messages (inquiry, order, complaint, etc.)
- **Sentiment Analysis** - Analyze customer sentiment
- **Translation** - Translate messages to business's preferred language

Planned providers:
- OpenAI GPT-4 API
- Google Cloud Natural Language API
- AWS Comprehend

## 🔒 Security

- **Spring Security** for authentication
- **OAuth2** support (Google)
- **CORS** configured for frontend separation
- **CSRF** protection (disabled for REST APIs)
- **BCrypt** password encoding

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.5.5
- **Language**: Java 17
- **Build Tool**: Maven
- **Database**: H2 (dev), PostgreSQL (prod)
- **Security**: Spring Security + OAuth2
- **Validation**: Jakarta Validation (Hibernate Validator)
- **Template Engine**: Thymeleaf (for admin UI)
- **API Documentation**: SpringDoc OpenAPI (planned)

## 📦 Dependencies

Key dependencies:
- `spring-boot-starter-web` - REST API
- `spring-boot-starter-data-jpa` - Database access
- `spring-boot-starter-security` - Authentication
- `spring-boot-starter-oauth2-client` - OAuth2 login
- `spring-boot-starter-validation` - DTO validation
- `spring-boot-starter-thymeleaf` - Server-side templates
- `h2` - Development database

## 🔄 Development Workflow

### Running Locally
```bash
./mvnw spring-boot:run
```

### Running Tests
```bash
./mvnw test
```

### Building for Production
```bash
./mvnw clean package
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

## 🌐 Frontend

This backend is designed to work with a **separate frontend repository**.

**Recommended Frontend Stack:**
- React + TypeScript
- Next.js (for SSR/SSG)
- Ant Design or Material-UI

**Frontend should connect to:**
- API: `http://localhost:8080/api/v1`
- WebSocket: `ws://localhost:8080/ws` (for real-time notifications)

## 🚧 Roadmap

### Phase 1: Foundation ✅ (Current)
- [x] Domain-driven architecture setup
- [x] Core entities created
- [x] Authentication system
- [x] Instagram webhook endpoint
- [x] Exception handling

### Phase 2: Core Features (In Progress)
- [ ] Implement Lead service and REST API
- [ ] Instagram Graph API integration
- [ ] AI service integration (OpenAI)
- [ ] WebSocket notifications
- [ ] Business account management

### Phase 3: Advanced Features
- [ ] Order management system
- [ ] Analytics dashboard
- [ ] Multi-language support
- [ ] Team collaboration features
- [ ] Automated responses

### Phase 4: Scale & Optimize
- [ ] Redis caching
- [ ] RabbitMQ message queue
- [ ] Database migrations (Liquibase)
- [ ] API documentation (Swagger)
- [ ] Performance optimization

## 📝 Environment Variables Reference

| Variable | Description | Default |
|----------|-------------|---------|
| `GOOGLE_CLIENT_ID` | Google OAuth client ID | - |
| `GOOGLE_CLIENT_SECRET` | Google OAuth client secret | - |
| `DB_URL` | Database JDBC URL | `jdbc:h2:file:./data/inboop` |
| `DB_DRIVER` | Database driver class | `org.h2.Driver` |
| `DB_USER` | Database username | `sa` |
| `DB_PASSWORD` | Database password | `password` |
| `DB_PLATFORM` | Hibernate dialect | `org.hibernate.dialect.H2Dialect` |
| `DDL_AUTO` | Hibernate DDL mode | `update` |
| `INSTAGRAM_WEBHOOK_VERIFY_TOKEN` | Meta webhook verification | `inboop_verify_token` |
| `ALLOWED_ORIGINS` | CORS allowed origins | `http://localhost:3000` |
| `BACKEND_URL` | Backend base URL | `http://localhost:8080` |

## 🐛 Troubleshooting

### Port Already in Use
```bash
# Find and kill process on port 8080
lsof -ti:8080 | xargs kill -9
```

### Database Connection Issues
- Check H2 console at http://localhost:8080/h2-console
- Verify JDBC URL matches your configuration
- Ensure `data/` directory has write permissions

### Git Authentication
- Use Personal Access Token instead of password
- Token needs `repo` scope
- Configure credential helper: `git config --global credential.helper osxkeychain`

## 🤝 Contributing

1. Create a feature branch: `git checkout -b feature/amazing-feature`
2. Commit your changes: `git commit -m 'Add amazing feature'`
3. Push to branch: `git push origin feature/amazing-feature`
4. Open a Pull Request

## 📄 License

This project is private and proprietary.

## 👤 Author

**Nitheesh Sudireddy**
- GitHub: [@nitheeshsudireddy](https://github.com/nitheeshsudireddy)

## 📞 Support

For questions or issues, please open an issue on GitHub.

---

**Built with ❤️ for modern lead management**
