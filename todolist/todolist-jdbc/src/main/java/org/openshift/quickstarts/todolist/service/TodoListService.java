package org.openshift.quickstarts.todolist.service;

import io.prometheus.client.Counter;
import org.apache.log4j.Logger;
import org.openshift.quickstarts.todolist.dao.JdbcTodoListDAO;
import org.openshift.quickstarts.todolist.dao.TodoListDAO;
import org.openshift.quickstarts.todolist.model.TodoEntry;

import java.util.List;

/**
 *
 */
public class TodoListService {
    private static Logger logger = Logger.getLogger( TodoListService.class );

    static final Counter daoCounter = Counter.build()
            .name("todolist_total").help("Total calls.")
            .labelNames("key").register();

    private TodoListDAO dao = new JdbcTodoListDAO();

    public void addEntry(TodoEntry entry) {
        logger.info("Adding entry <" + entry + ">");
        daoCounter.labels("addEntry").inc();
        dao.save(entry);
    }

    public List<TodoEntry> getAllEntries() {
        logger.info("getAllEntries");
        daoCounter.labels("getAllEntries").inc();
        return dao.list();
    }
}
