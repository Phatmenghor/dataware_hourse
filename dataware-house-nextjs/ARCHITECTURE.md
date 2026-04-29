# Dataware House Frontend Architecture

This document describes the refactored frontend architecture based on khmer_project's proven patterns.

## Project Structure

```
src/
├── app/                          # Next.js 13+ app directory (routes)
├── components/                   # Shared components
│   ├── common/                   # Reusable UI components (Button, Input, Modal, etc.)
│   ├── forms/                    # Form components
│   ├── tables/                   # Table components
│   ├── modals/                   # Modal dialogs
│   ├── cards/                    # Card components
│   ├── layouts/                  # Layout components
│   ├── loading/                  # Loading skeletons
│   ├── pagination/               # Pagination components
│   └── index.ts                  # Component exports
├── constants/                    # Application constants
│   ├── api-endpoints.ts         # API endpoint definitions
│   └── index.ts                 # Constants exports
├── context/                      # React Context (for global state)
├── data/                         # Mock/seed data
├── enums/                        # TypeScript enums
│   ├── common.ts                # Common enums (Status, etc.)
│   ├── user.ts                  # User-related enums
│   ├── report.ts                # Report-related enums
│   └── index.ts                 # Enums exports
├── hooks/                        # Custom React hooks
│   ├── useApi.ts                # API call hook with loading/error
│   ├── useForm.ts               # Form state management
│   ├── useLocalStorage.ts       # Local storage hook
│   ├── use-*.tsx                # Feature-specific hooks
│   └── index.ts                 # Hooks exports
├── i18n/                         # Internationalization
├── lib/                          # Utility functions & libraries
│   ├── api-client.ts            # Axios instance configuration
│   ├── init-token.ts            # Token initialization
│   ├── hash-password.ts         # Password hashing
│   ├── convert-month.ts         # Date utilities
│   └── index.ts                 # Library exports
├── messages/                     # Translation/message strings
├── middleware.ts                 # Next.js middleware
├── redux/                        # Redux state management
│   ├── store.ts                 # Redux store configuration
│   ├── index.ts                 # Redux exports
│   └── features/                # Feature-based Redux structure
│       ├── auth/
│       │   ├── authSlice.ts    # Redux reducer
│       │   ├── authAPI.ts      # API calls
│       │   ├── hooks.ts        # Custom hooks (useAuth)
│       │   ├── types.ts        # TypeScript types
│       │   └── index.ts        # Feature exports
│       ├── users/
│       │   ├── usersSlice.ts
│       │   ├── usersAPI.ts
│       │   ├── hooks.ts
│       │   ├── types.ts
│       │   └── index.ts
│       ├── departments/
│       ├── positions/
│       ├── roles/
│       ├── units/
│       ├── widgets/
│       ├── reports/
│       ├── metadata/
│       └── ...
├── services/                     # API service layer
│   ├── api-client.ts            # Shared API client
│   ├── users/
│   │   ├── index.ts             # User API with types
│   │   └── service.ts           # User service (legacy compatibility)
│   ├── departments/
│   ├── positions/
│   ├── roles/
│   ├── units/
│   ├── widgets/
│   ├── reports/
│   ├── metadata/
│   └── index.ts                 # Services exports
├── styles/                       # Global styles/CSS
├── types/                        # TypeScript type definitions
│   ├── auth.ts                  # Auth types
│   ├── user.ts                  # User types
│   ├── department.ts            # Department types
│   ├── position.ts              # Position types
│   ├── role.ts                  # Role types
│   ├── unit.ts                  # Unit types
│   ├── widget.ts                # Widget types
│   ├── report.ts                # Report types
│   ├── metadata.ts              # Metadata types
│   └── index.ts                 # Type exports
└── utils/                        # Utility functions
    ├── date.ts                  # Date utilities
    ├── validation.ts            # Validation functions
    └── index.ts                 # Utils exports
```

## Redux Feature Architecture

Each feature follows a consistent pattern:

### Feature Directory Structure

```
redux/features/{featureName}/
├── {featureName}Slice.ts   # Redux reducer & actions
├── {featureName}API.ts      # API calls
├── hooks.ts                 # Custom hooks (use{Feature})
├── types.ts                 # TypeScript types
└── index.ts                 # Feature exports
```

### Example: Users Feature

**types.ts** - Type definitions for the feature
```typescript
export interface UsersState {
  users: User[];
  selectedUser: User | null;
  loading: boolean;
  error: string | null;
  success: boolean;
}
```

**usersSlice.ts** - Redux reducer & actions
```typescript
const usersSlice = createSlice({
  name: "users",
  initialState,
  reducers: {
    fetchUsersSuccess: (state, action) => { ... },
    // ... more actions
  },
});
```

**usersAPI.ts** - API calls
```typescript
export const usersAPI = {
  async getAllUsers(): Promise<User[]> { ... },
  async createUser(data): Promise<User> { ... },
  // ... more API methods
};
```

