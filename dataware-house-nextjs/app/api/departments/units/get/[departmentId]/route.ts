import prismadb from "@/lib/prismadb";

export async function GET(
  req: Request,
  {
    params,
  }: {
    params: { departmentId: string };
  }
) {
  try {
    const units = await prismadb.units.findMany({
      where: {
        department_id: params.departmentId,
      },
    });
    await prismadb.$disconnect();
    return Response.json(units);
  } catch (error) {
    console.log("[GET_ALL]", error);
    return new Response("Internal error", { status: 500 });
  }
}
