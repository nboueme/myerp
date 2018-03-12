package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.testconsumer.consumer.ConsumerTestCase;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComptabiliteDaoImplTest extends ConsumerTestCase {

    @Test
    void getListCompteComptable() {
        List<CompteComptable> vList = getDaoProxy().getComptabiliteDao().getListCompteComptable();
        assertEquals(7, vList.size());
    }

    @Test
    void getListJournalComptable() {
        List<JournalComptable> vList = getDaoProxy().getComptabiliteDao().getListJournalComptable();
        assertEquals(4, vList.size());
    }

    @Test
    void getListEcritureComptable() {
        List<EcritureComptable> vList = getDaoProxy().getComptabiliteDao().getListEcritureComptable();
        assertEquals(5, vList.size());
    }

    @Test
    void getEcritureComptable() throws NotFoundException {
        EcritureComptable vEcritureComptable = getDaoProxy().getComptabiliteDao().getEcritureComptable(-3);
        assertEquals("BQ-2016/00003", vEcritureComptable.getReference());

        assertThrows(NotFoundException.class, () -> getDaoProxy().getComptabiliteDao().getEcritureComptable(0));
    }

    @Test
    void getEcritureComptableByRef() throws NotFoundException {
        EcritureComptable vEcritureComptable = getDaoProxy().getComptabiliteDao().getEcritureComptableByRef("BQ-2016/00003");
        assertEquals("BQ", vEcritureComptable.getJournal().getCode());
        String vEcritureYear = new SimpleDateFormat("yyyy").format(vEcritureComptable.getDate());
        assertEquals("2016", vEcritureYear);
        assertEquals(-3, vEcritureComptable.getId().intValue());

        assertThrows(NotFoundException.class, ()
                -> getDaoProxy().getComptabiliteDao().getEcritureComptableByRef("BQ-2016/33333"));
    }

    @Test
    void getSequenceByCodeAndAnneeCourante() throws NotFoundException {
        SequenceEcritureComptable vRechercheSequence = new SequenceEcritureComptable();
        vRechercheSequence.setJournalCode("OD");
        vRechercheSequence.setAnnee(2016);
        SequenceEcritureComptable vExistingSequence = getDaoProxy().getComptabiliteDao().getSequenceByCodeAndAnneeCourante(vRechercheSequence);

        if (vExistingSequence != null) {
            assertEquals("OD", vExistingSequence.getJournalCode());
            assertEquals(2016, vExistingSequence.getAnnee().intValue());
            assertEquals(88, vExistingSequence.getDerniereValeur().intValue());
        } else fail("Incorrect result size: expected 1, actual 0");

        assertThrows(NotFoundException.class, () -> {
            vExistingSequence.setAnnee(1963);
            getDaoProxy().getComptabiliteDao().getSequenceByCodeAndAnneeCourante(vExistingSequence);
        });
    }
}