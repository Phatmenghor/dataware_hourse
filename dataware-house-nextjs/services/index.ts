// API Client
export { default as apiClient } from "./api-client";

// Feature Services
export * from "./users";
export * from "./departments";
export * from "./positions";
export * from "./roles";
export * from "./units";
export * from "./widgets";
export * from "./reports";
export * from "./metadata";

// Legacy backward compatibility exports
// Users
export { userService } from "./users";
export * from "./users/service";

// Departments
export { departmentService } from "./departments";
export * from "./departments/service";

// Positions
export { positionService } from "./positions";
export * from "./positions/service";

// Roles
export { roleService } from "./roles";
export * from "./roles/service";

// Reports
export * from "./reports/service";

// Units
export * from "./units/service";

// Widgets
export * from "./widgets/service";

// Metadata
export * from "./metadata/service";
