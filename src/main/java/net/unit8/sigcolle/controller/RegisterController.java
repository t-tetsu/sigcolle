package net.unit8.sigcolle.controller;

import enkan.component.doma2.DomaProvider;
import enkan.data.Flash;
import enkan.data.HttpResponse;
import enkan.data.Session;
import kotowari.component.TemplateEngine;
import net.unit8.sigcolle.dao.CampaignDao;
import net.unit8.sigcolle.dao.UserDao;
import net.unit8.sigcolle.form.CampaignForm;
import net.unit8.sigcolle.form.UserForm;
import net.unit8.sigcolle.model.Campaign;
import net.unit8.sigcolle.model.User;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;

import static enkan.util.BeanBuilder.builder;
import static enkan.util.HttpResponseUtils.RedirectStatusCode.SEE_OTHER;
import static enkan.util.HttpResponseUtils.redirect;
import static enkan.util.ThreadingUtils.some;

/**
 * Created by tie303856 on 2016/12/14.
 */
public class RegisterController {
    @Inject
    TemplateEngine templateEngine;

    @Inject
    DomaProvider domaProvider;

    @Transactional
    public HttpResponse index(CampaignForm form) throws IOException {

        CampaignDao campaignDao = domaProvider.getDao(CampaignDao.class);
        Campaign campaign = campaignDao.selectById(new Long(1));

        return templateEngine.render("register",
                "campaign", campaign
        );
    }

    @Transactional
    public HttpResponse register(UserForm form, Session session) throws IOException {

        if (form.hasErrors()) {
            return builder(HttpResponse.of("Invalid"))
                    .set(HttpResponse::setStatus, 400)
                    .build();
        }

        UserDao userDao = domaProvider.getDao(UserDao.class);
        if (userDao.countByUserId(form.getUserIdLong()) != 0){
            return templateEngine.render("register" );
        }

        User user = builder(new User())
                .set(User::setUserId, form.getUserIdLong())
                .set(User::setLastName, form.getLastName())
                .set(User::setFirstName, form.getFirstName())
                .set(User::setEmail, form.getEmail())
                .set(User::setPass, form.getPass())
                .build();

        userDao.insert(user);

        if (session == null) {
            session = new Session();
        }
        String name = form.getLastName() + form.getFirstName();
        session.put("name", name);

        CampaignDao campaignDao = domaProvider.getDao(CampaignDao.class);
        Campaign campaign = campaignDao.selectById(new Long(1));

        return builder(redirect("/campaign/1", SEE_OTHER))
                .set(HttpResponse::setFlash, new Flash("ようこそ" + name + "さん"))
                .set(HttpResponse::setSession, session)
                .build();
    }

    @Transactional
    public HttpResponse login(UserForm form, Session session) throws IOException {

        UserDao userDao = domaProvider.getDao(UserDao.class);
        User user = userDao.selectById(form.getUserIdLong());

        if (session == null) {
            session = new Session();
        }
        String name = user.getLastName() + user.getFirstName();
        session.put("name", name);

        CampaignDao campaignDao = domaProvider.getDao(CampaignDao.class);
        Campaign campaign = campaignDao.selectById(new Long(1));



        return builder(redirect("/campaign/1", SEE_OTHER))
                .set(HttpResponse::setFlash, new Flash("ようこそ" + name + "さん"))
                .set(HttpResponse::setSession, session)
                .build();
    }
}
