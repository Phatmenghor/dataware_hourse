import prismadb from "@/lib/prismadb";

export async function POST(req: Request) {
  try {
    const body = await req.json();
    const { name, department_id, unit_id } = body;

    if (!name) {
      return new Response("Name is required", { status: 400 });
    }

    const report = await prismadb.reports.create({
      data: {
        name,
        department_id,
        unit_id,
      },
    });
    await prismadb.$disconnect();
    return Response.json(report);
  } catch (error) {
    console.log("[REPORT_POST]", error);
    return new Response("Internal error", { status: 500 });
  }
}

export async function GET(req: Request) {
  try {
    const roles = await prismadb.reports.findMany({
      include: {
        reportRole: true,
        department: true,
        unit: true,
      },
      orderBy: {
        created_at: "desc",
      },
    });
    await prismadb.$disconnect();
    return Response.json(roles);
  } catch (error) {
    console.log("[REPORT_GET]", error);
    return new Response("Internal error", { status: 500 });
  }
}
