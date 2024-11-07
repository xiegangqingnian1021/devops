# 用于解除用户与租户的关联关系

# 加载令牌
source /root/keystonerc_admin
if [ $? -ne 0 ]; then
  echo "1:令牌加载失败"
  exit
fi

# 解除关联关系
project_id=$(echo "$1")
user_id=$(echo "$2")
role_name=$(echo "$3")
res=$(openstack role remove --project ${project_id} --user ${user_id} ${role_name})
if [ $? -ne 0 ]; then
  echo "2:$res"
  exit
fi

echo "0:租户用户解除关联"



