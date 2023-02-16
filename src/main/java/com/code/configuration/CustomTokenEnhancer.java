package com.code.configuration;

import com.code.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

// custom jwt token add info
@Slf4j
public class CustomTokenEnhancer implements TokenEnhancer {

    //토큰에 사용자 정보를 추가로 넣기 위해 Enhancer 클래스를 생성하며, corin_id 와 corin_nick_name 이라는 정보를 추가한다.
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("corin_id", user.getId());
        additionalInfo.put("corin_nick_name", user.getNickname());
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
