import prismadb from "@/lib/prismadb";
import { RootClient } from "./components/client";

const ClientPage = async ({
  params,
}: {
  params: {
    clientId: string;
  };
}) => {
  const department = await prismadb.departments.findFirst({
    where: {
      short_name: params.clientId,
    },
    include: {
      units: {
        orderBy: {
          name: "asc",
        },
      },
    },
  });

  return (
    <div className="px-10 py-4 min-h-screen">
      <RootClient
        clientId={params.clientId}
        department={department}
        units={department?.units}
      />
    </div>
  );
};

export default ClientPage;
