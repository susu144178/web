package com.seonwu.board.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seonwu.board.common.constant.ResponseMessage;
import com.seonwu.board.dto.request.user.PatchProfileDto;
import com.seonwu.board.dto.response.ResponseDto;
import com.seonwu.board.dto.response.user.PatchProfileResponseDto;
import com.seonwu.board.entity.UserEntity;
import com.seonwu.board.repository.UserRepository;
import com.seonwu.board.service.UserService;

@Service
public class UserServiceImplements implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    public ResponseDto<PatchProfileResponseDto> patchProfile(String email, PatchProfileDto dto) {

        PatchProfileResponseDto data = null;

        String profile = dto.getProfile();

        try {

            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);

            userEntity.setProfile(profile);
            userRepository.save(userEntity);

            data = new PatchProfileResponseDto(userEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

}
