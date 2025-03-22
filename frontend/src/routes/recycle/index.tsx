import { createFileRoute } from "@tanstack/react-router";

export const Route = createFileRoute("/recycle/")({
  component: RouteComponent,
});

function RouteComponent() {
  return <>123</>;
}
