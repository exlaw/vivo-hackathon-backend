package vivo.chainpaper.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class RealNameUtil implements NameUtil {
    @Override
    public String getName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
