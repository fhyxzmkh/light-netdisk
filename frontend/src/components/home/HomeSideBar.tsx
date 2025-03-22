import React from "react";
import {
  FileOutlined,
  VideoCameraOutlined,
  PictureOutlined,
  FileTextOutlined,
  SoundOutlined,
  FolderOutlined,
} from "@ant-design/icons";
import type { MenuProps } from "antd";
import { Menu, Progress } from "antd";

type MenuItem = Required<MenuProps>["items"][number];

const categoryItems: MenuItem[] = [
  { key: "all", label: "全部", icon: <FolderOutlined /> },
  { key: "video", label: "视频", icon: <VideoCameraOutlined /> },
  { key: "audio", label: "音频", icon: <SoundOutlined /> },
  { key: "image", label: "图片", icon: <PictureOutlined /> },
  { key: "doc", label: "文档", icon: <FileTextOutlined /> },
  { key: "other", label: "其他", icon: <FileOutlined /> },
];

const HomeSideBar: React.FC = () => {
  const onClick: MenuProps["onClick"] = (e) => {
    console.log("menu click", e);
  };

  return (
    <div className="flex flex-col h-full bg-white w-full justify-around">
      <div className="p-4">
        <div className="text-gray-500 text-sm font-medium mb-2">文件分类</div>
        <Menu
          onClick={onClick}
          mode="inline"
          items={categoryItems}
          defaultSelectedKeys={["all"]}
          className="border-none"
        />
      </div>

      <div className="p-4">
        <div className="text-sm text-gray-600 mb-2">
          已使用 75% (375GB/500GB)
        </div>
        <Progress
          percent={75}
          strokeColor="#1890ff"
          trailColor="#f0f0f0"
          showInfo={false}
        />
        <div className="text-xs text-gray-400 mt-1">剩余空间 125GB</div>
      </div>
    </div>
  );
};

export default HomeSideBar;
