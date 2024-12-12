# 参数
router_id=$(echo $1)
external_network_id=$(echo $2)
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

# 2.检查路由器是否已经配置网关
res=$(openstack router show ${router_id} --format value --column external_gateway_info)
if [ "$res" != "null" ]; then
  echo "2:路由器存在网关，请先清理网关"
  exit
fi

# 3.设置网关
neutron router-gateway-set ${router_id} ${external_network_id} &> /dev/null
if [ $? -ne 0 ]; then
  echo "3:网关设置异常"
  exit
fi

# 0.输出网关设置成功
echo "0:网关设置成功"
