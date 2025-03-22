import { Button, Input, Space, Dropdown, MenuProps } from "antd";
import {
  UploadOutlined,
  FolderAddOutlined,
  DeleteOutlined,
  SwapOutlined,
  SearchOutlined,
  ReloadOutlined,
} from "@ant-design/icons";
import { useState } from "react";

export const HomeNavBar = () => {
  const [selectedCount, setSelectedCount] = useState(0);
  const [searchText, setSearchText] = useState("");

  const handleNewFolder = () => {
    console.log("新建文件夹");
  };

  const handleBatchDelete = () => {
    console.log("批量删除");
  };

  const handleBatchMove = () => {
    console.log("批量移动");
  };

  const handleSearch = (value: string) => {
    console.log("搜索:", value);
    setSearchText(value);
  };

  const handleReloadFile = () => {
    console.log("刷新");
  };

  return (
    <>
      <div className="bg-white p-4 border-b border-gray-200 flex items-center justify-between">
        <Space size="middle">
          <Button color="primary" variant="outlined" icon={<UploadOutlined />}>
            上传
          </Button>

          <Button
            color="primary"
            variant="outlined"
            icon={<FolderAddOutlined />}
            onClick={handleNewFolder}
          >
            新建文件夹
          </Button>

          <Space size="small">
            <Button
              color="danger"
              variant="outlined"
              icon={<DeleteOutlined />}
              onClick={handleBatchDelete}
              // disabled={selectedCount === 0}
            >
              删除{selectedCount > 0 && `(${selectedCount})`}
            </Button>
            <Button
              color="pink"
              variant="outlined"
              icon={<SwapOutlined />}
              onClick={handleBatchMove}
              // disabled={selectedCount === 0}
            >
              移动{selectedCount > 0 && `(${selectedCount})`}
            </Button>

            <Button
              color="purple"
              variant="outlined"
              icon={<ReloadOutlined />}
              onClick={handleReloadFile}
            >
              刷新文件
            </Button>
          </Space>
        </Space>

        <Input.Search
          placeholder="输入文件名搜索"
          allowClear
          enterButton={<SearchOutlined />}
          style={{ width: 300 }}
          onSearch={handleSearch}
        />
      </div>
    </>
  );
};
