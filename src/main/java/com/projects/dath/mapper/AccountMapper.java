package com.projects.dath.mapper;

import com.projects.dath.model.Admin;
import com.projects.dath.model.Customer;
import com.projects.dath.dto.RegisterReq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target="role", expression = "java(com.projects.dath.model.UserRole.ADMIN)")
    Admin toAdmin(RegisterReq user);
    @Mapping(target="role", expression = "java(com.projects.dath.model.UserRole.CUSTOMER)")
    Customer toCustumer(RegisterReq user);
}
