package com.sesac.guseok_be.service;

import com.sesac.guseok_be.domain.User;
import com.sesac.guseok_be.domain.UserDTO;
import com.sesac.guseok_be.repository.UserEditRepository;
import org.springframework.stereotype.Service;

@Service
public class UserEditService {

    private final UserEditRepository userEditRepository;

    public UserEditService(UserEditRepository userEditRepository) {
        this.userEditRepository = userEditRepository;
    }

    public User getUser(String email) {
        return userEditRepository.findByEmail(email);
    }

    public User updateUser(UserDTO user, String email) {
        User edituser = userEditRepository.findByEmail(email);
        if(!email.equals(edituser.getEmail())) {
            throw new SecurityException("Forbidden: You can only update your own information");
        }

        edituser.setName(user.getName());
        edituser.setBirth(user.getBirth());
        edituser.setGender(user.getGender());
        edituser.setDistrict(user.getDistrict());

        return userEditRepository.save(edituser);
    }
}
