package com.phuckim.springsecuritystarter.helper;

import com.phuckim.springsecuritystarter.model.entity.UserPrinciple;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * @author HIVELAB
 */
public class AuthenticationHelper {
    public static Integer getCurrentUserId() {
       var principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       if (principle instanceof UserPrinciple userPrinciple) {
           return userPrinciple.getId();
       }

       throw new AccessDeniedException("Access denied");
    }

    public static String getCurrentUsername() {
        var principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principle instanceof UserPrinciple userPrinciple) {
            return userPrinciple.getUsername();
        }

        throw new AccessDeniedException("Access denied");
    }
}
