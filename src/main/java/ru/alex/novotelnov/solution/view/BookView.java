package ru.alex.novotelnov.solution.view;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.alex.novotelnov.data.entity.BookEntity;
import ru.alex.novotelnov.solution.facade.LibraryFacade;

@Component
@Scope("singleton")
public class BookView {
    private final LibraryFacade libraryFacade;

    @Autowired
    public BookView(LibraryFacade libraryFacade) {
        this.libraryFacade = libraryFacade;
    }

    public void updateBookByOnCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (!newValue.equals(oldValue)) {
            BookEntity bookEntity = (BookEntity) ((DataTable) event.getComponent()).getRowData();
            if (bookEntity != null)
            {
                libraryFacade.updateBook(bookEntity);
            }
        }
    }
}
