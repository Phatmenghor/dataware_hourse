"use client";

import { TOKEN_COOKIES } from "@/constant/api-end-point";
import { getToken } from "@/lib/init-token";
import {
  departments,
  metaDatas,
  reportRoles,
  reports,
  units,
  users,
} from "@prisma/client";
import axios from "axios";
import {
  Cable,
  CheckCircle2,
  ChevronDown,
  LineChart,
  Loader2,
  PieChart,
  Radar,
  Repeat,
  Wallet,
} from "lucide-react";
import Link from "next/link";
import React, { useEffect, useState } from "react";

interface RootClientProps {
  clientId: string;
  department: departments | null;
  units: units[] | undefined;
}
export const RootClient: React.FC<RootClientProps> = ({
  clientId,
  department,
  units,
}) => {
  const [user, setUser] = useState<any>();
  const [reports, setReports] = useState<any>([]);
  const [categories, setCategories] = useState<metaDatas[]>([]);
  const [departments, setDepartments] = useState<departments[]>([]);

  const getUser = async () => {
    const userId = getToken(TOKEN_COOKIES.AUTH_ID);
    await axios.get(`/api/users/${userId}`).then((data) => {
      setUser(data.data);
    });
  };

  const getDepartments = async () => {
    try {
      await axios
        .get("/api/departments")
        .then(({ data }) => setDepartments(data));
    } catch (error) {}
  };

  const getCategory = async () => {
    try {
      await axios.get("/api/metadatas/type/report_category").then((data) => {
        setCategories(data.data);
      });
    } catch (error) {}
  };

  const getReportByCategory = async () => {
    await axios.get(`/api/reports`).then((data) => {
      const sorted = [...data.data].sort((a: any, b: any) =>
        a.name.localeCompare(b.name),
      );
      setReports(sorted);
    });
  };

  useEffect(() => {
    getUser();
    getDepartments();
    getCategory();
    getReportByCategory();
  }, []);

  return (
    <>
      <h3 className="capitalize font-semibold text-gray-700">
        {clientId} Department
      </h3>
      <div className="py-5 bg-white rounded-md mt-5">
        <h4 className="text-sm font-medium text-gray-500 border-b-2 w-full px-5 pb-3 flex items-center">
          {!user ? (
            <Loader2 className="animate-spin w-4 h-4 text-blue-500" />
          ) : (
            <CheckCircle2 className="w-4 h-4 text-green-500" />
          )}
          <span className="ml-2 uppercase">Report Lists</span>
        </h4>
        {/* Normal and Branch */}
        <div className="py-4">
          {clientId !== "risk" ? (
            <div className="px-5 grid grid-cols-3 gap-8">
              {units != undefined
                ? units!.length > 0
                  ? units?.map((unit, index) => (
                      <div key={index}>
                        <div className="flex items-center text-sm font-medium text-gray-700 py-2 w-full rounded-sm ">
                          <span className="p-1 rounded-full bg-green-400 mr-1.5"></span>
                          <span className="text-gray-500">{unit.name}</span>
                        </div>
                        <ul className="divide-y-2 divide-slate-100">
                          {user
                            ? reports.length > 0
                              ? reports
                                  .filter((rlt: any) => rlt.unit_id === unit.id)
                                  .map((item: any, key: number) =>
                                    item.reportRole.map((role: any) =>
                                      role.role_id === user.role_id ? (
                                        <li key={key}>
                                          <Link
                                            target="_blank"
                                            href={`/reports/${item.id}`}
                                            className="py-2 px-3 w-full flex items-center hover:text-blue-500 text-gray-500"
                                          >
                                            <svg
                                              xmlns="http://www.w3.org/2000/svg"
                                              viewBox="0 0 24 24"
                                              fill="currentColor"
                                              className="w-4 h-4 text-blue-400 mr-2"
                                            >
                                              <path d="M19.906 9c.382 0 .749.057 1.094.162V9a3 3 0 00-3-3h-3.879a.75.75 0 01-.53-.22L11.47 3.66A2.25 2.25 0 009.879 3H6a3 3 0 00-3 3v3.162A3.756 3.756 0 014.094 9h15.812zM4.094 10.5a2.25 2.25 0 00-2.227 2.568l.857 6A2.25 2.25 0 004.951 21H19.05a2.25 2.25 0 002.227-1.932l.857-6a2.25 2.25 0 00-2.227-2.568H4.094z" />
                                            </svg>

                                            <span className="text-xs font-medium">
                                              {item.name}
                                            </span>
                                          </Link>
                                        </li>
                                      ) : (
                                        ""
                                      ),
                                    ),
                                  )
                              : ""
                            : ""}
                        </ul>
                      </div>
                    ))
                  : ""
                : ""}
              <ul className="divide-y-2 divide-slate-100">
                {user
                  ? reports.length > 0
                    ? reports
                        .filter(
                          (rpl: reports) =>
                            rpl.department_id === department?.id &&
                            rpl.unit_id === "0",
                        )
                        .map((item: any, key: number) =>
                          item.reportRole.map((role: any) =>
                            role.role_id === user.role_id ? (
                              <li key={key}>
                                <Link
                                  target="_blank"
                                  href={`/reports/${item.id}`}
                                  className="py-2  w-full flex items-center hover:text-blue-500 text-gray-500"
                                >
                                  <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    viewBox="0 0 24 24"
                                    fill="currentColor"
                                    className="w-4 h-4 text-blue-400 mr-2"
                                  >
                                    <path d="M19.906 9c.382 0 .749.057 1.094.162V9a3 3 0 00-3-3h-3.879a.75.75 0 01-.53-.22L11.47 3.66A2.25 2.25 0 009.879 3H6a3 3 0 00-3 3v3.162A3.756 3.756 0 014.094 9h15.812zM4.094 10.5a2.25 2.25 0 00-2.227 2.568l.857 6A2.25 2.25 0 004.951 21H19.05a2.25 2.25 0 002.227-1.932l.857-6a2.25 2.25 0 00-2.227-2.568H4.094z" />
                                  </svg>

                                  <span className="text-xs font-medium">
                                    {item.name}
                                  </span>
                                </Link>
                              </li>
                            ) : (
                              ""
                            ),
                          ),
                        )
                    : ""
                  : ""}
              </ul>
            </div>
          ) : (
            ""
          )}

          {/* IT and Risk */}
          <div className="grid grid-cols-3 gap-8 p-3 px-5">
            {(clientId === "risk" && user?.department.code === "rsd") ||
            (clientId === "risk" && user?.role.code === "it_admin")
              ? departments
                  .filter(
                    (cat) => cat.type === "HO" && cat.short_name !== "risk",
                  )
                  .map((category: any) => (
                    <div key={category.id}>
                      <div className="flex  items-center text-sm font-medium text-700 py-1.5 px-3 w-full rounded-sm ">
                        {category.short_name === "operation" ? (
                          <Repeat className="w-4 h-4 mr-2" />
                        ) : (
                          ""
                        )}

                        {category.short_name === "credit" ? (
                          <PieChart className="w-4 h-4 mr-2" />
                        ) : (
                          ""
                        )}
                        {category.short_name === "business" ? (
                          <LineChart className="w-4 h-4 mr-2" />
                        ) : (
                          ""
                        )}
                        {category.short_name === "finance" ? (
                          <Wallet className="w-4 h-4 mr-2" />
                        ) : (
                          ""
                        )}
                        {category.short_name === "complaince" ? (
                          <Cable className="w-4 h-4 mr-2" />
                        ) : (
                          ""
                        )}
                        {category.short_name === "audit" ? (
                          <Radar className="w-4 h-4 mr-2" />
                        ) : (
                          ""
                        )}
                        <span>{category.name}</span>
                      </div>

                      <ul className="divide-y-2 divide-slate-100 mt-2">
                        {category.units.length > 0
                          ? category.units.map((unit: any, index: any) => (
                              <div key={index}>
                                <div className="flex items-center text-sm font-medium text-gray-700 py-2 px-3 w-full rounded-sm ">
                                  <span className="p-1 rounded-full bg-blue-400 mr-1.5"></span>
                                  <span className="text-gray-500">
                                    {unit.name}
                                  </span>
                                </div>
                                <div>
                                  <ul className="divide-y-2 divide-slate-100 px-5">
                                    {user
                                      ? reports
                                          .filter(
                                            (fl: any) =>
                                              fl.unit_id === unit.id && fl.mis,
                                          )
                                          .map((item: any) =>
                                            item ? (
                                              <li key={item.id}>
                                                <Link
                                                  target="_blank"
                                                  href={`/reports/${item.id}`}
                                                  className="py-2 w-full flex items-center hover:text-blue-600 text-gray-600"
                                                >
                                                  <svg
                                                    xmlns="http://www.w3.org/2000/svg"
                                                    viewBox="0 0 24 24"
                                                    fill="currentColor"
                                                    className="w-4 h-4 text-blue-400 mr-2"
                                                  >
                                                    <path d="M19.906 9c.382 0 .749.057 1.094.162V9a3 3 0 00-3-3h-3.879a.75.75 0 01-.53-.22L11.47 3.66A2.25 2.25 0 009.879 3H6a3 3 0 00-3 3v3.162A3.756 3.756 0 014.094 9h15.812zM4.094 10.5a2.25 2.25 0 00-2.227 2.568l.857 6A2.25 2.25 0 004.951 21H19.05a2.25 2.25 0 002.227-1.932l.857-6a2.25 2.25 0 00-2.227-2.568H4.094z" />
                                                  </svg>

                                                  <span className="text-xs font-medium">
                                                    {item.name}
                                                  </span>
                                                </Link>
                                              </li>
                                            ) : (
                                              ""
                                            ),
                                          )
                                      : ""}
                                  </ul>
                                </div>
                              </div>
                            ))
                          : ""}
                        {user
                          ? reports
                              .filter(
                                (fl: any) =>
                                  fl.department_id === category.id &&
                                  fl.unit_id === "0" &&
                                  fl.mis,
                              )
                              .map((item: any) =>
                                item ? (
                                  <li key={item.id} className="px-3">
                                    <Link
                                      target="_blank"
                                      href={`/reports/${item.id}`}
                                      className="py-2 w-full flex items-center hover:text-blue-600 text-gray-600"
                                    >
                                      <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        viewBox="0 0 24 24"
                                        fill="currentColor"
                                        className="w-4 h-4 text-blue-400 mr-2"
                                      >
                                        <path d="M19.906 9c.382 0 .749.057 1.094.162V9a3 3 0 00-3-3h-3.879a.75.75 0 01-.53-.22L11.47 3.66A2.25 2.25 0 009.879 3H6a3 3 0 00-3 3v3.162A3.756 3.756 0 014.094 9h15.812zM4.094 10.5a2.25 2.25 0 00-2.227 2.568l.857 6A2.25 2.25 0 004.951 21H19.05a2.25 2.25 0 002.227-1.932l.857-6a2.25 2.25 0 00-2.227-2.568H4.094z" />
                                      </svg>

                                      <span className="text-xs font-medium">
                                        {item.name}
                                      </span>
                                    </Link>
                                  </li>
                                ) : (
                                  ""
                                ),
                              )
                          : ""}
                      </ul>
                    </div>
                  ))
              : ""}
          </div>
        </div>
      </div>
    </>
  );
};
