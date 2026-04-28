import prismadb from "@/lib/prismadb";

export async function POST(req: Request) {
  try {
    const body = await req.json();
    const { user_id, widget_id } = body.formState.defaultValues;

    if (!user_id) {
      return new Response("user_id is required", { status: 400 });
    }

    if (!widget_id) {
      return new Response("widget_id is required", { status: 400 });
    }

    const favorite = await prismadb.widgetFavorites.create({
      data: {
        user_id,
        widget_id,
      },
    });
    await prismadb.$disconnect();
    return Response.json(favorite);
  } catch (error) {
    return new Response("Internal error", { status: 500 });
  }
}

export async function GET() {
  try {
    const favorite = await prismadb.widgetFavorites.findMany();
    await prismadb.$disconnect();
    return Response.json(favorite);
  } catch (error) {
    return new Response("Internal error", { status: 500 });
  }
}
