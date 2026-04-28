import prismadb from "@/lib/prismadb";

export async function POST(
  req: Request,
  params: {
    params: { id: string };
  }
) {
  const body = await req.json();
  const { widget_id } = body.formState.defaultValues;

  try {
    const favorite = await prismadb.widgetFavorites.deleteMany({
      where: {
        AND: [{ user_id: params.params.id }, { widget_id: widget_id }],
      },
    });
    await prismadb.$disconnect();
    return Response.json(favorite);
  } catch (error) {
    return new Response("Internal error", { status: 500 });
  }
}

export async function GET(
  req: Request,
  params: {
    params: { id: string };
  }
) {
  try {
    const favorite = await prismadb.widgetFavorites.findMany({
      where: {
        user_id: params.params.id,
      },
    });
    await prismadb.$disconnect();
    return Response.json(favorite);
  } catch (error) {
    return new Response("Internal error", { status: 500 });
  }
}
