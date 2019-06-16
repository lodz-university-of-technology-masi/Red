package com.masi.red;

import com.masi.red.entity.UsabilityData;
import com.masi.red.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsabilityDataService implements IUsabilityDataService {

    private final UsabilityDataRepository usabilityDataRepository;

    @Override
    public UsabilityData persist(UsabilityData usabilityData, User user) {
        UsabilityData lastUserUsabilityData =
                usabilityDataRepository.findTopByUsernameOrderByMeasurementNumberDesc(user.getUsername());
        usabilityData.setMeasurementNumber(lastUserUsabilityData.getMeasurementNumber() + 1);
        usabilityData.setUsername(user.getUsername());
        return usabilityDataRepository.save(usabilityData);
    }
}
