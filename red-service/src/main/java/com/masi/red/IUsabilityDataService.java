package com.masi.red;

import com.masi.red.entity.UsabilityData;
import com.masi.red.entity.User;

public interface IUsabilityDataService {

    UsabilityData persist(UsabilityData usabilityData, User user);
}
