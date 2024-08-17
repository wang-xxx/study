package com.demo.tx.service;

import com.demo.tx.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    @Transactional(readOnly = false,
            timeout = 1,
            rollbackFor = FileNotFoundException.class,
            noRollbackFor = ArithmeticException.class,
            isolation = Isolation.DEFAULT,
            propagation = Propagation.REQUIRED)
    public void changeInfo() throws FileNotFoundException {
        studentDao.updateNameById("张三", 1);
        //int i = 10 / 0;

        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        //new FileInputStream("F:/a.txt");

        studentDao.updateAgeById(18, 1);
    }

    /**
     * 声明两个独立修改数据库方法
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void changeAge() {
        studentDao.updateAgeById(100, 1);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void changeName() {
        studentDao.updateNameById("lisi", 1);
        int i = 10/0;
    }

}
