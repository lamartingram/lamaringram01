/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adm.virtualrealitygladiators;

import com.adm.virtualrealitygladiators.dao.VRGCategoryDao;
import com.adm.virtualrealitygladiators.dao.VRGStaticPageDao;
import com.adm.virtualrealitygladiators.dao.VRGUserDao;
import com.adm.virtualrealitygladiators.dto.StaticPage;
import com.adm.virtualrealitygladiators.dto.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Daniel McBride
 */
@Controller
public class VRGStaticPageController {

    private VRGStaticPageDao staticPageDao;
    private VRGUserDao userDao;
    private VRGCategoryDao categoryDao;

    @Inject
    public VRGStaticPageController(VRGStaticPageDao staticPageDao, VRGUserDao userDao,
            VRGCategoryDao categoryDao) {
        this.staticPageDao = staticPageDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
    }

    @RequestMapping(value = "/static/{pageName}", method = RequestMethod.GET)
    public String displayStaticPage(@PathVariable("pageName") String pageName, Model model) {
        StaticPage page = staticPageDao.getStaticPageByName(pageName);

        model.addAttribute("page", page);
        model.addAttribute("categories", categoryDao.getAllCategories());

        return "displayStaticPage";
    }

    @RequestMapping(value = "/displayCreateStaticPage")
    public String displayCreateStaticPage(Model model) {
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "createStaticPage";
    }

    @RequestMapping(value = "/postStaticPage", method = RequestMethod.POST)
    public String createStaticPage(HttpServletRequest request, Model model) {
        String title = request.getParameter("addPageTitle");
        String post = request.getParameter("addPageEditor");
        List<String> errorMessages = new ArrayList<>();
        boolean hasErrors = false;
        StaticPage page = new StaticPage();

        if (title == null || title.isEmpty()) {
            String errorMessage = "Please enter a title";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        if (post == null || post.isEmpty()) {
            String errorMessage = "Please enter a body for the static page";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        if (hasErrors) {
            model.addAttribute("errorMessages", errorMessages);
            return "createStaticPage";
        }
        page.setTitle(title);
        page.setPost(post);
        page.setDate(LocalDate.now());
        page.setUser(userDao.getUserById(1));
        staticPageDao.addStaticPage(page);

        model.addAttribute("categories", categoryDao.getAllCategories());

        return "redirect: static/" + page.getTitle();
    }

    @RequestMapping(value = "/adminAllPages", method = RequestMethod.GET)
    public String displayAdminAllPages(Model model) {
        List<StaticPage> pages = staticPageDao.getAllStaticPages();
        model.addAttribute("allPages", pages);
        model.addAttribute("categories", categoryDao.getAllCategories());
        return "adminAllStaticPages";
    }

    @RequestMapping(value = "/displayEditStatic/{staticId}", method = RequestMethod.GET)
    public String displayEditStatic(@PathVariable("staticId") int id, Model model) {
        StaticPage page = staticPageDao.getStaticPageById(id);
        model.addAttribute("page", page);
        return "editStaticPage";
    }

    @RequestMapping(value = "/editStaticPage", method = RequestMethod.POST)
    public String editStaticPage(HttpServletRequest request, Model model) {
        String title = request.getParameter("editPageTitle");
        String post = request.getParameter("editPageEditor");
        String idParam = request.getParameter("editPageId");
        List<String> errorMessages = new ArrayList<>();
        boolean hasErrors = false;
        if (title == null || title.isEmpty()) {
            String errorMessage = "Please enter a title";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        if (post == null || post.isEmpty()) {
            String errorMessage = "Please enter a body for the static page";
            errorMessages.add(errorMessage);
            hasErrors = true;
        }
        int id = 0;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            String em = "Something went wrong. Contact your system admin if problem persists";
            errorMessages.add(em);
            hasErrors = true;
        }
        if (hasErrors) {
            model.addAttribute("errorMessages", errorMessages);
            return "editStaticPage";
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userDao.getUserByUserName(username);
        StaticPage page = new StaticPage();
        page.setStaticPageId(id);
        page.setTitle(title);
        page.setPost(post);
        page.setDate(LocalDate.now());
        page.setUser(user);
        staticPageDao.updateStaticPage(page);

        model.addAttribute("categories", categoryDao.getAllCategories());

        return "redirect: static/" + page.getTitle();
    }

}
