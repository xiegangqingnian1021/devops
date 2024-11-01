# 加载令牌
source /root/keystonerc_admin
if [ $? -ne 0 ]; then
  echo "1:令牌加载失败"
  exit
fi

# 判断租户是否存在
project_id=$(echo $1)
openstack project show ${project_id} &> /dev/null
if [ $? -ne 0 ]; then
  echo "2:租户不存在"
  exit
fi

# 删除租户
res=$(openstack project delete ${project_id})
if [ $? -ne 0 ]; then
  echo "3:删除租户失败:${res}"
  exit
fi
echo "0:租户删除成功"
