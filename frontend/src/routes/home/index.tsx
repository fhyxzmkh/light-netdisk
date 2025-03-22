import { createFileRoute } from "@tanstack/react-router";
import HomeSideBar from "../../components/home/HomeSideBar.tsx";
import { HomeNavBar } from "../../components/home/HomeNavBar.tsx";

export const Route = createFileRoute("/home/")({
  // beforeLoad: async () => {
  //   const { is_login } = useUserStore.getState(); // 获取当前的 is_login 状态
  //   if (!is_login) {
  //     throw redirect({ to: "/user/login" }); // 如果用户未登录，重定向到登录页面
  //   }
  // },
  component: RouteComponent,
});

function RouteComponent() {
  return (
    <>
      <div className="flex mt-1 h-[90vh]">
        <div className="w-1/5">
          <HomeSideBar />
        </div>
        <div className="w-4/5 bg-gray-100">
          <HomeNavBar />
          Home Page
        </div>
      </div>
    </>
  );
}
