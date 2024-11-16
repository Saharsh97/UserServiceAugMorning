package org.example.userserviceaugmorning;

import org.example.userserviceaugmorning.security.repositories.JpaRegisteredClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

@SpringBootTest
public class AddEntryTest {

    @Autowired
    JpaRegisteredClientRepository jpaRegisteredClientRepository;

    @Test
    public void addClientToDB(){
        RegisteredClient scalerClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("youtube")
                .clientSecret("$2a$12$LudBOndrPhyK4rxDkk3MZOY3rxt5lE9yppZolW1kuqJyK53LcP8tK") // bcrypted value for "secret"
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("https://oauth.pstmn.io/v1/callback")
                .postLogoutRedirectUri("http://127.0.0.1:8080/")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .scope("ADMIN")
                .scope("VIEWER")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();
        jpaRegisteredClientRepository.save(scalerClient);
    }

}
