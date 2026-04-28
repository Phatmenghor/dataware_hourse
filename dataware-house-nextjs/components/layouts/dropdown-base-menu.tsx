"use client";

import {
  BadgePercent,
  BarChart,
  BarChart3,
  BarChartBig,
  BarChartHorizontalBig,
  Beaker,
  Briefcase,
  Building,
  Cable,
  ChevronRight,
  Command,
  Component,
  Cpu,
  Database,
  DatabaseBackup,
  DollarSign,
  Grip,
  Home,
  LayoutGrid,
  LineChart,
  PieChart,
  QrCode,
  Radar,
  Radiation,
  Repeat,
  Settings,
  ShieldCheck,
  User2,
  Users2,
  Wallet,
} from "lucide-react";
import { usePathname } from "next/navigation";
import { Popover, PopoverContent, PopoverTrigger } from "../ui/popover";
import Link from "next/link";
import { Button } from "../ui/button";
import { cn } from "@/lib/utils";
import React, { useEffect, useState } from "react";
import axios from "axios";
import toast from "react-hot-toast";
import { reports, users } from "@prisma/client";

interface DropdownBaseMenuProps {
  reportLists: reports[];
  role: string;
  label: string;
}

export const DropdownBaseMenu: React.FC<DropdownBaseMenuProps> = ({
  reportLists,
  role,
  label,
}) => {
  const pathname = usePathname();

  const generalManagRoutes = [
    {
      href: "/e-channel",
      label: "E-Channel",
      icon: <Cpu className="w-4 h-4 mr-2" />,
      active: pathname === "/e-channel",
    },
    {
      href: "/information-technology",
      label: "Information Technology",
      icon: <Briefcase className="w-4 h-4 mr-2" />,
      active: pathname === "/it-depart",
    },
    
  ];


  return (
    <Popover>
      <PopoverTrigger asChild>
        <Button
          size="icon"
          variant="ghost"
          className="text-gray-600 shadow-none hover:bg-white hover:text-blue-600 h-8 w-auto px-2 rounded-lg flex items-center"
        >
          {label !== "" ? (
            <span className="text-xs  mr-2">{label}</span>
          ) : (
            ""
          )}
          <ChevronRight className="w-4 h-4"/>
        </Button>
      </PopoverTrigger>
      <PopoverContent className="bg-white p-3 rounded shadow-xl border mt-2 w-auto">
        <div
         
        >
          <div className="flex flex-col">
           
            <ul>
              {generalManagRoutes.map((item,index) => (
                <li key={index}>
                  <Link
                    href={item.href}
                    className={cn(
                      " text-gray-700 px-3 py-2 my-2 text-xs hover:bg-blue-100 hover:text-blue-700 flex items-center rounded"
                    )}
                  >
                    {item.icon}
                    <span className="text-xs mt-1">{item.label}</span>
                  </Link>
                </li>
              ))}
            </ul>
          </div>
          
        </div>
      </PopoverContent>
    </Popover>
  );
};
