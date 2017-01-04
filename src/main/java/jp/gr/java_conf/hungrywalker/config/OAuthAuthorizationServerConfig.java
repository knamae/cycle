package jp.gr.java_conf.hungrywalker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuthAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter
{
    @Bean
    public TokenStore tokenStore()
    {
        return new InMemoryTokenStore();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception
    {
        endpoints.tokenStore(tokenStore());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception
    {
        clients.inMemory().withClient("oauth_client").resourceIds(OAuthResourceConfig.RESOURCE_ID)
                .scopes("read").authorizedGrantTypes("authorization_code")
                .redirectUris("http://localhost:8080").secret("oauth_client_secret");
    }
}
