"use client";
import * as z from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useEffect, useState } from "react";
import { Modal } from "../../ui/modal";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { useRouter } from "next/navigation";
import axios from "axios";
import toast from "react-hot-toast";
import { useForm } from "react-hook-form";
import { useReportModal } from "@/hooks/use-report-modal";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { ReportColumn } from "@/app/(dashboard)/(routes)/report-tamplate/components/columns";
import { Label } from "@/components/ui/label";
import { departments, units } from "@prisma/client";
import { ScrollArea } from "@/components/ui/scroll-area";
import { Check, X } from "lucide-react";

interface UpdateReportModalProps {
  isOpen: boolean;
  onClose: () => void;
  data: ReportColumn;
}

const formSchema = z.object({
  name: z.string().min(1),
  department_id: z.string(),
  unit_id: z.string(),
});

export const UpdateReportModal: React.FC<UpdateReportModalProps> = ({
  isOpen,
  onClose,
  data,
}) => {
  const router = useRouter();

  const [loading, setLoading] = useState(false);
  const [roles, setRoles] = useState([
    {
      id: "",
      name: "",
    },
  ]);

  const [departments, setDepartments] = useState<departments[]>([]);
  const [units, setUnits] = useState<units[]>([]);

  const [departId, setDepartId] = useState<string>(data.department_id);

  const getRoles = async () => {
    try {
      await axios.get("/api/roles").then((data) => {
        setRoles(data.data);
      });
    } catch (error) {}
  };

  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      name: data.name,
      department_id: data.department_id,
      unit_id: data.unit_id,
    },
  });

  const onSubmit = async (values: z.infer<typeof formSchema>) => {
    try {
      const toastId = toast.loading("Loading...");

      setLoading(true);
      values.department_id = departId;
      const response = await axios.patch(`/api/reports/${data.id}`, values);

      if (response) {
        router.refresh();
        window.location.reload();
        form.reset();
        onClose();
        toast.success("Report updated", {
          id: toastId,
        });
      }
    } catch (error) {
      toast.error("Internal server erorr.");
    } finally {
      setLoading(false);
    }
  };

  const getDepartments = async () => {
    try {
      await axios.get("/api/departments").then((data) => {
        setDepartments(data.data);
      });
    } catch (error) {}
  };

  const getUnits = async (val: string) => {
    form.resetField("unit_id");
    setDepartId(val);
    try {
      await axios
        .get(`/api/departments/units/get/${val}`)
        .then(({ data }) => setUnits(data));
    } catch (error) {
      toast.error("Internal server erorr.");
    }
  };

  useEffect(() => {
    getRoles();
    getDepartments();
    getUnits(departId);
  }, [departId]);

  return (
    <Modal
      title="Update Report"
      description=""
      isOpen={isOpen}
      onClose={onClose}
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

              <FormField
                control={form.control}
                name="department_id"
                render={({ field }) => (
                  <FormItem className="my-5">
                    <FormLabel className="text-[11px]">Department </FormLabel>
                    <Select
                      name="department_id"
                      disabled={loading}
                      onValueChange={(value) => getUnits(value)}
                      value={departId !== "0" ? departId : field.value}
                    >
                      <FormControl>
                        <SelectTrigger className="w-full shadow-none rounded py-5 text-xs">
                          <SelectValue placeholder="Select department" />
                        </SelectTrigger>
                      </FormControl>
                      <SelectContent>
                        <ScrollArea className="h-[250px]">
                          <SelectItem value="0">Select department</SelectItem>

                          {departments
                            .filter((ft) => ft.type === "HO")
                            .map((item) => (
                              <SelectItem key={item.id} value={item.id}>
                                {item.name}
                              </SelectItem>
                            ))}
                        </ScrollArea>
                      </SelectContent>
                    </Select>

                    <FormMessage />
                  </FormItem>
                )}
              />

              {units.length > 0 ? (
                <FormField
                  control={form.control}
                  name="unit_id"
                  render={({ field }) => (
                    <FormItem className="my-5">
                      <FormLabel className="text-[11px]">Units </FormLabel>
                      <Select
                        name="unit_id"
                        disabled={loading}
                        onValueChange={field.onChange}
                        value={field.value}
                      >
                        <FormControl>
                          <SelectTrigger className="w-full shadow-none rounded py-5 text-xs">
                            <SelectValue placeholder="Select Unit" />
                          </SelectTrigger>
                        </FormControl>
                        <SelectContent>
                          <ScrollArea className="h-[250px]">
                            <SelectItem value="0">Select Unit</SelectItem>

                            {units.map((item) => (
                              <SelectItem key={item.id} value={item.id}>
                                {item.name}
                              </SelectItem>
                            ))}
                          </ScrollArea>
                        </SelectContent>
                      </Select>

                      <FormMessage />
                    </FormItem>
                  )}
                />
              ) : (
                ""
              )}

              <div className="space-x-2 pt-6 flex items-center justify-end">
                <Button
                  type="button"
                  disabled={loading}
                  variant={"outline"}
                  onClick={() => onClose()}
                >
                  Cancel
                </Button>
                <Button disabled={loading} type="submit">
                  Save change
                </Button>
              </div>
            </form>
          </Form>
        </div>
      </div>
    </Modal>
  );
};
