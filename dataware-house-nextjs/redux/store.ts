import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./features/auth/authSlice";
import usersReducer from "./features/users/usersSlice";
import departmentsReducer from "./features/departments/departmentsSlice";
import positionsReducer from "./features/positions/positionsSlice";
import rolesReducer from "./features/roles/rolesSlice";
import unitsReducer from "./features/units/unitsSlice";
import widgetsReducer from "./features/widgets/widgetsSlice";
import reportsReducer from "./features/reports/reportsSlice";
import metadataReducer from "./features/metadata/metadataSlice";

export const store = configureStore({
  reducer: {
    auth: authReducer,
    users: usersReducer,
    departments: departmentsReducer,
    positions: positionsReducer,
    roles: rolesReducer,
    units: unitsReducer,
    widgets: widgetsReducer,
    reports: reportsReducer,
    metadata: metadataReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
