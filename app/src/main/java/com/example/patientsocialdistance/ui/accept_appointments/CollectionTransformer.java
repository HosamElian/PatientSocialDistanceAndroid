package com.example.patientsocialdistance.ui.accept_appointments;

import com.example.patientsocialdistance.pojo.DTOs.VisitorRequestVisitDTO;
import com.example.patientsocialdistance.pojo.DTOs.VisitsAcceptedDTO;

import java.util.ArrayList;
import java.util.List;

public abstract class CollectionTransformer<E, F> {

    abstract F transform(E e);

    public List<F> transform(List<E> list) {
        List<F> newList = new ArrayList<F>();
        for (E e : list) {
            newList.add(transform(e));
        }
        return newList;
    }
    public static List<VisitorRequestVisitDTO> mapVisitsAcceptedDTOToVisitorRequestVisitDTOCollection(List<VisitsAcceptedDTO> list) {

        CollectionTransformer<VisitsAcceptedDTO, VisitorRequestVisitDTO> transformer = new CollectionTransformer<>() {
            @Override
            VisitorRequestVisitDTO transform(VisitsAcceptedDTO visitsAcceptedDTO) {
                return new VisitorRequestVisitDTO(0, visitsAcceptedDTO.visitorUsername, visitsAcceptedDTO.startDate,
                        visitsAcceptedDTO.message, visitsAcceptedDTO.durationInMinutes, false, "");
            }

        };
        return transformer.transform(list);
    }
}


