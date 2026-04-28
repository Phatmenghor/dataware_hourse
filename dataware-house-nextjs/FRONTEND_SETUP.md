# Frontend Setup - Connected to Spring Boot Backend

## Overview
This Next.js frontend has been migrated from using local Prisma/Next.js APIs to consuming the Spring Boot backend REST APIs.

## Prerequisites
- Node.js 16+ and npm/yarn
- Spring Boot backend running on `http://localhost:8081` (or your configured URL)

## Setup Instructions

### 1. Install Dependencies
```bash
npm install
# or
yarn install
```

### 2. Configure Backend URL
Create a `.env.local` file in the root directory:

```bash
cp .env.example .env.local
```

Edit `.env.local`:
```env
# Development
NEXT_PUBLIC_BACKEND_URL=http://localhost:8081/api/v1

# Or for production
NEXT_PUBLIC_BACKEND_URL=https://your-backend-domain.com/api/v1
```

### 3. Run Development Server
```bash
npm run dev
# or
yarn dev
```

Open [http://localhost:3000](http://localhost:3000) in your browser.

## API Endpoints Reference

All API calls are now routed through the Spring Boot backend:

| Resource | Endpoint |
|----------|----------|
| Users | `GET/POST /users` |
| Departments | `GET/POST /departments` |
| Positions | `GET/POST /positions` |
| Roles | `GET/POST /roles` |
| Reports | `GET/POST /reports` |
| Widgets | `GET/POST /widgets` |
| Units | `GET/POST /units` |
| Metadata | `GET/POST /metadata` |

### Example API Call

All service files already use the configured backend URL:

```typescript
// service/report-service.ts
export async function getAllReportService(query: string) {
  const response = await axios.post(
    `${API_END_POINT.REPORTS}`,  // Uses NEXT_PUBLIC_BACKEND_URL from env
    {},
    {
      headers: {
        Authorization: `Bearer ${token}`,
      }
    }
  );
  return response.data;
}
```

## Changes Made

### Removed
- ❌ Prisma ORM (`@prisma/client`)
- ❌ Prisma schema and migrations
- ❌ Next.js API routes (`/app/api/...`)
- ❌ Local database dependencies

### Updated
- ✅ API endpoints to point to Spring Boot backend
- ✅ Environment configuration with `.env.example`
- ✅ Build script (removed `prisma generate`)
- ✅ package.json dependencies

### Kept
- ✅ All frontend UI components
- ✅ State management (Zustand)
- ✅ Form validation (React Hook Form)
- ✅ Styling (Tailwind CSS)
- ✅ Charts and visualizations

## Authentication Flow

JWT tokens are handled the same way:

1. User logs in → receives JWT token from backend
2. Token stored in cookies
3. All subsequent requests include token in Authorization header
4. Backend validates token

## Testing the Integration

### 1. Test Backend Connection
```bash
curl -X GET http://localhost:8081/api/v1/departments \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### 2. Check Frontend Console
Open browser DevTools (F12) → Network tab
- All API calls should go to `http://localhost:8081/api/v1/...`
- Check for 200 responses from backend

### 3. Verify Data Flow
- Login with credentials
- Navigate to any data page (Users, Departments, etc.)
- Check that data loads from backend

## Build for Production

```bash
npm run build
npm run start
```

Or deploy to Vercel:
```bash
# Vercel automatically handles build and deployment
vercel deploy --prod
```

Make sure to set environment variables in Vercel dashboard:
```
NEXT_PUBLIC_BACKEND_URL=https://your-backend-domain.com/api/v1
```

## Troubleshooting

### CORS Errors
If you get CORS errors, ensure your Spring Boot backend has:
```java
@CrossOrigin("*")  // Or specify allowed origins
```

### 401 Unauthorized
- Check JWT token is valid
- Verify token is being sent in Authorization header
- Check backend authentication is working

### 404 Not Found
- Verify backend is running
- Check NEXT_PUBLIC_BACKEND_URL is correct
- Verify endpoint paths match backend routes

### Connection Refused
- Ensure Spring Boot backend is running
- Check port 8081 is correct (or your configured port)
- Verify firewall allows connections

## Additional Notes

- No database operations on frontend
- All data validation happens on backend
- Frontend is now purely a UI consumer
- Clean separation of concerns
- Better security (no database exposure)

## Support

For issues with:
- **Frontend**: Check Next.js console and Network tab
- **Backend**: Check Spring Boot logs
- **Integration**: Verify environment variables and API endpoints
