package com.epam.filter;

import com.epam.entity.User;
import com.epam.entity.enums.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebFilter(urlPatterns = { "/controller?command=*"}, servletNames = {"Controller"})
public class CommandSecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        User user = (User) req.getSession().getAttribute("user");
        Role role;
        if (user != null){
            role = user.getRole();
        } else {
            role = Role.GUEST;
        }

        Map<String, List<Role>> commandRoleMap = initializeCommandRoleMap();
        String command = req.getParameter("command");
        if (commandRoleMap.containsKey(command)){
            List<Role> roles = commandRoleMap.get(command);
            if (!roles.contains(role)){
                resp.sendRedirect(req.getContextPath() + "/error.jsp");
                return;
            }
        }
        chain.doFilter(request, response);
    }


    private Map<String, List<Role>> initializeCommandRoleMap(){
        List<Role> guest = new ArrayList<>(Arrays.asList(Role.GUEST));
        List<Role> admin = new ArrayList<>(Arrays.asList(Role.ADMIN));
        List<Role> userAndAdmin = new ArrayList<>(Arrays.asList(Role.USER, Role.ADMIN));
        Map<String, List<Role>> map = new HashMap<>();
        map.put("login", guest);
        map.put("loginPage", guest);
        map.put("main", userAndAdmin);
        map.put("showUsers", admin);
        map.put("users", admin);
        map.put("audios", userAndAdmin);
        map.put("audio", admin);
        map.put("showAddAudio", admin);
        map.put("showAudios", userAndAdmin);
        map.put("addAudio", admin);
        map.put("showAddArtist", admin);
        map.put("addArtist", admin);
        map.put("sendArtistForAddAlbum", admin);
        map.put("showAddAlbumSecond", admin);
        map.put("showAlbums", userAndAdmin);
        map.put("showAddAlbum", admin);
        map.put("sendAudiosForAddAlbumSecond", admin);
        map.put("addAlbum", admin);
        map.put("sendAlbum", userAndAdmin);
        map.put("showAlbum", userAndAdmin);
        map.put("sendAlbums", userAndAdmin);
        map.put("block", admin);
        map.put("unblock", admin);
        map.put("sendAudioIdForAddReview", userAndAdmin);
        map.put("showAddReview", userAndAdmin);
        map.put("addReview", userAndAdmin);
        map.put("sendReviews", userAndAdmin);
        map.put("showReviews", userAndAdmin);
        map.put("deleteAudio", admin);
        map.put("showAddOrder", userAndAdmin);
        map.put("sendAudioIdAndPriceForAddOrder", userAndAdmin);
        map.put("addOrder", userAndAdmin);

        return map;
    }

    @Override
    public void destroy() {

    }
}
