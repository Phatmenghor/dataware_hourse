import prismadb from "@/lib/prismadb";

export async function POST(
  req: Request,
  {
    params,
  }: {
    params: { departmentId: string };
  }
) {
  try {
    const body = await req.json();
    const { name } = body;

    if (!name) {
      return new Response("Name is required", { status: 400 });
    }

    const units = await prismadb.units.create({
      data: {
        name,
        department_id: params.departmentId,
      },
    });
    await prismadb.$disconnect();
    return Response.json(units);
  } catch (error) {
    console.log("[DEPART_UNIT_PATH]", error);
    return new Response("Internal error", { status: 500 });
  }
}
