import { Menu, MenuProps, Dropdown, Avatar, Typography, Layout } from "antd";
import {
  UserOutlined,
  LogoutOutlined,
  CaretDownOutlined,
  HomeOutlined,
  ShareAltOutlined,
  DeleteOutlined,
  SettingOutlined,
} from "@ant-design/icons";
import { MenuItemType } from "antd/es/menu/interface";
import useUserStore from "../../store/userStore.ts";
import { useNavigate } from "@tanstack/react-router";

const { Header } = Layout;

const { Text } = Typography;

const mainNavItems: MenuProps["items"] = [
  {
    key: "/home",
    label: "首页",
    icon: <HomeOutlined />,
  },
  {
    key: "/share",
    label: "分享",
    icon: <ShareAltOutlined />,
  },
  {
    key: "/recycle",
    label: "回收站",
    icon: <DeleteOutlined />,
  },
  {
    key: "/settings",
    label: "设置",
    icon: <SettingOutlined />,
  },
];

export const NavBar = () => {
  const navigate = useNavigate();
  const logout = useUserStore((state) => state.logout);
  const userAvatar = useUserStore((state) => state.avatar);

  const handleMenuClick: MenuProps["onClick"] = (e) => {
    navigate({ to: e.key });
  };

  const handleLogout = () => {
    logout();
    navigate({
      to: "/user/login",
    });
  };

  // 用户下拉菜单的选项
  const userMenuItems: MenuItemType[] = [
    {
      key: "profile",
      label: "Profile",
      icon: <UserOutlined />,
    },
    {
      key: "logout",
      label: "Logout",
      icon: <LogoutOutlined />,
      onClick: handleLogout,
    },
  ];

  return (
    <>
      <Layout>
        <Header className="flex w-full items-center">
          <div className="text-white mr-12 font-bold text-xl">
            Light Netdisk
          </div>
          <Menu
            theme="dark"
            mode="horizontal"
            defaultSelectedKeys={["/home"]}
            items={mainNavItems}
            style={{ flex: 1, minWidth: 0 }}
            onClick={handleMenuClick}
          />

          <Dropdown menu={{ items: userMenuItems }}>
            <div
              style={{
                display: "flex",
                alignItems: "center",
                gap: "8px",
                cursor: "pointer",
              }}
            >
              {userAvatar === "" ? (
                <Avatar icon={<UserOutlined />} />
              ) : (
                <Avatar src={userAvatar} />
              )}
              <Text strong style={{ color: "white" }}>
                Username
              </Text>
              <CaretDownOutlined style={{ color: "white" }} />
            </div>
          </Dropdown>
        </Header>
      </Layout>
    </>
  );
};
