# 0.接收参数
router_name=$(echo $1)
router_description=$(echo $2)
project_id=$(echo $3)

# 1.验证令牌
source /root/keystonerc_admin
if [ $? -ne 0 ]; then
  echo "1:令牌加载失败"
  exit
fi

# 2.验证路由器是否已经存在
openstack router show ${router_name} &> /dev/null
if [ $? -eq 0 ]; then
  echo "2:路由器重名"
  exit
fi

# 3.创建路由器
## res将保存路由器的id
res=$(openstack router create --enable --description ${router_description} --project ${project_id} ${router_name} --format value --column id)
if [ $? -ne 0 ]; then
  echo "3:路由器创建失败:${res}"
  exit
fi

# 0.将获取到路由器ID输出
echo "0:路由器创建成功:${res}"

