package restaurant.repositories;

import restaurant.common.ExceptionMessages;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.TableRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TableRepositoryImpl implements TableRepository<Table> {

    private Collection<Table> entities;

    public TableRepositoryImpl() {
        this.entities = new ArrayList<>();
    }

    @Override
    public Collection<Table> getAllEntities() {
        return Collections.unmodifiableCollection(entities);
    }

    @Override
    public void add(Table entity) {

        if (byNumber(entity.getTableNumber()) != null) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.TABLE_IS_ALREADY_ADDED, entity.getTableNumber()));
        }

        this.entities.add(entity);
    }

    @Override
    public Table byNumber(int tableNumber) {
        return this.entities.stream().filter(t -> t.getTableNumber() == tableNumber)
                .findFirst().orElse(null);
    }
}
