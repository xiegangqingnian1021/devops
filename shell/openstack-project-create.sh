source /root/keystonerc_admin

if [ $? -ne 0 ]; then
  # 令牌加载失败
  echo "1:令牌加载失败"
  exit
fi

# 检查平台中是否已经存在对应名称的租户

project_name=$(echo $1)
project_description=$(echo $2)

openstack project show ${project_name} &> /dev/null

if [ $? -eq 0 ]; then
  # 对应名称的租户已经存在
  echo "2:对应名称的租户已经存在"
  exit
fi

# 创建租户

res=$(openstack project create --description ${project_description} ${project_name} --format value -c id)

if [ $? -ne 0 ]; then
  # 系统异常创建失败
  echo "3:系统异常创建失败:$res"
  exit
fi

echo "0:租户创建成功:$res"
