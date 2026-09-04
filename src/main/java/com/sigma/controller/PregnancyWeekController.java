package com.sigma.controller;

import com.sigma.dao.PregnancyWeekDAO;
import com.sigma.model.PregnancyWeekModel;

import java.util.List;

public class PregnancyWeekController {

    private final PregnancyWeekDAO pregnancyWeekDAO;

    public PregnancyWeekController() {
        pregnancyWeekDAO = new PregnancyWeekDAO();
    }

    public List<PregnancyWeekModel> getAllWeeks() {
        return pregnancyWeekDAO.getAllWeeks();
    }

    public PregnancyWeekModel getWeekByNumber(int week) {
        return pregnancyWeekDAO.getWeekByNumber(week);
    }

    public boolean saveWeek(PregnancyWeekModel week) {
        if (week == null) {
            return false;
        }

        return pregnancyWeekDAO.saveWeek(week);
    }

    public boolean updateWeek(PregnancyWeekModel week) {
        if (week == null) {
            return false;
        }

        return pregnancyWeekDAO.updateWeek(week);
    }

    public boolean deleteWeek(int week) {
        return pregnancyWeekDAO.deleteWeek(week);
    }

    public boolean seedDefaultWeeks() {
        return pregnancyWeekDAO.seedDefaultWeeks();
    }
}