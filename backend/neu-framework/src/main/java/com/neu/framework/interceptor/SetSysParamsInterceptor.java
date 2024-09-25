package com.neu.framework.interceptor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.neu.common.annotation.CreateTime;
import com.neu.common.annotation.LoginUser;
import com.neu.common.annotation.UpdateTime;
import com.neu.common.core.domain.BaseEntity;
import com.neu.common.utils.SecurityUtils;

/**
 * 自动设置实体创建时间和修改时间拦截器
 * 
 * @author Administrator
 *
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class SetSysParamsInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		// 获取 SQL 命令
		SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
		// 获取参数
		Object[] parameters = invocation.getArgs();
		if (parameters == null || parameters.length == 0) {
			return invocation.proceed();
		}
		for (Object param : parameters) {
			if (param instanceof BaseEntity) {
				setSysParamValue(sqlCommandType, param);
			}
		}
		return invocation.proceed();
	}

	private void setSysParamValue(SqlCommandType sqlCommandType, Object parameter) throws IllegalAccessException {
		// 获取私有成员变量
		Field[] fields = getAllFields(parameter);
		for (Field field : fields) {
			setCreateTime(sqlCommandType, parameter, field);
			setUpdateTime(sqlCommandType, parameter, field);
			setSysUser(sqlCommandType, parameter, field);
		}
	}

	private void setSysUser(SqlCommandType sqlCommandType, Object parameter, Field field)
			throws IllegalAccessException {
		LoginUser loginUser = field.getAnnotation(LoginUser.class);
		if (loginUser != null) { // insert 或 update 语句插入 createBy
			Authentication authentication = SecurityUtils.getAuthentication();
			if (authentication == null) {
				return;
			}
			if (authentication instanceof AnonymousAuthenticationToken) {
				return;
			}
			if (SqlCommandType.INSERT.equals(sqlCommandType) && !loginUser.update()) {
				com.neu.common.core.domain.model.LoginUser sysUser = (com.neu.common.core.domain.model.LoginUser) authentication
						.getPrincipal();
				field.setAccessible(true);
				Object value = field.get(parameter);
				if (value == null) {
					field.set(parameter, String.valueOf(sysUser.getUser().getUserId()));
				}
			} else if (SqlCommandType.UPDATE.equals(sqlCommandType) && loginUser.update()) {
				com.neu.common.core.domain.model.LoginUser sysUser = (com.neu.common.core.domain.model.LoginUser) authentication
						.getPrincipal();
				field.setAccessible(true);
				Object value = field.get(parameter);
				if (value == null) {
					field.set(parameter, String.valueOf(sysUser.getUser().getUserId()));
				}
			}
		}
	}

	private void setUpdateTime(SqlCommandType sqlCommandType, Object parameter, Field field)
			throws IllegalAccessException {
		if (field.getAnnotation(UpdateTime.class) != null) { // update 语句插入 updateTime
			if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
				field.setAccessible(true);
				Object value = field.get(parameter);
				if (value == null) {
					field.set(parameter, new Date());
				}
			}
		}
	}

	private void setCreateTime(SqlCommandType sqlCommandType, Object parameter, Field field)
			throws IllegalAccessException {
		if (field.getAnnotation(CreateTime.class) != null) {
			if (SqlCommandType.INSERT.equals(sqlCommandType)) { // insert 语句插入 createTime
				field.setAccessible(true);
				Object value = field.get(parameter);
				if (value == null) {
					field.set(parameter, new Date());
				}
			}
		}
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}

	private Field[] getAllFields(Object object) {
		Class<?> clazz = object.getClass();
		List<Field> fieldList = new ArrayList<>();
		while (clazz != null) {
			fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
			clazz = clazz.getSuperclass();
		}
		Field[] fields = new Field[fieldList.size()];
		fieldList.toArray(fields);
		return fields;
	}
}
