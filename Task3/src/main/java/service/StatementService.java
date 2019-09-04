package service;

import dao.StatementDao;
import models.Entrant;
import models.Statement;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StatementService {

    private StatementDao statementDao = new StatementDao(new ExecutorService());
    private EntrantService entrantService = new EntrantService();

    public Statement getStatement(){

        Statement statement = statementDao.getAll().get(0);
        if (statement !=null){
            statement.setEntrants(getEntrants(statement));
        } else {
            statement = createEmptyStatement();

        }


        return statement;
    }

    public void createStatement(Statement statement){
        statementDao.create(statement);
    }

    public void deleteStatement(){statementDao.deleteAll();}

    public Statement getStatementById(Long id){
        Statement statement = statementDao.getEntityById(id);
        if (statement != null) {
            statement.setEntrants(getEntrants(statement));
        } else {
            statement = createEmptyStatement();
        }
        return statement;
    }

    private List<Entrant> getEntrants(Statement statement){
        return statement.getEntrants()
                .stream()
                .map(entrant -> entrantService.getEntrantById(entrant.getId()))
                .collect(Collectors.toList());

    }

    private Statement createEmptyStatement(){
        Statement statement = new Statement();
        statement.setEntrants(Collections.emptyList());
        return statement;
    }



}
