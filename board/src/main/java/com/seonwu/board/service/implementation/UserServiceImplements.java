package com.seonwu.board.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seonwu.board.common.constant.ResponseMessage;
import com.seonwu.board.dto.request.user.PatchProfileDto;
import com.seonwu.board.dto.request.user.ValidateEmailDto;
import com.seonwu.board.dto.request.user.ValidateNicknameDto;
import com.seonwu.board.dto.request.user.ValidateTelNumberDto;
import com.seonwu.board.dto.response.ResponseDto;
import com.seonwu.board.dto.response.user.GetUserResponseDto;
import com.seonwu.board.dto.response.user.PatchProfileResponseDto;
import com.seonwu.board.dto.response.user.ValidateEmailResponseDto;
import com.seonwu.board.dto.response.user.ValidateNicknameResponseDto;
import com.seonwu.board.dto.response.user.ValidateTelNumberResponseDto;
import com.seonwu.board.entity.UserEntity;
import com.seonwu.board.repository.UserRepository;
import com.seonwu.board.service.UserService;

@Service
public class UserServiceImplements implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    public ResponseDto<GetUserResponseDto> getUser(String email) {

        GetUserResponseDto data = null;

        try {

            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);

            data = new GetUserResponseDto(userEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<ValidateEmailResponseDto> validateEmail(ValidateEmailDto dto) {
        
        ValidateEmailResponseDto data = null;

        String email = dto.getEmail();

        try {

            boolean hasEmail = userRepository.existsByEmail(email);
            data = new ValidateEmailResponseDto(!hasEmail);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);       
    }

    public ResponseDto<ValidateNicknameResponseDto> validateNickname(ValidateNicknameDto dto) {

        ValidateNicknameResponseDto data = null;

        String nickname = dto.getNickname();

        try {

            boolean hasNickname = userRepository.existsByNickname(nickname);
            data = new ValidateNicknameResponseDto(!hasNickname);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<ValidateTelNumberResponseDto> validateTelNumber(ValidateTelNumberDto dto) {

        ValidateTelNumberResponseDto data = null;

        String telNumber = dto.getTelNumber();

        try {

            boolean hasTelNumber = userRepository.existsByTelNumber(telNumber);
            data = new ValidateTelNumberResponseDto(!hasTelNumber);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);      
    }

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
    
