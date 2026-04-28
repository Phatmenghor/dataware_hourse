import prismadb from "@/lib/prismadb";

export async function PATCH(
  req: Request,
  {
    params,
  }: {
    params: { id: string };
  }
) {
  try {
    const body = await req.json();
    const { name } = body;

    if (!name) {
      return new Response("Name is required", { status: 400 });
    }

    const units = await prismadb.units.update({
      data: {
        name,
      },
      where: {
        id: params.id,
      },
    });
    await prismadb.$disconnect();
    return Response.json(units);
  } catch (error) {
    console.log("[UPDATE_UNIT]", error);
    return new Response("Internal error", { status: 500 });
  }
}
