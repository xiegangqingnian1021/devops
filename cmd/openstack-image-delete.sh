
source /root/keystonerc_admin

if [ $? -ne 0 ]; then
  echo "1:令牌错误"
  exit
fi

image_id=$(echo "$1")

openstack image show ${image_id} &> /dev/null
if [ $? -ne 0 ]; then
  echo "-1:镜像不存在"
  exit
fi

res=$(openstack image delete ${image_id})
if [ $? -ne 0 ]; then
  echo "2:删除失败:$res"
  exit
fi

echo "0:删除成功"
