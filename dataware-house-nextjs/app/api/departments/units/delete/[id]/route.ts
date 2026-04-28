import prismadb from "@/lib/prismadb";

export async function DELETE(
  req: Request,
  {
    params,
  }: {
    params: { id: string };
  }
) {
  try {
    const units = await prismadb.units.deleteMany({
      where: {
        id: params.id,
      },
    });
    await prismadb.$disconnect();
    return Response.json(units);
  } catch (error) {
    console.log("[DELETE]", error);
    return new Response("Internal error", { status: 500 });
  }
}
