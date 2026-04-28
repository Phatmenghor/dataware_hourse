"use client";
import { Button } from "@/components/ui/button";
import { Heart } from "lucide-react";
import React, { useState } from "react";
import * as z from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import axios from "axios";
import toast from "react-hot-toast";
import { useRouter } from "next/navigation";

interface WidgetFavoriteClientProp {
  isFavor: boolean;
  userId: string | "";
  widgetId: string | undefined;
}

const formSchema = z.object({
  user_id: z.string().min(1),
  widget_id: z.string().min(1),
});

export const WidgetFavoriteClient: React.FC<WidgetFavoriteClientProp> = ({
  isFavor,
  userId,
  widgetId,
}) => {
  const router = useRouter();
  const [loading, setLoading] = useState(false);

  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      user_id: userId,
      widget_id: widgetId,
    },
  });

  const onSubmit = async () => {
    const toastId = toast.loading("Loading...");

    try {
      setLoading(true);
      console.log(form.getValues().user_id);
      const response = await axios.post(`/api/widget-favorite/`, form);

      if (response) {
        router.refresh();
        toast.success("Add to favorited.", {
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

  const onDelete = async () => {
    const toastId = toast.loading("Loading...");

    try {
      setLoading(true);
      console.log(form.getValues().user_id);
      const response = await axios.post(`/api/widget-favorite/${userId}`, form);

      if (response) {
        router.refresh();
        toast.success("Your favorite removed.", {
          id: toastId,
        });
      }
    } catch (error) {
      toast.error("Internal server erorr.", {
        id: toastId,
      });
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <Button
        variant={isFavor ? "destructive" : "outline"}
        size="sm"
        onClick={isFavor ? onDelete : onSubmit}
      >
        <Heart className="w-4 h-4 mr-1" />
        <span className="text-sx">{isFavor ? "Remove Favor" : "Favorite"}</span>
      </Button>
    </div>
  );
};
