import prismadb from "@/lib/prismadb";
import Charts from "../../components/charts";
import { WidgetFavoriteClient } from "./components/client";

const WidgetListsPage = async ({
  params,
}: {
  params: {
    userId: string;
  };
}) => {
  const auth = await prismadb.users.findFirst({
    where: {
      id: params.userId,
    },
    include: {
      role: {
        include: {
          widgetRoles: {
            include: {
              widget: true,
            },
          },
        },
      },
    },
  });

  const favorites = await prismadb.widgetFavorites.findMany({
    where: {
      user_id: params.userId,
    },
    include: {
      widget: true,
    },
  });

  return (
    <div className="px-10 py-10 min-h-screen">
      <div className="flex justify-between items-center">
        <h1 className="text-sm font-semibold text-gray-700">Widget Lists</h1>
      </div>
      <div className="mt-10">
        {" "}
        {auth?.role?.widgetRoles.map((item, key) => (
          <div className="my-5 bg-white  rounded" key={key}>
            <div className="w-full flex items-center px-5 pt-5">
              <WidgetFavoriteClient
                isFavor={favorites.some(
                  (favor) => favor.widget_id === item.widget?.id
                )}
                userId={auth?.id}
                widgetId={item.widget?.id}
              />
            </div>
            <Charts
              widgetId={item.widget?.id ?? ""}
              type={item.widget?.type ?? ""}
              code={item.widget?.code ?? ""}
              title={item.widget?.name ?? ""}
            />
          </div>
        ))}
      </div>
    </div>
  );
};

export default WidgetListsPage;
