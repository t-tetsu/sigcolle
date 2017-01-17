package net.unit8.sigcolle.controller;

import enkan.collection.Multimap;
import enkan.component.doma2.DomaProvider;
import enkan.data.Flash;
import enkan.data.HttpResponse;
import enkan.data.Session;
import kotowari.component.TemplateEngine;
import net.unit8.sigcolle.dao.CampaignDao;
import net.unit8.sigcolle.dao.UserDao;
import net.unit8.sigcolle.form.CampaignForm;
import net.unit8.sigcolle.form.CreateCampaignForm;
import net.unit8.sigcolle.form.LoginForm;
import net.unit8.sigcolle.form.UserForm;
import net.unit8.sigcolle.model.Campaign;
import net.unit8.sigcolle.model.User;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;

import static enkan.util.BeanBuilder.builder;
import static enkan.util.HttpResponseUtils.RedirectStatusCode.SEE_OTHER;
import static enkan.util.HttpResponseUtils.redirect;

/**
 * Created by tie303856 on 2016/12/14.
 */
public class CreateCampaignController {
    @Inject
    TemplateEngine templateEngine;

    @Inject
    DomaProvider domaProvider;

    // キャンペーン作成画面表示
    @Transactional
    public HttpResponse index(CampaignForm form, Session session) throws IOException {

        if((session!=null) && (session.containsKey("userId"))) {
            return templateEngine.render("createCampaign",
                    "createCampaign", new CreateCampaignForm()
            );
        }
        return templateEngine.render("login",
                "login", new LoginForm()
        );
    }

    // キャンペーン登録処理
    @Transactional
    public HttpResponse create(CreateCampaignForm form, Session session) throws IOException {

        if (form.hasErrors()) {
            return templateEngine.render("createCampaign",
                    "createCampaign", form
            );
        }

        String statement = form.getStatement();
        PegDownProcessor processor = new PegDownProcessor(Extensions.ALL);
        statement = processor.markdownToHtml(statement);

        CampaignDao campaignDao = domaProvider.getDao(CampaignDao.class);

        Campaign campaign = builder(new Campaign())
                .set(Campaign::setTitle, form.getTitle())
                .set(Campaign::setStatement, statement)
                .set(Campaign::setGoal, form.getGoalLong())
                .set(Campaign::setCreatedBy, form.getCreatedByLong())
                .build();
        campaignDao.insert(campaign);

        int campaignId = campaignDao.countAll();

        return builder(redirect("/campaign/" + campaignId, SEE_OTHER))
                .set(HttpResponse::setFlash, new Flash("キャンペーンを作成しました。"))
                .build();
    }
}
