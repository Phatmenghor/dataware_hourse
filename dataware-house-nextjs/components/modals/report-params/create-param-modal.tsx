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
import { useParams, useRouter } from "next/navigation";
import axios from "axios";
import toast from "react-hot-toast";
import { useForm } from "react-hook-form";
import {
  Binary,
  Calendar,
  DollarSign,
  LayoutList,
  WrapText,
} from "lucide-react";
import { cn } from "@/lib/utils";
import { useModal } from "@/hooks/use-modal";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { ScrollArea } from "@/components/ui/scroll-area";
import { metaDatas } from "@prisma/client";

const formSchema = z.object({
  display: z.string().min(1),
  name: z.string().min(1),
  type: z.string().min(1),
  report_id: z.string(),
});

export const CreateParamModal = () => {
  const params = useParams();
  const router = useRouter();

  const modalUse = useModal();

  const [loading, setLoading] = useState(false);
  const [type, setType] = useState("");

  const [paramNames, setParamNames] = useState<metaDatas[]>([]);

  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      display: "",
      name: "",
      type: "text",
      report_id: `${params.reportId}`,
    },
  });

  const getParamNames = async () => {
    try {
      await axios.get("/api/metadatas/type/params").then((data) => {
        setParamNames(data.data);
      });
    } catch (error) {}
  };

  const onSubmit = async (values: z.infer<typeof formSchema>) => {
    const toastId = toast.loading("Loading...");

    try {
      setLoading(true);
      values.type = type;
      values.report_id = `${params.reportId}`;
      const response = await axios.post(
        `/api/report-params/${params.reportId}`,
        values
      );

      if (response) {
        router.refresh();
        modalUse.onClose();
        toast.success("Report param created.", {
          id: toastId,
        });
        form.reset();
      }
    } catch (error) {
      toast.error("Internal server erorr.", {
        id: toastId,
      });
    } finally {
      setLoading(false);
    }
  };

  const listType = [
    {
      id: 1,
      label: "Text",
      value: "text",
      icon: <WrapText className="w-4 h-4" />,
      active: type === "text" ? "border border-blue-500 bg-blue-100" : "",
    },
    {
      id: 2,
      label: "Date",
      value: "date",
      icon: <Calendar className="w-4 h-4" />,
      active: type === "date" ? "border border-blue-500 bg-blue-100" : "",
    },
    {
      id: 3,
      label: "Select",
      value: "select",
      icon: <LayoutList className="w-4 h-4" />,
      active: type === "select" ? "border border-blue-500 bg-blue-100" : "",
    },
  ];

  useEffect(() => {
    getParamNames();
  }, []);

  return (
    <Modal
      title="Create Param"
      description=""
      isOpen={modalUse.isOpen}
      onClose={modalUse.onClose}
      size="w-[600px]"
    >
      <div>
        <div className="space-y-4 py-2 pb-2">
          <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)}>
              <FormField
                control={form.control}
                name="display"
                render={({ field }) => (
                  <FormItem className="mb-5">
                    <FormLabel className="text-xs">Display</FormLabel>
                    <FormControl>
                      <Input
                        disabled={loading}
                        placeholder="display"
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
                name="name"
                render={({ field }) => (
                  <FormItem className="mb-5">
                    <FormLabel className="text-xs">Name</FormLabel>

                    <Select
                      name="category"
                      disabled={loading}
                      onValueChange={field.onChange}
                      value={field.value}
                    >
                      <FormControl>
                        <SelectTrigger className="w-full shadow-none rounded py-5 text-xs">
                          <SelectValue placeholder="Select Category" />
                        </SelectTrigger>
                      </FormControl>
                      <SelectContent>
                        <ScrollArea className="h-[250px]">
                          <SelectItem value="0">Select category</SelectItem>

                          {paramNames.map((item) => (
                            <SelectItem key={item.id} value={item.value}>
                              {item.label}
                            </SelectItem>
                          ))}
                        </ScrollArea>
                      </SelectContent>
                    </Select>
                    <FormMessage />
                  </FormItem>
                )}
              />

              <div className="py-2">
                <span className="text-xs">Type</span>
                <div className="grid grid-cols-3 gap-8 mt-4">
                  {listType.map((item) => (
                    <div
                      key={item.id}
                      onClick={() => setType(item.value)}
                      className={cn(
                        "flex border border-gray-300 rounded-sm hover:cursor-pointer",
                        item.active
                      )}
                    >
                      <div className="bg-slate-100 p-1 border-r flex items-center text-gray-500 rounded-l-md">
                        {item.icon}
                      </div>
                      <div className="text-xs text-gray-700 p-2">
                        {item.label}
                      </div>
                    </div>
                  ))}
                </div>
              </div>

              <div className="space-x-2 pt-6 flex items-center justify-end">
                <Button
                  type="button"
                  disabled={loading}
                  variant={"outline"}
                  onClick={() => modalUse.onClose()}
                >
                  Cancel
                </Button>
                <Button type="submit" disabled={loading}>
                  Create
                </Button>
              </div>
            </form>
          </Form>
        </div>
      </div>
    </Modal>
  );
};
