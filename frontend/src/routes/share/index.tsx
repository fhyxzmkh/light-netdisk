import { createFileRoute } from "@tanstack/react-router";

export const Route = createFileRoute("/share/")({
  component: RouteComponent,
});

function RouteComponent() {
  return <>123423</>;
}
