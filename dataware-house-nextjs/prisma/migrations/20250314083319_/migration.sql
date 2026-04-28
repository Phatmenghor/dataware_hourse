-- CreateTable
CREATE TABLE "departments" (
    "id" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "short_name" TEXT NOT NULL,
    "code" TEXT NOT NULL,
    "status" BOOLEAN NOT NULL DEFAULT false,
    "type" TEXT,
    "created_at" TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP(3),

    CONSTRAINT "departments_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "positions" (
    "id" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "code" TEXT NOT NULL,
    "status" BOOLEAN NOT NULL DEFAULT false,
    "created_at" TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP(3),

    CONSTRAINT "positions_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "roles" (
    "id" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "code" TEXT NOT NULL,
    "status" BOOLEAN NOT NULL DEFAULT false,
    "created_at" TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP(3),

    CONSTRAINT "roles_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "users" (
    "id" TEXT NOT NULL,
    "full_name" TEXT,
    "staff_id" TEXT,
    "username" TEXT,
    "password" TEXT,
    "password_expired" TIMESTAMP(3),
    "password_reseted_at" TIMESTAMP(3),
    "status" BOOLEAN NOT NULL DEFAULT false,
    "department_id" TEXT,
    "position_id" TEXT,
    "role_id" TEXT,
    "created_at" TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP(3),

    CONSTRAINT "users_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "reports" (
    "id" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "code" TEXT,
    "status" BOOLEAN NOT NULL DEFAULT false,
    "mis" BOOLEAN NOT NULL DEFAULT false,
    "category" TEXT,
    "unit_id" TEXT,
    "department_id" TEXT,
    "created_at" TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP(3),

    CONSTRAINT "reports_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "reportColumns" (
    "id" TEXT NOT NULL,
    "display" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "type" TEXT NOT NULL,
    "status" BOOLEAN NOT NULL DEFAULT false,
    "report_role_id" TEXT NOT NULL,
    "ordering" INTEGER NOT NULL DEFAULT 0,
    "created_at" TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP(3),

    CONSTRAINT "reportColumns_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "reportParams" (
    "id" TEXT NOT NULL,
    "display" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "type" TEXT NOT NULL,
    "status" BOOLEAN NOT NULL DEFAULT false,
    "report_id" TEXT NOT NULL,
    "created_at" TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP(3),

    CONSTRAINT "reportParams_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "metaDatas" (
    "id" TEXT NOT NULL,
    "type" TEXT NOT NULL,
    "label" TEXT NOT NULL,
    "value" TEXT NOT NULL,

    CONSTRAINT "metaDatas_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "reportRoles" (
    "id" TEXT NOT NULL,
    "report_id" TEXT NOT NULL,
    "role_id" TEXT NOT NULL,
    "created_at" TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP(3),

    CONSTRAINT "reportRoles_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "widgets" (
    "id" TEXT NOT NULL,
    "name" TEXT,
    "type" TEXT,
    "code" TEXT,
    "position" TEXT,
    "created_at" TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP(3),

    CONSTRAINT "widgets_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "widgetDisplays" (
    "id" TEXT NOT NULL,
    "display" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "type" TEXT NOT NULL,
    "ordering" INTEGER NOT NULL DEFAULT 0,
    "status" BOOLEAN NOT NULL DEFAULT false,
    "widget_id" TEXT NOT NULL,
    "created_at" TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP(3),

    CONSTRAINT "widgetDisplays_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "widgetParams" (
    "id" TEXT NOT NULL,
    "display" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "type" TEXT NOT NULL,
    "ordering" INTEGER NOT NULL DEFAULT 0,
    "status" BOOLEAN NOT NULL DEFAULT false,
    "widget_id" TEXT NOT NULL,
    "created_at" TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP(3),

    CONSTRAINT "widgetParams_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "widgetRoles" (
    "id" TEXT NOT NULL,
    "widget_id" TEXT NOT NULL,
    "role_id" TEXT NOT NULL,
    "created_at" TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP(3),

    CONSTRAINT "widgetRoles_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "widgetFavorites" (
    "id" TEXT NOT NULL,
    "widget_id" TEXT NOT NULL,
    "user_id" TEXT NOT NULL,
    "created_at" TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP(3),

    CONSTRAINT "widgetFavorites_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "units" (
    "id" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "status" BOOLEAN NOT NULL DEFAULT false,
    "department_id" TEXT NOT NULL,
    "created_at" TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP(3),

    CONSTRAINT "units_pkey" PRIMARY KEY ("id")
);

-- CreateIndex
CREATE UNIQUE INDEX "users_staff_id_key" ON "users"("staff_id");

-- CreateIndex
CREATE UNIQUE INDEX "users_username_key" ON "users"("username");

-- CreateIndex
CREATE INDEX "users_department_id_idx" ON "users"("department_id");

-- CreateIndex
CREATE INDEX "users_position_id_idx" ON "users"("position_id");

-- CreateIndex
CREATE INDEX "users_role_id_idx" ON "users"("role_id");

-- CreateIndex
CREATE INDEX "reports_unit_id_idx" ON "reports"("unit_id");

-- CreateIndex
CREATE INDEX "reports_department_id_idx" ON "reports"("department_id");

-- CreateIndex
CREATE INDEX "reportColumns_report_role_id_idx" ON "reportColumns"("report_role_id");

-- CreateIndex
CREATE INDEX "reportParams_report_id_idx" ON "reportParams"("report_id");

-- CreateIndex
CREATE INDEX "reportRoles_report_id_idx" ON "reportRoles"("report_id");

-- CreateIndex
CREATE INDEX "reportRoles_role_id_idx" ON "reportRoles"("role_id");

-- CreateIndex
CREATE INDEX "widgetDisplays_widget_id_idx" ON "widgetDisplays"("widget_id");

-- CreateIndex
CREATE INDEX "widgetParams_widget_id_idx" ON "widgetParams"("widget_id");

-- CreateIndex
CREATE INDEX "widgetRoles_widget_id_idx" ON "widgetRoles"("widget_id");

-- CreateIndex
CREATE INDEX "widgetRoles_role_id_idx" ON "widgetRoles"("role_id");

-- CreateIndex
CREATE INDEX "widgetFavorites_widget_id_idx" ON "widgetFavorites"("widget_id");

-- CreateIndex
CREATE INDEX "widgetFavorites_user_id_idx" ON "widgetFavorites"("user_id");

-- CreateIndex
CREATE INDEX "units_department_id_idx" ON "units"("department_id");
