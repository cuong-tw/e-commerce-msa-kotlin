package config;//package config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class WebConfig {
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
////                .csrf(AbstractHttpConfigurer::disable)
////                .authorizeHttpRequests(authConfig -> authConfig.anyRequest().authenticated())
////                .httpBasic(httpBasic -> {})
////                .formLogin(formLogin -> formLogin.loginPage("/login").permitAll())
////                .logout(
////                        logout -> {
////                            logout.logoutUrl("/logout");
////                            logout.addLogoutHandler(
////                                    ((request, response, authentication) -> authentication.setAuthenticated(false))
////                            );
////                        }
////                );
////        return http.build();
////    }
//
////    @Value("${app.eureka.username}")
////    private String username;
////    @Value("${app.eureka.password}")
////    private String password;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf->csrf.ignoringRequestMatchers("/eureka/**"))
//                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults());
//        return http.build();
//    }
//
////    @Bean
////    public UserDetailsService userDetailsService() {
////        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
////        UserDetails user = User
////                .withUsername(username)
////                .password(encoder.encode(password))
////                .roles("USER")
////                .build();
////        return new InMemoryUserDetailsManager(user);
////    }
//}
