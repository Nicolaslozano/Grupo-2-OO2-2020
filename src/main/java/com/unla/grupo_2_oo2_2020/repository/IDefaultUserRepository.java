package com.unla.grupo_2_oo2_2020.repository;

import javax.transaction.Transactional;

import com.unla.grupo_2_oo2_2020.entities.User;

import org.springframework.stereotype.Repository;

@Transactional
@Repository("userRepository")
public interface IDefaultUserRepository extends IUserRepository<User>{
}