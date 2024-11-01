# 加载令牌
source /root/keystonerc_admin

if [ $? -ne 0 ]; then
  echo "1:令牌加载失败"
  exit
fi

# 接收参数
project_id=$(echo "$1")
user_id=$(echo "$2")
role_name=$(echo "$3")

# 判定用户与租户是否已经关联
res=$(openstack role assignment list --user $user_id --project $project_id --format value | wc -l)
if [ $res -ne 0 ]; then
  echo "2:用户与租户已经关联"
  exit
fi


# 添加用户与租户的关联
res=$(openstack role add --project $project_id --user $user_id $role_name)
if [ $? -ne 0 ]; then
  echo "3:系统异常:$res"
  exit
fi

echo "0:关联完成"
