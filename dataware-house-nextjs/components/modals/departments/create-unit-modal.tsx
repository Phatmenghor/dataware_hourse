"use client";
import * as z from "zod";
import { Button } from "@/components/ui/button";
import { zodResolver } from "@hookform/resolvers/zod";
import { useEffect, useState } from "react";
import { Modal } from "../../ui/modal";

import { useForm } from "react-hook-form";
import axios from "axios";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import toast from "react-hot-toast";
import { useRouter } from "next/navigation";

import { useUnitModal } from "@/hooks/use-unit-modal";
import { units } from "@prisma/client";
import { Check, Pencil, Trash } from "lucide-react";

const formSchema = z.object({
  name: z.string().min(1),
});

export const CreateUnitModal = () => {
  const router = useRouter();

  const unitModal = useUnitModal();

  const [loading, setLoading] = useState(false);

  const [unitdata, setUnitData] = useState<units[]>([]);

  const [id, setId] = useState<string | undefined>("");
  const [editName, setEditName] = useState<string | undefined>("");

  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      name: "",
    },
  });

  const onSubmit = async (values: z.infer<typeof formSchema>) => {
    try {
      setLoading(true);

      const response = await axios.post(
        `/api/departments/units/create/${unitModal.departmentId}`,
        values
      );

      if (response) {
        router.refresh();
        getUnits();
        toast.success("Unit's department created.");
        form.reset();
      }
    } catch (error) {
      toast.error("Internal server erorr.");
    } finally {
      setLoading(false);
    }
  };

  const onDelete = async (id: string) => {
    try {
      setLoading(true);

      const response = await axios.delete(
        `/api/departments/units/delete/${id}`
      );

      if (response) {
        router.refresh();
        getUnits();
        toast.success("Unit delete.");
        form.reset();
      }
    } catch (error) {
      toast.error("Internal server erorr.");
    } finally {
      setLoading(false);
    }
  };

  const getUnits = async () => {
    if (unitModal.isOpen) {
      await axios
        .get(`/api/departments/units/get/${unitModal.departmentId}`)
        .then(({ data }) => setUnitData(data));
    }
  };

  const onSetEdit = (id: string) => {
    setId(id);
    console.log(id);
  };

  const onUpdate = async (id: string) => {
    try {
      setLoading(true);

      const response = await axios.patch(
        `/api/departments/units/update/${id}`,
        { name: editName }
      );

      if (response) {
        router.refresh();
        getUnits();
        setId("");
        toast.success("Unit updated.");
        form.reset();
      }
    } catch (error) {
      toast.error("Internal server erorr.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    getUnits();
    console.log(unitModal.isOpen);
  }, [unitModal]);

  return (
    <Modal
      title="Unit's department"
      description=""
      isOpen={unitModal.isOpen}
      onClose={unitModal.onClose}
      size="w-[500px]"
    >
      <div>
        <div className="space-y-4 py-2 pb-2">
          <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)}>
              <FormField
                control={form.control}
                name="name"
                render={({ field }) => (
                  <FormItem className="mb-5">
                    <FormLabel className="text-xs">Name</FormLabel>
                    <FormControl>
                      <Input
                        disabled={loading}
                        placeholder="name"
                        {...field}
                        className="shadow-none py-5 rounded"
                      />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />

              <div className="space-x-2 pt-2 flex items-center justify-end">
                <Button
                  disabled={loading}
                  variant={"outline"}
                  onClick={() => unitModal.onClose()}
                  type="button"
                >
                  Cancel
                </Button>
                <Button disabled={loading} type="submit">
                  Create
                </Button>
              </div>
            </form>
          </Form>
        </div>
        <div className="pt-3 mt-2 text-md text-gray-600 border-t w-full"></div>
        <div className=" rounded-md p-2">
          <table className="w-full">
            <thead>
              <tr className="border-b">
                <th className="p-2 text-sm font-medium text-gray-600 text-left">
                  #
                </th>
                <th className="p-2 text-sm font-medium text-gray-600 text-left">
                  Name
                </th>
                <th className="p-2 text-sm font-medium text-gray-600 text-left">
                  Action
                </th>
              </tr>
            </thead>
            <tbody>
              {unitdata.map((item, index) => (
                <tr key={index}>
                  <td className="p-2 text-sm text-gray-600 border-b">
                    {index + 1}
                  </td>
                  <td className="p-2 text-sm text-gray-600 border-b">
                    {id === item.id ? (
                      <input
                        defaultValue={item.name}
                        className="py-1 px-2 border border-gray-300 rounded focus:outline-none"
                        onChange={(e) => setEditName(e.target.value)}
                        autoFocus
                      />
                    ) : (
                      item.name
                    )}
                  </td>
                  <td className="p-2 text-sm text-gray-600 border-b">
                    <div>
                      {id === item.id ? (
                        <Button
                          size="icon"
                          className="text-gray-600 w-7 h-7 shadow-none rounded-md bg-gray-200 hover:bg-gray-300 hover:text-blue-600"
                          onClick={() => onUpdate(item.id)}
                        >
                          <Check className="w-4 h-4" />
                        </Button>
                      ) : (
                        <Button
                          size="icon"
                          className="text-gray-600 w-7 h-7 shadow-none rounded-md bg-gray-200 hover:bg-gray-300 hover:text-blue-600"
                          onClick={() => onSetEdit(item.id)}
                        >
                          <Pencil className="w-4 h-4" />
                        </Button>
                      )}

                      <Button
                        size="icon"
                        className="ml-3 text-gray-600 w-7 h-7 shadow-none rounded-md bg-gray-200 hover:bg-gray-300 hover:text-red-600"
                        onClick={() => onDelete(item.id)}
                      >
                        <Trash className="w-4 h-4" />
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </Modal>
  );
};
