package com.seonwu.board.dto.response.user;

import com.seonwu.board.entity.UserEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "유저 정보 불러오기 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponseDto {

    @ApiModelProperty(value = "사용자 이메일", example = "susu144178@gmail.com", required = true)
    private String email;

    @ApiModelProperty(value = "사용자 닉네임", example = "noa", required = true)
    private String nickname;

    @ApiModelProperty(value = "사용자 휴대 전화번호", example = "010-1111-1111", required = true)
    private String telNumber;

    @ApiModelProperty(value = "사용자 주소", example = "부산광역시 부산진구", required = true)
    private String address;

    @ApiModelProperty(value = "사용자 프로필 URL", example = "http://~", required = true)
    private String profile;

    public GetUserResponseDto(UserEntity userEntity) {
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.telNumber = userEntity.getTelNumber();
        this.address = userEntity.getAddress();
        this.profile = userEntity.getProfile();
    }
    
}
