# 参数
router_id=$(echo $1)
internal_subnet_id=$(echo $2)
user_name=$(echo $3)
user_pwd=$(echo $4)
project_id=$(echo $5)

# 1.加载令牌
unset OS_SERVICE_TOKEN
export OS_USERNAME=$(echo ${user_name})
export OS_PASSWORD=$(echo ${user_pwd})
export OS_AUTH_URL=http://192.168.105.100:5000/v2.0
export OS_TENANT_ID=$(echo ${project_id})
export OS_REGION_NAME=RegionOne

# 2.给路由器添加内网
neutron router-interface-add ${router_id} ${internal_subnet_id} &> /dev/null
if [ $? -ne 0 ]; then
  echo "2:添加内网异常"
  exit
fi

# 0. 添加内网成功
echo "0:添加内网成功"
