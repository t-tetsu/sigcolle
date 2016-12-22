package net.unit8.sigcolle.controller;

import enkan.component.doma2.DomaProvider;
import enkan.data.Flash;
import enkan.data.HttpResponse;
import enkan.data.Session;
import kotowari.component.TemplateEngine;
import net.unit8.sigcolle.dao.CampaignDao;
import net.unit8.sigcolle.dao.UserDao;
import net.unit8.sigcolle.form.CampaignForm;
import net.unit8.sigcolle.form.LoginForm;
import net.unit8.sigcolle.form.UserForm;
import net.unit8.sigcolle.model.Campaign;
import net.unit8.sigcolle.model.User;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;

import static enkan.util.BeanBuilder.builder;
import static enkan.util.HttpResponseUtils.RedirectStatusCode.SEE_OTHER;
import static enkan.util.HttpResponseUtils.redirect;

/**
 * Created by tie303856 on 2016/12/14.
 */
public class LoginController {
    @Inject
    TemplateEngine templateEngine;

    @Inject
    DomaProvider domaProvider;

    // ログイン画面表示
    @Transactional
    public HttpResponse index(CampaignForm form) throws IOException {

        CampaignDao campaignDao = domaProvider.getDao(CampaignDao.class);
        Campaign campaign = campaignDao.selectById(new Long(1));

        return templateEngine.render("login",
                "campaign", campaign
        );
    }

    // ログイン処理
    @Transactional
    public HttpResponse login(LoginForm form, Session session) throws IOException {

        UserDao userDao = domaProvider.getDao(UserDao.class);
        User user = userDao.selectByEmail(form.getEmail());

        // パスワードチェック
        if (!user.getPass().equals(form.getPass())) {
            // ToDo:入力フォームに戻してエラーメッセージを出力したい
        }
        if (session == null) {
            session = new Session();
        }
        String name = user.getLastName() + " " + user.getFirstName();
        session.put("name", name);

        CampaignDao campaignDao = domaProvider.getDao(CampaignDao.class);
        Campaign campaign = campaignDao.selectById(new Long(1));

        return builder(redirect("/campaign/1", SEE_OTHER))
                .set(HttpResponse::setSession, session)
                .build();

    }

    // ログアウト処理
    @Transactional
    public HttpResponse logout(Session session) throws IOException {

        if (session != null) {
            session.clear();
        }

        return builder(redirect("/campaign/1", SEE_OTHER))
                .set(HttpResponse::setSession, session)
                .build();
    }
}