**hooks.ts** - Custom hooks for components
```typescript
export const useUsers = () => {
  const dispatch = useDispatch<AppDispatch>();
  const { users, loading, error } = useSelector((state: RootState) => state.users);
  
  const fetchAllUsers = async () => { ... };
  // ... more hooks
  
  return { users, loading, error, fetchAllUsers, ... };
};
```

## Component Organization

### Common Components (`components/common/`)
Reusable UI components from shadcn/ui and custom implementations
- Button, Input, Modal, Dialog, Form, Table, etc.

### Feature Components
Feature-specific components are organized under `redux/features/{feature}/components/`
- UserForm, DepartmentTable, RoleModal, etc.

### Usage Example

```typescript
// Import from common components
import { Button, Input, Modal } from "@/components/common";

// Import feature hooks
import { useUsers } from "@/redux/features/users/hooks";

// Component
export function UserPage() {
  const { users, loading, fetchAllUsers } = useUsers();
  
  useEffect(() => {
    fetchAllUsers();
  }, []);
  
  return (
    <div>
      {loading && <Spinner />}
      <Button onClick={() => fetchAllUsers()}>Refresh</Button>
      {/* ... */}
    </div>
  );
}
```

## State Management Pattern

### Using Redux Hooks

```typescript
import { useUsers } from "@/redux/features/users";

function UserList() {
  const { users, loading, error, fetchAllUsers } = useUsers();
  
  useEffect(() => {
    fetchAllUsers();
  }, []);
  
  if (loading) return <Spinner />;
  if (error) return <Alert>{error}</Alert>;
  
  return (
    <ul>
      {users.map(user => <li key={user.id}>{user.name}</li>)}
    </ul>
  );
}
```

## API Services Pattern

API services handle all backend communication:

```typescript
// redux/features/users/usersAPI.ts
export const usersAPI = {
  async getAllUsers(): Promise<User[]> {
    const response = await apiClient.get("/users");
    return response.data;
  },
  
  async createUser(data: CreateUserRequest): Promise<User> {
    const response = await apiClient.post("/users", data);
    return response.data;
  },
};
```

## Hooks Pattern

Custom hooks provide clean component interface:

```typescript
// Using the hook in a component
const MyComponent = () => {
  const { 
    users, 
    loading, 
    error, 
    success,
    fetchAllUsers,
    createUser,
    deleteUser,
    clearError,
    resetSuccess
  } = useUsers();
  
  // ... component logic
};
```

## Type Safety

All features have proper TypeScript types:

```typescript
// types/user.ts
export interface User {
  id: string;
  name: string;
  email: string;
  status: UserStatus;
}

export interface CreateUserRequest {
  name: string;
  email: string;
  password: string;
}
```

## Constants & Enums

### Constants
API endpoints and configuration values:
```typescript
// constants/api-endpoints.ts
export const API_END_POINT = {
  USERS: `${BACKEND_URL}/users`,
  DEPARTMENTS: `${BACKEND_URL}/departments`,
  // ...
};
```

### Enums
Type-safe enumeration values:
```typescript
// enums/user.ts
export enum UserStatus {
  ACTIVE = 'ACTIVE',
  INACTIVE = 'INACTIVE',
  SUSPENDED = 'SUSPENDED',
}
```

## Import Patterns

### Recommended Imports

```typescript
// From features
import { useUsers } from "@/redux/features/users";
import { usersAPI } from "@/redux/features/users";

// From components
import { Button, Input } from "@/components/common";

// From types
import type { User, CreateUserRequest } from "@/types/user";

// From enums
import { UserStatus } from "@/enums/user";

// From constants
import { API_END_POINT } from "@/constants/api-endpoints";

// From hooks
import { useApi, useForm } from "@/hooks";
```

## Folder Consolidation

The following consolidations were made:

1. **constant/** → **constants/** (with backward compatibility)
2. **service/** → **services/** (merged with existing services)
3. **ui/** → **components/common/** (renamed for clarity)

## Adding a New Feature

1. Create feature directory under `redux/features/{featureName}/`
2. Create `{featureName}Slice.ts` with Redux reducer
3. Create `{featureName}API.ts` with API calls
4. Create `hooks.ts` with custom hooks
5. Create `types.ts` with TypeScript types
6. Create `index.ts` with feature exports
7. Add reducer to `redux/store.ts`

Example structure:
```
redux/features/myfeature/
├── myfeatureSlice.ts
├── myfeatureAPI.ts
├── hooks.ts
├── types.ts
└── index.ts
```

## Best Practices

1. **Keep Redux slices simple** - Focus on state shape only
2. **Organize API calls separately** - Keep API logic in API files
3. **Use custom hooks** - Abstract complex state logic into hooks
4. **Type everything** - Use TypeScript extensively
5. **Keep components pure** - Use hooks to manage state
6. **Reuse common components** - Build upon the common UI library
7. **Error handling** - Always handle errors in API calls and display to users
8. **Loading states** - Show appropriate loading indicators

## Migration Notes

Legacy imports will still work through backward compatibility exports:
- Old service imports will redirect to new API services
- Old constant imports will redirect to new constants
- Components can be gradually migrated to use new hooks

However, **new code should always use the new architecture patterns**.
