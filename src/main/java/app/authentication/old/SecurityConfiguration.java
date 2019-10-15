//package app.authentication.old;
//
//import app.service.UtentiService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//        @Autowired
//        private UtentiService utentiService;
//
//        @Autowired
//        private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
//
//        @Autowired
//        private MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler;
//
//        private SimpleUrlAuthenticationFailureHandler myFailureHandler = new SimpleUrlAuthenticationFailureHandler();
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
////            http
////                .csrf().disable()
////                .exceptionHandling()
////                .authenticationEntryPoint(restAuthenticationEntryPoint)
////                .and()
////                .authorizeRequests()
////                .antMatchers("/api/**").authenticated()
//////                    .antMatchers("/api/admin/**").hasRole("ADMIN")
////                .and()
////                .formLogin()
////                .successHandler(mySuccessHandler)
////                .failureHandler(myFailureHandler)
////                .and()
////                .logout();
//
//
//            http
//                    .cors().and()
//                    .csrf().disable()
//                    .exceptionHandling()
//                    .authenticationEntryPoint(restAuthenticationEntryPoint)
//                    .and()
//                    .authorizeRequests()
//                    .antMatchers("/api/**").authenticated()
//                    .and()
//                    .addFilter(new JwtAuthenticationFilter(authenticationManager()))
//                    .addFilter(new JwtAuthorizationFilter(authenticationManager()))
//                    .formLogin()
//                    .successHandler(mySuccessHandler)
//                    .failureHandler(myFailureHandler)
//                    .and()
//                    .logout();
////                    .sessionManagement()
////                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        }
//
//
//
////        @Override
////        protected void configure(HttpSecurity http) throws Exception {
////            http
////                    .authorizeRequests()
////                    .anyRequest().authenticated()
////                    .and()
////                    .httpBasic()
//////                    .and()
//////                    .formLogin().loginPage("/login").failureUrl("/login?error").permitAll()
////                    .and()
////                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
//////                    .logout().logoutUrl("j_spring_security_logout").logoutSuccessUrl("/")
////            .invalidateHttpSession(true);
////        }
//
//        @Bean
//        public BCryptPasswordEncoder passwordEncoder(){
//            return new BCryptPasswordEncoder();
//        }
//
//        @Bean
//        public DaoAuthenticationProvider authenticationProvider(){
//            DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
//            auth.setUserDetailsService(utentiService);
//            auth.setPasswordEncoder(passwordEncoder());
//            return auth;
//        }
//
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.authenticationProvider(authenticationProvider());
//        }
//
//
////        @Override
////        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////            auth.inMemoryAuthentication()
////                    .withUser("admin").password(encoder().encode("adminPass")).roles("ADMIN")
////                    .and()
////                    .withUser("user").password(encoder().encode("userPass")).roles("USER");
////        }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//
//        return source;
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .authorizeRequests()
//////                .antMatchers("/css/**").permitAll()
////                .antMatchers("/error").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login").permitAll()
////                .and()
////                .logout().permitAll();
////    }
//
////    @Override
////    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
////                .and()
////                .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
////                .and()
////                .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
////    }
//
////    @Override
////    protected void configure(final HttpSecurity http) throws Exception {
////        http
////                .csrf().disable()
////                .authorizeRequests()
//////                .antMatchers("/admin/**").hasRole("ADMIN")
//////                .antMatchers("/anonymous*").anonymous()
////                .antMatchers("/login*").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login.html")
//////                .loginProcessingUrl("/perform_login")
////                .defaultSuccessUrl("/homepage.html", true)
////                .failureUrl("/login.html?error=true")
//////                .failureHandler(authenticationFailureHandler())
////                .and()
////                .logout()
////                .logoutUrl("/logout")
////                .deleteCookies("JSESSIONID");
//////                .logoutSuccessHandler(logoutSuccessHandler());
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////}
