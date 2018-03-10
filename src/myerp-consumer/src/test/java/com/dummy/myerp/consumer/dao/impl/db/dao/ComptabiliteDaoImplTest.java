package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import com.dummy.myerp.testconsumer.consumer.ConsumerTestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComptabiliteDaoImplTest extends ConsumerTestCase {

    @Test
    void getSequenceByCodeAndAnneeCourante() {
        SequenceEcritureComptable vRechercheSequence = new SequenceEcritureComptable();
        vRechercheSequence.setJournalCode("OD");
        vRechercheSequence.setAnnee(2016);
        SequenceEcritureComptable vExistingSequence = getDaoProxy().getComptabiliteDao().getSequenceByCodeAndAnneeCourante(vRechercheSequence);

        if (vExistingSequence != null) {
            assertEquals("OD", vExistingSequence.getJournalCode());
            assertEquals(2016, vExistingSequence.getAnnee().intValue());
            assertEquals(88, vExistingSequence.getDerniereValeur().intValue());
        } else fail("Incorrect result size: expected 1, actual 0");
    }
}