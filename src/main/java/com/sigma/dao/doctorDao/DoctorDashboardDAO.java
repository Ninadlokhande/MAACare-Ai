package com.sigma.dao.doctorDao;

import com.sigma.model.DoctorModel.DoctorDashboardModel;

public class DoctorDashboardDAO {

    public DoctorDashboardModel getDashboardData() {

        // Temporary hard-coded data
        // Firestore will be connected later.

        return new DoctorDashboardModel(
                18,
                6,
                32,
                4.8);
    }
}