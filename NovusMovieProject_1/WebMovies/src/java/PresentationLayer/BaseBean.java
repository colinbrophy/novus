package PresentationLayer;

import ApplicationVariables.AppVariables;
import ClassLayer.Person;
import ClassLayer.SimplisticFilm;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.model.SelectItem;

// Abstract bean class so cannot create objects of this type
public abstract class BaseBean {
    
    // Create a Drop down list
    protected <T>List populateDropDownList(List<T> dataSource){
        List<SelectItem> siList = new ArrayList();
         
       if(dataSource.size() > 1){
            //No selection option = <--SELECT--> 
            SelectItem noSelect = new SelectItem(null, AppVariables.WebProperties.dropDownDefault);
            noSelect.setNoSelectionOption(true);
            siList.add(noSelect);
       }

        // If the first entry is a SimplisticFilm
        if(dataSource != null && dataSource.get(0) instanceof SimplisticFilm){
            List<SimplisticFilm> tmpList = (List<SimplisticFilm>)dataSource;
            // Create a lists of JSF SelectItem objects with label film name and 
            // value of filmID
            siList.addAll(tmpList.stream()
                 .map(f -> new SelectItem(f.getFilmID(), f.getFilmName()))
                 .collect(Collectors.toList()));
        // If the first entry is a Person
        }else if(dataSource != null && dataSource.get(0) instanceof Person){
            List<Person> tmpList = (List<Person>)dataSource;
            // Create a lists of JSF SelectItem objects with label person name and 
            // value of personID
            siList.addAll(tmpList.stream()
                 .map(p -> new SelectItem(p.getID(), p.getName()))
                 .collect(Collectors.toList()));
        // If the first entry is a String
        }else if(dataSource != null && dataSource.get(0) instanceof String){
            // Create a lists of JSF SelectItem objects with value of the string
            List<String> tmpList = (List<String>)dataSource;
            tmpList.stream()
                .map(a -> siList.add(new SelectItem(a)))
                .collect(Collectors.toList());
        }
        
        // return the Created list
        return siList;
    }
    
}
