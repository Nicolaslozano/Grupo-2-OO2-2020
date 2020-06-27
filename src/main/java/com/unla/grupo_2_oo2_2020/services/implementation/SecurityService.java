package com.unla.grupo_2_oo2_2020.services.implementation;

import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.services.ISecurityService;
import com.unla.grupo_2_oo2_2020.services.IUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * We create SecurityService to provide current logged-in user and auto login
 * user after registration
 */

@Service("securityService")
public class SecurityService implements ISecurityService {

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
	@Qualifier("userService")
	private IUserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    @Override
    public String findLoggedInUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }else {
            return principal.toString();
        }
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("AUTO LOGIN SUCCESSFUL %s", username));
        }
    }

    @Override
    // devuelve una vista http 403 si el usuario logueado tiene el rol recibido
    public ModelAndView redirectAccessForbidden(String role) {
        ModelAndView mAV;

		if (userService.hasRole(findLoggedInUsername(), role)) {
			mAV = new ModelAndView(ViewRouteHelper.ERROR_FORBIDDEN);
			return mAV;
        }

        return null;
    }

}