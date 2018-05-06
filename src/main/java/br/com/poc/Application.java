package br.com.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Servidor de Oauth2
 * 
 * Um servidor Oauth é dividido em duas partes:
 * 
 * Authorization Server(@EnableAuthorizationServer):
 * 
 * Configura os endpoints de autenticação padrões da especificação Oauth2
 * e dos clients que são as aplicações que utilizam o servidor Ouath2, nesse
 * exemplo apenas um client é configurado no application.yml, para configurar
 * o authorization server é necessario definir uma classe como bean do Spring 
 * que extenda a classe AuthorizationServerConfigurerAdapter e de override nos
 * métodos configure para definir configurações customizadas.
 * 
 * Resource Server(@EnableResourceServer):
 * 
 * O resource server define quais urls devem ser protegidas pela autenticação
 * Oauth2, por padrão todas as urls do resource server menos as que começam com /ouath/
 * que são as urls padrões do Oauth2 são protegidas, para customizar as configurações
 * do ResourceServer é necessário declarar uma classe como @Configuration que extenda
 * ResourceServerConfigurerAdapter e dê override nos métodos configure.
 * 
 * Um resource server precisa referenciar um authorization server, caso não sejam
 * a mesma aplicação, isso é feito apartir das propriedades abaixo no application.yml:
 * 
 * security.oauth2.resource.user-info-uri
 * security.oauth2.resource.token-info-uri
 * 
 * Para incluir usuários nas aplicações para autenticação existe um exemplo na classe WebSecurityConfig.
 * 
 * Para recuperar um token nesse servidor é preciso realizar uma requisição post para:
 * 
 * localhost:8080/oauth/token
 * 
 * Com um header de http basic authorization com o username = clientId, e password = clientSecret
 * e no corpo um formulario com as seguintes chaves e valores:
 * 
 * grant_type = password
 * scope = qualquerEscopo
 * username = user (um dos usuários configurados em WebSecurityConfig)
 * password = password
 * 
 * O resultado dessa requisição é um token como o abaixo:
 * 
 * {
 *   "access_token": "e0426163-0074-43c2-8907-c2b7759f2073",
 *   "token_type": "bearer",
 *   "refresh_token": "e8261b21-a632-41e9-a21e-14ad676eb367",
 *   "expires_in": 42930,
 *   "scope": "webclient"
 *	}
 *
 * Para utilizar esse token para acessar o web service de exemplo declarado 
 * na classe SecureRestService é chamar o serviço que é um GET com um header
 * de valor:
 * 
 * Authorization = Bearer e0426163-0074-43c2-8907-c2b7759f2073(valor do token retornado no atributo access_token)
 */
@EnableResourceServer
@EnableAuthorizationServer
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
