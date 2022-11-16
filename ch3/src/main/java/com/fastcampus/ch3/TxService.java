package com.fastcampus.ch3;

import com.fastcampus.ch3.A1Dao;
import com.fastcampus.ch3.B1Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import javax.swing.plaf.PanelUI;

@Service
public class TxService {
    @Autowired A1Dao a1Dao;
    @Autowired B1Dao b1Dao;
    @Autowired
    DataSource ds;

    //    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertA1WithTx() throws Exception {
        PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
        DefaultTransactionDefinition txd = new DefaultTransactionDefinition();
        txd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = tm.getTransaction(txd);
        // Tx 시작
        try {
            a1Dao.insert(1, 100); // 성공
            insertB1WtihTx();
            a1Dao.insert(2, 200); // 성공
            tm.commit(status);
        } catch (Exception e) {
            e.printStackTrace();
            tm.rollback(status);
        } finally {
        }

    }

    //    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void insertB1WtihTx() throws Exception {
        PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
        DefaultTransactionDefinition txd = new DefaultTransactionDefinition();
        txd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = tm.getTransaction(txd);

        // Tx 시작
        try {
            b1Dao.insert(1, 100); // 성공
            b1Dao.insert(1, 200); // 실패
            tm.commit(status);
        } catch (Exception e) {
            e.printStackTrace();
            tm.rollback(status);
        } finally {
        }
    }
    // 아래 애너에티션이 안된이유 - Tx가 분리 되지 않고 같은 Tx를 사용중 -
    // @Transactional이 동작하지 않은 이유는 같은 클래스에 속한 메서드끼리의 호출(내부호출)이기 떄문
    // 프록시 방식(디폴트)의 AOP는 내부 호출인 경우, Advice가 적용되지 않음. 그래서 Tx가 적용되지 않은것
    // 두 메서드를 별도의 클래스로 분리하면 Tx가  적용됨.
    // 근본적인 해결은 프록시 방식이 아닌 다른방식을 사용해야함.
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public void insertA1WithTx() throws Exception {
//        a1Dao.insert(1, 100);       // 성공
//        insertB1WithTx();
//        a1Dao.insert(1, 200);       // 성공
//    }
//
//    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
//    public void insertB1WithTx() throws Exception {
//        b1Dao.insert(1, 100);       // 성공
//        b1Dao.insert(2, 200);       // 실패
//    }

    public void insertA1WithoutTx() throws Exception {
        a1Dao.insert(1, 100);
        a1Dao.insert(1, 200);
    }

    @Transactional(rollbackFor = Exception.class)
//    @Transactional      // RuntimeException, Error만 rollback
    public void insertA1WithTxFail() throws Exception {
        a1Dao.insert(1, 100);       // 성공
//        throw new Exception();
//        throw new RuntimeException();
        a1Dao.insert(1, 200);       // 실패
    }

    @Transactional
    public void insertA1WithTxSuccess() throws Exception {
        a1Dao.insert(1, 100);
        a1Dao.insert(2, 200);
    }
}
