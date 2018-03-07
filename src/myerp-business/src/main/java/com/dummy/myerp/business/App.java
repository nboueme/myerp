package com.dummy.myerp.business;

import com.dummy.myerp.business.contrat.BusinessProxy;
import com.dummy.myerp.business.impl.BusinessProxyImpl;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/bootstrapContext.xml");
        BusinessProxy businessProxy = applicationContext.getBean("businessProxy", BusinessProxyImpl.class);

        // Ne pas oublier de remettre les scopes postgres à 'test'
        for (CompteComptable compteComptable : businessProxy.getComptabiliteManager().getListCompteComptable()) {
            System.out.println(compteComptable.toString());
        }
    }
}
