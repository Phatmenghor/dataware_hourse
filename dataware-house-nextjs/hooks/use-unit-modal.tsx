import { units } from "@prisma/client";
import { create } from "zustand";

interface useUnitModalStore {
  isOpen: boolean;
  departmentId: string;
  units: units[];
  onOpen: (id: string, units: units[]) => void;
  onClose: () => void;
}

export const useUnitModal = create<useUnitModalStore>((set) => ({
  isOpen: false,
  departmentId: "",
  units: [],
  onOpen: (id: string, data: units[]) =>
    set({ isOpen: true, departmentId: id, units: data }),
  onClose: () => set({ isOpen: false }),
}));
